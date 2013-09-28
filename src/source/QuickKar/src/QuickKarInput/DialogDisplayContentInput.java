package QuickKarInput;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class DialogDisplayContentInput extends JDialog {

    public DialogDisplayContentInput(JPanel contentPanel) {

        super(null, JDialog.ModalityType.APPLICATION_MODAL);
        DialogDisplayContentInput.this.setTitle("Top rate");
        DialogDisplayContentInput.this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        DialogDisplayContentInput.this.add(contentPanel);
        DialogDisplayContentInput.this.setSize(200, 500);
        DialogDisplayContentInput.this.setLocationRelativeTo(null);
        DialogDisplayContentInput.this.setVisible(true);
    }
}
