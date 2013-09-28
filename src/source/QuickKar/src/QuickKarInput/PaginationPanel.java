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

import QuickKarController.SongInfoInterface;
import QuickKarModel.facade.QuickKarModel;
import QuickKarModel.facade.SongInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class PaginationPanel extends JPanel implements SongInfoInterface {

    public static final int ITEM_PER_PAGE = 30;
    private CustomPaginationButton[] paginationButtons;
    private Map<String, SongInfo> songMap;
    private DefaultTableModel tableModel;
    private List<String> songList;
    private JButton firstButton;
    private JButton lastButton;
    private int currentPage;
    private int maxPage;
    private String username;
    private QuickKarModel model = QuickKarModel.getModel();
    private ResourceBundle resources = model.getResource();
    
    public PaginationPanel(Map<String, SongInfo> songMap, List<String> songList, String username) {

        //Model
        this.username = username;
        this.songMap = songMap;
        this.songList = songList;
        this.currentPage = 0;

        //GUI
        this.setOpaque(false);
        this.firstButton = new JButton(resources.getString("firstButton"));
        this.lastButton = new JButton(resources.getString("lastButton"));
        this.paginationButtons = new CustomPaginationButton[5];
        add(firstButton);
        for (int i = 0; i < 5; i++) {
            this.paginationButtons[i] = new CustomPaginationButton((i + 1) + "");
            this.paginationButtons[i].setOpaque(false);
            add(paginationButtons[i]);
        }
        add(lastButton);
        firstButton.setOpaque(false);
        lastButton.setOpaque(false);

        firstButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                flushPage(1);
            }
        });
        lastButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                flushPage(maxPage);
            }
        });
        updateMaxPage();
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void setSongList(List<String> songList) {
        this.songList = songList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /*
     * Remove all record from table
     */
    private void removeAllRecord() {
        tableModel.getDataVector().removeAllElements();
    }

    public void listAllRecord() {
        songList = new ArrayList<String>(songMap.keySet());
        Collections.sort(songList);
        flushPage(1);
    }

    /*
     * rearrangePaginationButton method
     * It rearrages or sets visibility for each type of button
     */
    private void rearrangePaginationButton(int buttonNumber) {

        //reset all buttons's features
        for (int i = 0; i < 5; i++) {
            paginationButtons[i].setEnabled(true);
            paginationButtons[i].setVisible(true);
        }
        firstButton.setEnabled(true);
        lastButton.setEnabled(true);
        if (buttonNumber == 1) {
            firstButton.setEnabled(false);
        }
        if (buttonNumber == maxPage || maxPage == 0) {
            lastButton.setEnabled(false);
        }
        //set button invisible if the record don't have enough
        if (maxPage < 5) {
            for (int i = maxPage; i < 5; i++) {
                paginationButtons[i].setVisible(false);
            }
            for (int i = 0; i < maxPage; i++) {
                paginationButtons[i].setText((i + 1) + "");
                if (i + 1 == buttonNumber) {
                    paginationButtons[i].setEnabled(false);
                }
            }
        } else {
            if (buttonNumber == 1 || buttonNumber == 2) {
                for (int i = 0; i < 5; i++) {
                    if (i + 1 == buttonNumber) {
                        paginationButtons[i].setEnabled(false);
                    }
                    paginationButtons[i].setText((i + 1) + "");
                }
            } else if (buttonNumber == maxPage - 1 || buttonNumber == maxPage) {
                for (int i = 0; i < 5; i++) {
                    if ((i == 3 && buttonNumber == maxPage - 1) || (i == 4 && buttonNumber == maxPage)) {
                        paginationButtons[i].setEnabled(false);
                    }
                    paginationButtons[i].setText((maxPage - (4 - i)) + "");
                }
            } else {
                paginationButtons[0].setText((buttonNumber - 2) + "");
                paginationButtons[1].setText((buttonNumber - 1) + "");
                paginationButtons[2].setText(buttonNumber + "");
                paginationButtons[2].setEnabled(false);
                paginationButtons[3].setText((buttonNumber + 1) + "");
                paginationButtons[4].setText((buttonNumber + 2) + "");
            }
        }
    }

    private void displayPage(int pageNumber) {

        if (pageNumber == 1 && songList.size() < ITEM_PER_PAGE) {
            displayTable(0, songList.size());
        } else if (pageNumber == maxPage) {
            displayTable((pageNumber - 1) * ITEM_PER_PAGE, songList.size());
        } else {
            displayTable((pageNumber - 1) * ITEM_PER_PAGE, pageNumber * ITEM_PER_PAGE);
        }
    }

    /*
     * Display tabel method
     *
     * With a given index and a size, display tabel method
     * will fill the table with the row that contains information
     * about the song
     */
    private void displayTable(int index, int size) {

        for (int i = index; i < size; i++) {
            //create data
            Object[] object = new Object[5];

            object[SONGCODE] = songList.get(i);
            //get account infomation
            SongInfo songInfo = songMap.get(songList.get(i));

            object[SONGNAME] = songInfo.getName();
            object[LYRIC] = songInfo.getLyric();
            object[COMPOSER] = songInfo.getComposer();
            object[RATE] = QuickKarModel.getModel().getStar(username, songList.get(i));

            tableModel.addRow(object);
        }
    }

    /*
     * Remove record then redisplay the table
     */
    private void updateMaxPage() {
        maxPage = (int) Math.ceil(songList.size() * 1.0 / ITEM_PER_PAGE);
    }

    public void flushPage(int pageNumber) {

        updateMaxPage();
        removeAllRecord();
        displayPage(pageNumber);
        rearrangePaginationButton(pageNumber);
        currentPage = pageNumber;
    }

    /*
     * Make a private custom button to paginate among pages
     */
    private class CustomPaginationButton extends JButton implements ActionListener {

        public CustomPaginationButton(String text) {
            super(text);
            addActionListener();
        }

        private void addActionListener() {
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {

            //get the button number
            int buttonNumber = Integer.valueOf(this.getText());
            currentPage = buttonNumber;
            flushPage(buttonNumber);
        }
    }
}
