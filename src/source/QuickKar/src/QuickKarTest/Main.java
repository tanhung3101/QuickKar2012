package QuickKarTest;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author vuongdothanhhuy
 */
public class Main {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        f.add(p);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 300);
//        ChooseFileToImport.fileBrowse(p);
        final ContextMenu c = new ContextMenu();
        p.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent p) {
                if (p.isPopupTrigger()) {
                    c.show(p.getComponent(), p.getX(), p.getY());
                }
            }
        });
    }
}