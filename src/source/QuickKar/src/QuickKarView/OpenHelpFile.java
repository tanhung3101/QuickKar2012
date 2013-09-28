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

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This ActionListener open help file.
 *
 * @author vuongdothanhhuy
 */
public class OpenHelpFile implements ActionListener {

    public void actionPerformed(ActionEvent e) {

        /*
         * Different ways of accessing help files are used in a fail-over settings.
         * Because on some computer, browse() with URI does not work, but open() with file does.
         *
         * Note that, accroding to Java API, by using open(), .html file might be opened in an editor,
         *  rather than in a web broswer as expected.
         */

        try {
            Desktop.getDesktop().browse(new URI("userguide/index.html"));
        } catch (URISyntaxException ex) {
            System.out.println("URI Exception");
        } catch (IOException ex) {
            System.out.println("Cannot open by URI");
            try {
                Desktop.getDesktop().open(new File("userguide\\index.html"));
            } catch (IOException ex1) {
                System.out.println("Cannot open file");
                System.out.println("Trying another way...");
                try {
                    Desktop.getDesktop().open(new File("userguide/index.html"));
                } catch (IOException ex2) {
                    System.out.println("We give up! SORRY.");
                }
            }
        }
    }
}
