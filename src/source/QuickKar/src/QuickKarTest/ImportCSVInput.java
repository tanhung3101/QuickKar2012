package QuickKarTest;

import QuickKarInput.*;
import QuickKarModel.facade.QuickKarModel;
import QuickKarModel.facade.SongInfo;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileFilter;

public class ImportCSVInput extends JDialog {

    private JProgressBar progressBar;
    private JTextArea textArea;
    private JButton okButton;
    private JButton cancelButton;
    private SwingWorkClass swingWorkClass;
    private File file;
    private boolean cancelled;
    private boolean closing;

    public ImportCSVInput() {

        //initial Dialog panel
        setTitle("Import CSV file");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //using file chooser to let user input the file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileFilter() {

            //only allow CSV file
            @Override
            public boolean accept(File f) {
                return (f.getName().endsWith(".csv") || f.isDirectory()) ? true : false;
            }

            @Override
            public String getDescription() {
                return ".csv";
            }
        });
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            //import csv and display logfile
            file = fileChooser.getSelectedFile();
            add(getAddSongInputPanel());
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

    private JPanel getAddSongInputPanel() {

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
            importCSV();
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

        private void importCSV() {

            final int SONGCODE = 0;
            final int SONGNAME = 1;
            final int LYRIC = 2;
            final int COMPOSER = 3;

//        List<String> songCodeList = QuickKarModel.getModel().getSongCodeList();
//        List<SongInfo> songInfoList = QuickKarModel.getModel().getSongInfoList();
            QuickKarModel model = QuickKarModel.getModel();
            Map<String, SongInfo> songMap = model.getSongMap();

            try {
                Scanner scanner = new Scanner(file);
                long length = file.length();
                long process = 0;
                while (scanner.hasNextLine() && !cancelled) {
                    String temp = scanner.nextLine();
                    String[] itemsProperties = temp.split("\\|");
                    try {
                        if (songMap.containsKey(itemsProperties[SONGCODE])) {
//                            publish("Error...  Cannot add song with the code " + itemsProperties[SONGCODE] + "\n");
                        } else {
//                            publish("Sucessfullly added song with the code " + itemsProperties[SONGCODE] + "\n");
                            model.addSongMap(itemsProperties[SONGCODE], itemsProperties[SONGNAME], itemsProperties[LYRIC], itemsProperties[COMPOSER]);
                        }
                        process += temp.getBytes().length;
                        progressBar.setValue((int) ((process * 100.0) / length));
//                    songCodeList.add(itemsProperties[SONGCODE]);
//                    songInfoList.add(new SongInfo(itemsProperties[SONGNAME], itemsProperties[LYRIC], itemsProperties[COMPOSER]));
                    } catch (Exception ex) {
                        textArea.append("Cannot read the file");
                    }
                }
                publish("Loading complete");
                progressBar.setValue(100);
                scanner.close();
            } catch (Exception e) {
                textArea.append("Cannot open the file");
            }
        }
    }
}
