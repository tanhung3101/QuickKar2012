/**
 * Course: Software Development: Process & Tools
 * Lecturer: Quang Tran
 * Assignment: 2-2012B
 * Assignment Title: QuickKar2012
 *
 *      RMIT International University Vietnam
 * Bachelor of Information Technology - Application
 *
 */
package QuickKarInput;

import QuickKarController.SavingAndReadingController;
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
import java.util.ResourceBundle;
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
/**
 * Create import panel and handle import process.
 *
 * @author vuongdothanhhuy
 */
public class ImportCSVInput extends JDialog {

    private JProgressBar progressBar;
    private JTextArea textArea;
    private JButton okButton;
    private JButton cancelButton;
    private SwingWorkClass swingWorkClass;
    private File[] files;
    private boolean cancelled;
    private boolean closing;
    private QuickKarModel model = QuickKarModel.getModel();
    private ResourceBundle resources = model.getResource();

    public ImportCSVInput() {

        //initial Dialog panel
        setTitle("Import CSV file");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //using file chooser to let user input the file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
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
            files = fileChooser.getSelectedFiles();
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
        okButton = new JButton(resources.getString("okButton"));
        cancelButton = new JButton(resources.getString("cancelButton"));

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
                SavingAndReadingController.saveSong();
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
            final int LYRIC = 3;
            final int COMPOSER_1 = 4;
            final int COMPOSER_2 = 5;

            QuickKarModel model = QuickKarModel.getModel();
            Map<String, SongInfo> songMap = model.getSongMap();
            for (int i = 0; i < files.length; i++) {
                try {
                    Scanner scanner = new Scanner(files[i], "UTF-8");
                    long length = files[i].length();
                    long process = 0;
                    int error = 0;
                    int success = 0;
                    publish("Reading file " + files[i].getName() + "\n");

                    while (scanner.hasNextLine() && !cancelled) {
                        String temp = scanner.nextLine();
                        String[] itemsProperties = temp.split("\"");
                        if (itemsProperties.length == 6) {
                            itemsProperties[SONGCODE] = itemsProperties[SONGCODE].substring(0, 5);
                            if (songMap.containsKey(itemsProperties[SONGCODE])) {
                                error++;
                            } else {
                                model.addSongMap(itemsProperties[SONGCODE], itemsProperties[SONGNAME], itemsProperties[LYRIC], itemsProperties[COMPOSER_2]);
                                success++;
                            }
                            process += temp.getBytes().length;
                            progressBar.setValue((int) ((process * 100.0) / length));
                        } else if (itemsProperties.length == 5) {
                            itemsProperties[SONGCODE] = itemsProperties[SONGCODE].substring(0, 5);
                            itemsProperties[COMPOSER_1] = itemsProperties[COMPOSER_1].substring(1);
                            if (songMap.containsKey(itemsProperties[SONGCODE])) {
                                error++;
                            } else {
                                model.addSongMap(itemsProperties[SONGCODE], itemsProperties[SONGNAME], itemsProperties[LYRIC], itemsProperties[COMPOSER_1]);
                                success++;
                            }
                            process += temp.getBytes().length;
                            progressBar.setValue((int) ((process * 100.0) / length));
                        }
                    }
                    publish("Loading " + files[i].getName() + " complete; Error: " + error + "; Success: " + success + "\n\n");
                    progressBar.setValue(100);
                    scanner.close();
                } catch (Exception ex) {
                    publish(ex.getMessage() + "\n");
                }
            }
            publish("Loading complete" + "\n");
        }
    }
}
