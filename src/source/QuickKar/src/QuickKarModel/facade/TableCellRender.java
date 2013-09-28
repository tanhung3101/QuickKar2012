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

import QuickKarInput.RatePanelForJComboBox;
import QuickKarInput.BoldWordsPanel;
import QuickKarInput.TopRatePanel;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This is customized renderer for table cell.
 *
 * Use to put star rating bar and bold word capability into jtable.
 *
 *  Used by JTable to custom render the table UI.
 *
 * @author vuongdothanhhuy
 */
public class TableCellRender extends DefaultTableCellRenderer {

    public static final int SONG_CODE_COLUMN = 0;
    public static final int RATING_COLUMN = 4;
    public static final int RATING_COLUMN_GUI_2 = 4;
    private String searchText;
    private String username;
    private TopRatePanel topRatePanel;

    public TableCellRender() {
        this.searchText = "";
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTopRatePanel(TopRatePanel topRatePanel) {
        this.topRatePanel = topRatePanel;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (column == RATING_COLUMN) {
            RatePanelForJComboBox panel = new RatePanelForJComboBox(username, (String) table.getValueAt(row, SONG_CODE_COLUMN));
            if (value != null) {
                panel.changeStar((Integer) value);
                if (topRatePanel != null) {
                    topRatePanel.updateTopRate();
                }
            }
            return panel;
        } else {
            BoldWordsPanel panel = new BoldWordsPanel((String) value);
            panel.setSearchText(searchText);
            return panel;
        }
    }
}
