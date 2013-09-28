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
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Waiting dialog appear when importing data from database.
 * 
 * @author vuongdothanhhuy
 */
public class ReadingDatabaseDialog extends JDialog implements SongInfoInterface{

    public ReadingDatabaseDialog() {

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

        PaintedPanel panel = new PaintedPanel(new GridBagLayout(),LOADING_PANEL );
        ImageIcon icon = LOAD1_1;
        JLabel label = new JLabel(icon);
        JLabel load = new JLabel("   LOADING...");
        load.setFont(new Font("Ravie", Font.BOLD, 20));
        load.setForeground(Color.WHITE);

        label.setOpaque(false);
        load.setOpaque(false);
        panel.add(label);
        panel.add(load);

        return panel;
    }

    public void disposeAfterReadingComplete() {
        while (!SavingAndReadingController.isDoneReadingDatabase()) {
        }
        dispose();
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            disposeAfterReadingComplete();
        }
    }
}
