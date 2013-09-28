package QuickKarTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author vuongdothanhhuy
 */
public class ContextMenu extends JPopupMenu {

    private JMenuItem itemDelete = new JMenuItem("Delete");
    private JMenuItem itemEdit = new JMenuItem("Edit");

    public ContextMenu() {
        this.add(itemDelete);
        this.add(itemEdit);

        ActionListener a = new ItemAction();
        itemDelete.addActionListener(a);
        itemEdit.addActionListener(a);

    }

    private class ItemAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == itemDelete) {
                System.out.println("DELETE");
            } else if (e.getSource() == itemEdit) {
                System.out.println("EDIT");
            }
        }

    }
}
