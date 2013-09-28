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
import QuickKarController.SongInfoInterface;
import QuickKarView.PaintedPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Dialog appear when saving account data.
 *
 * @author vuongdothanhhuy
 */
public class SavingDatabaseDialog extends JDialog implements SongInfoInterface{

    public static final int ACCOUNT = 1;
    public static final int FAVORITE = 2;
    public static final int RATE = 3;
    public static final int SONG = 4;
    private int fileType;

    public SavingDatabaseDialog(int fileType) {

        setSize(300, 100);
        setResizable(false);
        setUndecorated(true);
        add(getLoadingPanel());
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.DOCUMENT_MODAL);
    }

    private JPanel getLoadingPanel() {

        PaintedPanel panel = new PaintedPanel(new GridBagLayout(), LOADING_PANEL);
        ImageIcon icon = LOAD1_1;
        JLabel label = new JLabel(icon);
        JLabel load = new JLabel("   SAVING...");
        load.setFont(new Font("Ravie", Font.BOLD, 20));
        load.setForeground(Color.WHITE);

        label.setOpaque(false);
        load.setOpaque(false);
        panel.add(label);
        panel.add(load);

        return panel;
    }

    public void disposeAfterSavingComplete() {

        switch (fileType) {
            case ACCOUNT:
                while (!SavingAndReadingController.isDoneSavingAccountDatabase()) {
                    System.out.println();
                }
                break;
            case FAVORITE:
                while (!SavingAndReadingController.isDoneSavingFavoriteDatabase()) {
                    System.out.println();
                }
                break;
            case RATE:
                while (!SavingAndReadingController.isDoneSavingRateDatabase()) {
                    System.out.println();
                }
                break;
            case SONG:
                while (!SavingAndReadingController.isDoneSavingSongDatabase()) {
                    System.out.println();
                }
                break;
        }
        SavingDatabaseDialog.this.dispose();
    }
}
