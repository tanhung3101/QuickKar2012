/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickKarTest;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Vuong Do Thanh Huy
 */
public class BoldTextJTable {

    final String TEXT_TO_HIGHLIGHT = "an";
    JTable table;
    String[] colName = {"ID", "SongName", "Lyric", "Composer"};
    String[][] rowData = {
        {"001", "TIA NANG HAT MUA", "Hinh nhu trong tung tia nang co net tinh nghich ban trai", "Lop 6"},
        {"023", "TRAI DAT NAY LA CUA CHUNG MINH", "Trai dat nay, la cua chung minh.", "Lop mau giao"},
        {"342", "NU CUOI", "Cho troi sang len, va ang may tuoi hong", "Lop 8"},
        {"456", "TUOI HONG", "Vui sao khi buoc tren duong nay, den truong than quen vui ngay ngay", "lop 9"},
        {"897", "TIENG CHUONG VA NGON CO", "Trai dat than yeu long chung em siet bao tu hao Mot qua cau dep tuoi lung linh giua troi sao.", "Lop 6"},
        {"234", "MAI TRUONG MEN YEU", "Oi hang cay xanh tham duoi mai truong men yeu", "lop 9"},
        {"745", "A LOVER'S CONCERTO", "How gentle is he now?", "Unknown"},
        {"254", "MUA", "Hat nang, hat nang, cho me ra dong.", "Lop 7"},};

    public static void main(String[] args) {
//        BoldTextJTable n = new BoldTextJTable();
        String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(Locale.ENGLISH);
        for (int i= 0; i < fontList.length; i++) {
            System.out.println(fontList[i]);
        }
    }

    public BoldTextJTable() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(rowData, colName) {

            @Override
            public Class getColumnClass(int col) {
                Object o = getValueAt(0, col);
                if (o == null) {
                    return Object.class;
                } else {
                    return o.getClass();
                }
            }
        };
        table = new JTable(model);
        table.setDefaultRenderer(String.class, new MyTableRenderer());
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScroll = new JScrollPane(table);

        JButton btn = new JButton("Highlight!");
        btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
//                search();
            }
        });

        panel.add(tableScroll, BorderLayout.CENTER);
        panel.add(btn, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class MyTableRenderer implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return new BoldWordClass((String) table.getValueAt(row, column), TEXT_TO_HIGHLIGHT);
        }
    }
}
