package QuickKarInput;

import QuickKarController.SavingAndReadingController;
import QuickKarController.SongInfoInterface;
import QuickKarModel.facade.QuickKarModel;
import QuickKarModel.facade.SongInfo;
import QuickKarView.EmptyPanel;
import QuickKarView.PaintedPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FavouriteSong extends JDialog implements SongInfoInterface{

    private String username;
    private JPanel contentPanel;
    private int row;
    private QuickKarModel model = QuickKarModel.getModel();
    private ResourceBundle resources = model.getResource();

    public FavouriteSong(String username) {

        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(400, 500);

        this.username = username;
        Map<String, SongInfo> songMap = QuickKarModel.getModel().getSongMap();
        List<String> favoriteList = QuickKarModel.getModel().getFavoriteSongCodeList(username);

        row = favoriteList.size();
        contentPanel = new JPanel(new GridLayout(favoriteList.size(), 1));

        for (int i = 0; i < favoriteList.size(); i++) {
            String songCode = favoriteList.get(i);
            String songName = songMap.get(songCode).getName();
            contentPanel.add(addFavoritePanel(songCode, songName));
        }
        PaintedPanel wrapPanel = new PaintedPanel(BACKGROUND1);
        wrapPanel.setLayout(new BorderLayout());
        contentPanel.setOpaque(false);
        JPanel centerPanel = new JPanel();
        centerPanel.add(contentPanel);
        centerPanel.setOpaque(false);
        wrapPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        JLabel label = new JLabel(resources.getString("favoriteButton"));
        label.setFont(new Font(resources.getString("font"), Font.BOLD, 25));
        label.setForeground(Color.white);
        topPanel.add(label);
        topPanel.setOpaque(false);
        wrapPanel.add(topPanel, BorderLayout.NORTH);

        add(new JScrollPane(wrapPanel));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel addFavoritePanel(String songCode, String songName) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(350, 100));

        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        leftPanel.add(new JLabel("" + songName+"  [" + songCode+"]"));
//        leftPanel.add(new JLabel());
        leftPanel.add(new EmptyPanel());

//        JPanel leftPanel = new JPanel();
//        JLabel label = new JLabel(songName+" ["+songCode+"]");
//        label.setHorizontalAlignment(JLabel.LEFT);
//        leftPanel.add(label);

        JButton button = new JButton(resources.getString("removeButton"));
//        button.setFont(new Font("Ravie", Font.ITALIC, 10));
//        JPanel rightPanel = new JPanel(new GridBagLayout());
         JPanel rightPanel = new JPanel();
        rightPanel.add(button);

        leftPanel.setOpaque(false);
        rightPanel.setOpaque(false);

        panel.add(leftPanel, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);

        button.addActionListener(new ButtonActionListener(panel, songCode));
        panel.setOpaque(false);
        return panel;
    }

    private class ButtonActionListener implements ActionListener {

        private JPanel panel;
        private String songCode;

        public ButtonActionListener(JPanel panel, String songCode) {
            this.panel = panel;
            this.songCode = songCode;
        }

        public void actionPerformed(ActionEvent e) {

            contentPanel.remove(panel);
            contentPanel.updateUI();
            contentPanel.setLayout(new GridLayout(--row, 1));
            QuickKarModel.getModel().removeFavoriteSong(username, songCode);
            SavingAndReadingController.saveFavoriteMap();
        }
    }
}
