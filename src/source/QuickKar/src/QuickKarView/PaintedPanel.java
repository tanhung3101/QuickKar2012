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

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This abstract class is used as a framework to paint other self-painted panels.
 *
 * @author vuongdothanhhuy
 */
public class PaintedPanel extends JPanel {

    public Image image;

    public PaintedPanel(){}

    /**
     * Draw a panel with input image file and FlowLayout.
     *
     * @param fileName
     */
    public PaintedPanel(ImageIcon imageIcon) {
        this(new FlowLayout(), imageIcon);
    }

    /**
     * Draw a panel with input image file and input layout.
     * 
     * @param layout
     * @param fileName
     */
    public PaintedPanel(LayoutManager layout, ImageIcon imageIcon) {
        //this panel is used for paint the image
        setLayout(layout);
        image = imageIcon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
