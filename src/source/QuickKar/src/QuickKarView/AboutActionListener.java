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
package QuickKarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 * This is the ActionListener for opening About dialog.
 *
 * Used in AdminGUI.java, CustomerView.java and StaffView.java
 *
 * @author Vuong Do Thanh Huy
 */
public class AboutActionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        JDialog about = new About();
    }
}
