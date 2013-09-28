package QuickKarView;

import QuickKarInput.PaginationPanel;
import QuickKarModel.facade.QuickKarModel;
import QuickKarModel.facade.SongListSortFunction;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author s3342135
 */
public class SortChooserPanel extends JPanel {

    private JCheckBox sortCheckBox;
    private JCheckBox descendingCheckBox;
    private JRadioButton optionByCode;
    private JRadioButton optionByTitle;
    private JRadioButton optionByLyric;
    private JRadioButton optionByComposer;
    private JRadioButton optionByRating;
    private ButtonGroup radioGroup;
    private JPanel panelRadioBtn;
    private List<String> songList;
    private PaginationPanel paginationPanel;
    private SongListSortFunction sort;
    private ResourceBundle resources=QuickKarModel.getModel().getResource();

    public void setSongList(List<String> songList) {
        this.songList = songList;
    }

    private boolean isDescendingOrder() {

        if (sortCheckBox.isSelected() && descendingCheckBox.isSelected()) {
            return true;
        } else {
            return false;
        }
    }

    public void sortBy(int type) {

        sort.sortBy(SortChooserPanel.this.songList, type, isDescendingOrder());
        SortChooserPanel.this.paginationPanel.flushPage(1);
    }

    public void sortBySelectedOption() {

        if (sortCheckBox.isSelected()) {

            int type;

            if (optionByCode.isSelected()) {
                type = SongListSortFunction.SONG_CODE;
            } else if (optionByTitle.isSelected()) {
                type = SongListSortFunction.SONG_NAME;
            } else if (optionByLyric.isSelected()) {
                type = SongListSortFunction.SONG_LYRIC;
            } else if (optionByComposer.isSelected()) {
                type = SongListSortFunction.SONG_COMPOSER;
            } else {
                type = SongListSortFunction.SONG_RATE;
            }
            sortBy(type);
        } else {
            sortBy(SongListSortFunction.SONG_CODE);
        }
    }

    public SortChooserPanel(PaginationPanel paginationPanel, List<String> songList) {

        this.songList = songList;
        this.sort = new SongListSortFunction();
        this.paginationPanel = paginationPanel;
        SortChooserPanel.this.setLayout(new FlowLayout(FlowLayout.LEFT));
        SortChooserPanel.this.setOpaque(false);

        JPanel panelChkbox = new JPanel();
        panelChkbox.setOpaque(false);
        sortCheckBox = new JCheckBox(resources.getString("sort"));
        sortCheckBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (sortCheckBox.isSelected()) {
                    panelRadioBtn.setVisible(true);
                    optionByCode.setSelected(true);
                } else {
                    panelRadioBtn.setVisible(false);
                }
            }
        });
        panelChkbox.add(sortCheckBox);

        panelRadioBtn = new JPanel();
        panelRadioBtn.setOpaque(false);
        optionByCode = new JRadioButton(resources.getString("optionByCode"));
        optionByTitle = new JRadioButton(resources.getString("optionByTitle"));
        optionByLyric = new JRadioButton(resources.getString("optionByLyric"));
        optionByComposer = new JRadioButton(resources.getString("optionByComposer"));
        optionByRating = new JRadioButton(resources.getString("optionByRating"));
        descendingCheckBox = new JCheckBox(resources.getString("descendingCheckBox"));
        panelRadioBtn.add(optionByCode);
        panelRadioBtn.add(optionByTitle);
        panelRadioBtn.add(optionByLyric);
        panelRadioBtn.add(optionByComposer);
        panelRadioBtn.add(optionByRating);
        panelRadioBtn.add(descendingCheckBox);
        radioGroup = new ButtonGroup();
        radioGroup.add(optionByCode);
        radioGroup.add(optionByTitle);
        radioGroup.add(optionByLyric);
        radioGroup.add(optionByComposer);
        radioGroup.add(optionByRating);
        panelRadioBtn.setVisible(false);
        SortChooserPanel.this.add(panelChkbox);
        SortChooserPanel.this.add(panelRadioBtn);

        //actionListener
        optionByCode.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sortBy(SongListSortFunction.SONG_CODE);
            }
        });
        optionByTitle.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sortBy(SongListSortFunction.SONG_NAME);
            }
        });
        optionByLyric.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sortBy(SongListSortFunction.SONG_LYRIC);
            }
        });
        optionByComposer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sortBy(SongListSortFunction.SONG_COMPOSER);
            }
        });
        optionByRating.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sortBy(SongListSortFunction.SONG_NAME);
            }
        });
        descendingCheckBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                sortBySelectedOption();
            }
        });
    }
}
