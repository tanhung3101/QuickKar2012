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
package QuickKarModel.facade;

import javax.swing.table.DefaultTableModel;

public class QuickKarTableModel extends DefaultTableModel {

    public static final int RATING_COLUMN = 4;
    private boolean adminTableModel;

    public QuickKarTableModel(String[] columnNames, int rows, boolean adminTableModel) {
        super(columnNames, rows);
        this.adminTableModel = adminTableModel;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == RATING_COLUMN && !adminTableModel) {
            return true;
        } else {
            return false;
        }
    }
}
