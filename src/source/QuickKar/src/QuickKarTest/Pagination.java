package QuickKarTest;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Pagination extends JFrame {

    private CustomPaginationButton[] paginationButtons = new CustomPaginationButton[5];
    private int maxPage;
    private int itemPerPage;
    private ArrayList<String> songList = new ArrayList<String>();

    public Pagination() {

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //test pagination button
        for (int i = 0; i < 5; i++) {
            paginationButtons[i] = new CustomPaginationButton((i + 1) + "");
        }
        setLayout(new FlowLayout());
        for (int i = 0; i < 5; i++) {
            add(paginationButtons[i]);
        }
        for (int i = 0; i < 200; i++) {
            songList.add("" + (i + 1));
        }
        itemPerPage = 50;
        maxPage = (int) Math.ceil(songList.size() * 1.0 / itemPerPage);
        rearrangePaginationButton(1);
        System.out.println(maxPage);
        pack();
        validate();
    }

    private void rearrangePaginationButton(int buttonNumber) {

        //reset all buttons's features
        for (int i = 0; i < 5; i++) {
            paginationButtons[i].setEnabled(true);
            paginationButtons[i].setVisible(true);
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
        pack();
    }

    public void displayTable(int index, int size) {
        System.out.println("Table: " + index + "--" + size);
        for (int i = index; i < size; i++) {
            System.out.println(i + ":" + songList.get(i));
        }
    }

    public void displayPage(int pageNumber) {

        if (pageNumber == 1 && songList.size() < itemPerPage) {
            displayTable(0, itemPerPage);
        } else if (pageNumber == maxPage) {
            displayTable((pageNumber - 1) * itemPerPage, songList.size());
        } else {
            displayTable((pageNumber - 1) * itemPerPage, pageNumber * itemPerPage);
        }
    }

    private class CustomPaginationButton extends JButton implements ActionListener {

        public CustomPaginationButton(String text) {
            super(text);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {

            int buttonNumber = Integer.valueOf(this.getText());
            displayPage(buttonNumber);
            rearrangePaginationButton(buttonNumber);
        }
    }

    public static void main(String[] args) {

        Pagination frame = new Pagination();
    }
}
