/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickKarTest;

import QuickKarController.*;

import QuickKarModel.facade.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nop
 */
public class FavoriteSongPanel extends JPanel implements SongInfoInterface {

    private Map<String, HashSet<String>> favoriteMap = new HashMap<String, HashSet<String>>();
    private HashSet<String> songCodeList;
    SongInfo song1 = new SongInfo("name1", "liric1", "aaaa1");
    SongInfo song2 = new SongInfo("name2", "liric2", "aaaa2");
    SongInfo song3 = new SongInfo("name3", "liric3", "aaaa3");
    JPanel mainPanel;
    private QuickKarModel model;
    private JTable table;
    private TableCellRender myTabelCellRender;
    private DefaultTableModel tableModel;
    Map<String, SongInfo> songMap;
    private JButton b1, b2, b3, ShowButton;

    public FavoriteSongPanel() {
        model = QuickKarModel.getModel();

        //songmap=model.getSongMap();

        //this is a list of All song
        songMap = new HashMap<String, SongInfo>();


        //button to add favorite song
        b1 = new JButton("song1");
        b2 = new JButton("song2");
        b3 = new JButton("song3");
        ShowButton = new JButton("Show");


        //add song to AllList
        songMap.put("111", song1);
        songMap.put("112", song2);
        songMap.put("113", song3);

        System.out.println(songMap.toString());

        //add sample 2 phone Number
        favoriteMap.put("08113", null);
        favoriteMap.put("08114", null);
        songCodeList = new HashSet<String>();
        final Scanner re = new Scanner(System.in);

        //Action to Add FAvorite Song
        b1.addActionListener(new AddFavoriteSong(favoriteMap, songCodeList, tableModel, 3));
        b2.addActionListener(new AddFavoriteSong(favoriteMap, songCodeList, tableModel, 3));
        b3.addActionListener(new AddFavoriteSong(favoriteMap, songCodeList, tableModel, 3));

        //show list songs
        ShowButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println(favoriteMap.toString());
            }
        });





        mainPanel = new JPanel();
        setSize(300, 300);
        setLayout(new BorderLayout());


        for (int i = 0; i < favoriteMap.size(); i++) {
            System.out.println(favoriteMap.toString());
        }

        String[] columnNames = new String[]{"Code", "SongName", "Lyric", "Composer"};
        tableModel = new QuickKarTableModel(columnNames, 0, false);

        tableModel.addRow(columnNames);
        //tableModel.addRow(new Object[] {});
        for (int i = 0; i < favoriteMap.size(); i++) {
//            tableModel.addRow(songList);
        }
        table = new JTable(tableModel);
        table.getColumn("Code").setCellRenderer(myTabelCellRender);
        table.getColumn("SongName").setCellRenderer(myTabelCellRender);
        table.getColumn("Lyric").setCellRenderer(myTabelCellRender);
        table.getColumn("Composer").setCellRenderer(myTabelCellRender);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        mainPanel.add(table);
        mainPanel.add(b1);
        mainPanel.add(b2);
        mainPanel.add(b3);
        mainPanel.add(ShowButton);


        add(mainPanel);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.add(new FavoriteSongPanel());
        frame.validate();
        frame.setVisible(true);
    }
}
