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

import QuickKarModel.facade.QuickKarMethod;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.util.Locale;
import javax.swing.JPanel;

/**
 * Responsible for bolding word when searching
 * For use with JTable
 * 
 * @author vuongdothanhhuy
 */
public class BoldWordsPanel extends JPanel {

    public static final int X = 5;
    public static final int Y = 20;
    private String text;
    private String searchText;
    private Font font;
    private Color normalColor;
    private Color highLightColor;

    public BoldWordsPanel(String text) {

        this.text = text;
        this.searchText = "";
        this.font = new Font("Arial", Font.PLAIN, 12);
        this.highLightColor = new Color(255, 0, 0);
    }

    /**
     * Auto find and check and choose available common fonts
     *  on Windows and Ubuntu/Linux system.
     * 
     * @return
     * @throws Exception
     */
    private String autoSetFont() throws Exception {
        boolean FIRST_FONT_AVAILABLE = false;
        boolean SECOND_FONT_AVAILABLE = false;
        boolean THIRD_FONT_AVAILABLE = false;
        boolean FORTH_FONT_AVAILABLE = false;

        // get list of fonts on system
        String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(Locale.ENGLISH);

        // check if any desired font is available
        for (int i = 0; i < fontList.length; i++) {
            if (fontList[i].equals("Arial")) {
                FIRST_FONT_AVAILABLE = true;
            }
            if (fontList[i].equals("Courier New")) {
                SECOND_FONT_AVAILABLE = true;
            }
            if (fontList[i].equals("Ubuntu")) {
                THIRD_FONT_AVAILABLE = true;
            }
            if (fontList[i].equals("Nimbus Sans L")) {
                FORTH_FONT_AVAILABLE = true;
            }
        }

        // return fontname, following priority.
        // if none is found, throw exception.
        if (FIRST_FONT_AVAILABLE) {
            return "Arial";
        } else if (SECOND_FONT_AVAILABLE) {
            return "Courier New";
        } else if (THIRD_FONT_AVAILABLE) {
            return "Ubuntu";
        } else if (FORTH_FONT_AVAILABLE) {
            return "Nimbus Sans L";
        } else {
            throw new Exception("No suitable font found!");
        }
    }

    /**
     * Trim all dangerous char and throw search string.
     * 
     * @param searchText
     */
    public void setSearchText(String searchText) {
        this.searchText = searchText.toLowerCase().replaceAll("\"", "");
        repaint();
    }

    private void checkArray(boolean[] checkWord, int from, int to) {
        //check array
        for (int i = from; i <= to; i++) {
            checkWord[i] = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        FontMetrics fontMetrics = g.getFontMetrics();
        normalColor = g.getColor();
        g.setFont(font);
        String tempText = text.toLowerCase();

        if (!searchText.isEmpty()) {
            String[] tag = QuickKarMethod.removeDuplicateWords(searchText);
            String textWithoutAccent = QuickKarMethod.stringProcess(tempText, false);
            boolean[] checkWord = new boolean[tempText.length()];

            for (int i = 0; i < tag.length; i++) {

                int indexOfTag = tempText.indexOf(tag[i]);
                int indexOfTagWithoutAccent = textWithoutAccent.indexOf(tag[i]);

                while (indexOfTag > -1) {
                    checkArray(checkWord, indexOfTag, indexOfTag + tag[i].length() - 1);
                    indexOfTag = tempText.indexOf(tag[i], indexOfTag + tag[i].length());
                }
                while (indexOfTagWithoutAccent > -1) {
                    checkArray(checkWord, indexOfTagWithoutAccent, indexOfTagWithoutAccent + tag[i].length() - 1);
                    indexOfTagWithoutAccent = textWithoutAccent.indexOf(tag[i], indexOfTagWithoutAccent + tag[i].length());
                }
            }
            int x = X;
            for (int i = 0; i < tempText.length(); i++) {

                if (checkWord[i]) {
                    g.setColor(highLightColor);
                } else {
                    g.setColor(normalColor);
                }

                String character = text.charAt(i) + "";

                g.drawString(character, x, Y);
                x += fontMetrics.stringWidth(character);
            }
        } else {
            g.drawString(text, X, Y);
        }
    }
}
