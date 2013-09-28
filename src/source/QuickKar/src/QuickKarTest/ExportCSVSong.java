package QuickKarTest;

import QuickKarInput.*;
import QuickKarModel.facade.QuickKarModel;
import QuickKarModel.facade.SongInfo;
import QuickKarTest.ExportSong;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 *
 * Export tool.
 *
 * @author s3342135
 */
public class ExportCSVSong extends JDialog {

    private JProgressBar progressBar;
    private JTextArea textArea;
    private JButton okButton;
    private JButton cancelButton;
    private SwingWorkClass swingWorkClass;
    private File file;
    private boolean cancelled;
    private boolean closing;
    private String filePath;

    public ExportCSVSong() {

        //initial Dialog panel
        setTitle("Export song to CSV...");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JFileChooser fileChooser = new JFileChooser();
        try {
            file = new File(new File("SongLists.csv").getCanonicalPath());
            fileChooser.setSelectedFile(file);
        } catch (IOException ex) {
            Logger.getLogger(ExportCSVSong.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

            //export csv and display logfile
            file = fileChooser.getSelectedFile();
            filePath = file.getPath();

            add(getSaveSongPanel());
            pack();
            setVisible(true);
            setLocationRelativeTo(null);
            setModalityType(ModalityType.DOCUMENT_MODAL);
            swingWorkClass = new SwingWorkClass();
            swingWorkClass.execute();
        } else {
            dispose();
        }
    }

    public boolean isClosing() {
        return closing;
    }

    private JPanel getSaveSongPanel() {

        JPanel returnPanel = new JPanel(new BorderLayout());

        progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        textArea = new JTextArea(20, 60);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        progressBar.setStringPainted(true);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);

        //button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        okButton.setEnabled(false);
        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cancelled = true;
            }
        });
        returnPanel.add(progressBar, BorderLayout.NORTH);
        returnPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        returnPanel.add(buttonPanel, BorderLayout.SOUTH);

        return returnPanel;
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            cancelled = true;
            while (!swingWorkClass.isDone()) {
            }
            dispose();
        }
    }

    private class SwingWorkClass extends SwingWorker<Integer, String> {

        @Override
        protected Integer doInBackground() throws Exception {
            exportCSV();
            return 0;
        }

        @Override
        protected void done() {
            super.done();
            okButton.setEnabled(true);
            cancelButton.setEnabled(false);
        }

        @Override
        protected void process(List<String> chunks) {
            for (int i = 0; i < chunks.size(); i++) {
                textArea.append(chunks.get(i));
            }
        }

        private void exportCSV() {
            FileWriter f = null;
            Map<String, SongInfo> songMap = QuickKarModel.getModel().getSongMap();
            while (!cancelled) {
                try {
                    f = new FileWriter(filePath);

                    for (Map.Entry<String, SongInfo> entry : songMap.entrySet()) {
                        String songCode = entry.getKey();

                        SongInfo songInfo = entry.getValue();
                        String songName = songInfo.getName();
                        String songLyric = songInfo.getLyric();
                        String songComposer = songInfo.getComposer();

                        f.write(songCode);
                        f.write(",");
                        f.write("\"");
                        f.write(songName);
                        f.write("\"");
                        f.write(",");
                        f.write("\"");
                        f.write(songLyric);
                        f.write("\"");
                        f.write(",");
                        f.write("\"");
                        f.write(songComposer);
                        f.write("\"");
                        f.write("\n");
                    }
                    publish("Saving complete");
                    progressBar.setValue(100);
                } catch (IOException ex) {
                    Logger.getLogger(ExportSong.class.getName()).log(Level.SEVERE, null, ex);
                    textArea.append("Cannot save the file");
                } finally {
                    if (f != null) {
                        try {
                            f.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ExportSong.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }
}
