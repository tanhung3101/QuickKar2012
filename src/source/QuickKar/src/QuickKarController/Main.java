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
package QuickKarController;

import QuickKarModel.facade.AccountInfo;
import QuickKarModel.facade.QuickKarModel;
import QuickKarView.ChooseRoleView;
import java.awt.event.WindowEvent;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main extends JFrame {

    private static JPanel currentPanel;
    private static Main mainFrame;

    public Main() {

        currentPanel = new JPanel();
        setTitle("QuickKar 2012");
        setSize(1004, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        add(currentPanel);
        Map<String, AccountInfo> accountMap = QuickKarModel.getModel().getAccountMap();
        if (!accountMap.containsKey("admin")) {
            accountMap.put("admin", new AccountInfo("admin", "Default name", "s3342137@rmit.edu.vn", "090-123-4567", "Default information"));
        }
    }

    public static void changePanel(JPanel newPanel) {

        mainFrame.remove(currentPanel);
        currentPanel = newPanel;
        mainFrame.add(currentPanel);
        mainFrame.validate();
        System.gc();
    }

    public static Main getMainFrame() {
        return mainFrame;
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            SavingAndReadingController.saveRateMap();
            SavingAndReadingController.saveFavoriteMap();
            System.exit(0);
        }
    }

    public static void main(String[] args) {

        try {
            for (LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(laf.getName())) {
                    UIManager.setLookAndFeel(laf.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Cannot load the Nimbus theme!!!");
        }
        SavingAndReadingController.readFile();
        mainFrame = new Main();
        changePanel(new ChooseRoleView());
    }
}
