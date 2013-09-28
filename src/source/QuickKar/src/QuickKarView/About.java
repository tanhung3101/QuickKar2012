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

import QuickKarModel.facade.QuickKarModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the About dialog.
 *
 * @author Vuong Do Thanh Huy
 */
public class About extends JDialog {
    private QuickKarModel model;
    private ResourceBundle resources;

    public About() {


        super();
        model=QuickKarModel.getModel();
        resources=model.getResource();
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle(resources.getString("aboutTitle"));
        setModalityType(ModalityType.APPLICATION_MODAL);
        getAboutPanel();
        setSize(360, 440);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void getAboutPanel() {

        add(new JLabel(new ImageIcon("html\\Banner.png")), BorderLayout.CENTER);

        JPanel temp = new JPanel(new BorderLayout(10, 10));
        File fileHTML = new File("html\\about.html");
        String text = "";
        try {
            Scanner htmlInput = new Scanner(fileHTML);
            while (htmlInput.hasNext()) {
                text += htmlInput.nextLine() + "\n";
                text.trim();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        JLabel information = new JLabel(text);
        JButton closeButton = new JButton(resources.getString("closeButton"));

        // Users can close using ESC key or click Close button.
        closeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ///return to original
                dispose();
            }
        });
        closeButton.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        temp.add(information, BorderLayout.CENTER);
        temp.add(buttonPanel, BorderLayout.SOUTH);
        add(temp, BorderLayout.SOUTH);

        closeButton.requestFocusInWindow();

    }
}
