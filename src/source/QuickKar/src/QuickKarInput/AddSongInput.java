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
import QuickKarModel.facade.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Add panel for Song
 * 
 * @author vuongdothanhhuy
 */
public class AddSongInput extends JDialog {

    private JTextField songCodeTF = new JTextField(20);
    private JTextField songNameTF = new JTextField(20);
    private JTextField songLyricTF = new JTextField(20);
    private JTextField songComposerTF = new JTextField(20);
    private QuickKarModel model = QuickKarModel.getModel();
    private Map<String, SongInfo> songMap;
    private DefaultTableModel tableModel;
    private ResourceBundle resources = QuickKarModel.getModel().getResource();
    
    public AddSongInput(Map<String, SongInfo> songMap, DefaultTableModel tableModel) {

        super();
        setTitle(resources.getString("AddSongInputTitle"));
        add(getAddSongInputPanel());
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.songMap = songMap;
        this.tableModel = tableModel;
    }

    private JPanel getAddSongInputPanel() {

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

        //create OK button
        JButton okButton = new JButton(resources.getString("okButton"));

        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String songCode = songCodeTF.getText().trim();
                String songName = songNameTF.getText().trim();
                String songLyric = songLyricTF.getText().trim();
                String songComposer = songComposerTF.getText().trim();
                String message = "";
                
                if (songCode.matches("[\\d]{5,5}")) {
                    if (songMap.containsKey(songCode)) {
                        message += resources.getString("messageExsitCode");
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
                    tableModel.addRow(new Object[]{songCode, songName, songLyric, songComposer});
                    model.addSongMap(songCode, songName, songLyric, songComposer);
                    JOptionPane.showMessageDialog(null, resources.getString("addSongSuccess"), null, JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    SavingAndReadingController.saveSong();
                } else {
                    JOptionPane.showMessageDialog(null, message, resources.getString("addSongError"), JOptionPane.ERROR_MESSAGE);
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

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(contentPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }
}
