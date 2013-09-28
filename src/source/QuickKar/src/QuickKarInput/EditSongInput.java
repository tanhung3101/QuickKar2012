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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 * Edit dialog for song.
 *
 * @author vuongdothanhhuy
 */
public class EditSongInput extends JDialog {

    private static final int SONG_CODE = 0;
    private static final int SONG_NAME = 1;
    private static final int SONG_LYRIC = 2;
    private static final int SONG_COMPOSER = 3;
    private JTextField songCodeTF = new JTextField(20);
    private JTextField songNameTF = new JTextField(20);
    private JTextField songLyricTF = new JTextField(20);
    private JTextField songComposerTF = new JTextField(20);
    private DefaultTableModel tableModel;
    private QuickKarModel model =  QuickKarModel.getModel();;
    private Map<String, SongInfo> songMap;
    private int selectedRow;
    private String oldSongCode;
    private PaginationPanel panel;
    //private int selectedRow;
    private ResourceBundle resources = model.getResource();

    public EditSongInput(Map<String, SongInfo> songMap, PaginationPanel panel, DefaultTableModel tableModel, int selectedRow) {

        super();

        this.songMap = songMap;
        this.tableModel = tableModel;
        this.selectedRow = selectedRow;
        this.panel = panel;
        setTitle(resources.getString("EditSongTitle"));

        add(getEditSongInputPanel());
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }

    private JPanel getEditSongInputPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        //create top panel
        JPanel contentPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        contentPanel.add(new JLabel(resources.getString("InputSongCode")));
        contentPanel.add(songCodeTF);
        contentPanel.add(new JLabel(resources.getString("InputSongName")));
        contentPanel.add(songNameTF);
        contentPanel.add(new JLabel(resources.getString("InputSongLyric")));
        contentPanel.add(songLyricTF);
        contentPanel.add(new JLabel(resources.getString("InputSongComposer")));
        contentPanel.add(songComposerTF);

        //create button panel
        JPanel buttonPanel = new JPanel();

        //create Edit button
        JButton editButton = new JButton(resources.getString("editSongButton"));



        String selectedCode = (String) tableModel.getValueAt(selectedRow, 0);
        songCodeTF.setText(selectedCode);

        oldSongCode = (String) tableModel.getValueAt(selectedRow, 0);
        songCodeTF.setText(oldSongCode);


        SongInfo songInfo = songMap.get(oldSongCode);
        songNameTF.setText(songInfo.getName());
        songLyricTF.setText(songInfo.getLyric());
        songComposerTF.setText(songInfo.getComposer());

        editButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String songCode = songCodeTF.getText().trim();
                String songName = songNameTF.getText().trim();
                String songLyric = songLyricTF.getText().trim();
                String songComposer = songComposerTF.getText().trim();

                String message = "";

                if (songCode.matches("[\\d]{5,5}")) {
                    if (songMap.containsKey(songCode) && !songCode.equals(oldSongCode)) {
                        message = resources.getString("messageExsitCode");
                    }
                } else {
                    message += resources.getString("message5Didist");
                }
                if (songName.isEmpty()) {
                    message += resources.getString("messageEmtyName");
                }
                if (songLyric.isEmpty()) {
                    message += resources.getString("messageEmtyLyric");
                }
                if (songComposer.isEmpty()) {
                    message += resources.getString("messageEmtyComposer");
                }
                if (message.isEmpty()) {
                    
                    tableModel.setValueAt(songCode, selectedRow, SONG_CODE);
                    tableModel.setValueAt(songName, selectedRow, SONG_NAME);
                    tableModel.setValueAt(songLyric, selectedRow, SONG_LYRIC);
                    tableModel.setValueAt(songComposer, selectedRow, SONG_COMPOSER);
                    model.editSongMap(oldSongCode, songCode, songName, songLyric, songComposer);
                    JOptionPane.showMessageDialog(null, resources.getString("editSongSuccess"), null, JOptionPane.INFORMATION_MESSAGE);
                    panel.listAllRecord();
                    dispose();
                    SavingAndReadingController.saveSong();
                } else {
                    JOptionPane.showMessageDialog(null, message, resources.getString("editSongError"), JOptionPane.ERROR_MESSAGE);
                    songCodeTF.requestFocusInWindow();
                }
            }
        });
        
        //create cancel button and add dispose listener
        JButton cancelButton = new JButton(resources.getString("cancelButton"));
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(contentPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }
}
