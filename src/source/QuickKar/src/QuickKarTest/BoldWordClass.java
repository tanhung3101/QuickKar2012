package QuickKarTest;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author Vuong Do Thanh Huy
 */
public class BoldWordClass extends JLabel {

    public BoldWordClass(String text, String searchText) {
        String s = "<html>" + text;
        System.out.println(text);
        if (text.contains(searchText)) {
//            System.out.println("1");
            s = s.replace(searchText, "<b>" + searchText + "</b>");
        } else if (text.contains(searchText.toLowerCase())) {
//            System.out.println("2");
            s = s.replace(searchText.toLowerCase(), "<b>" + searchText.toLowerCase() + "</b>");
        } else {
            s = text;
        }
//        s = text.replace(searchText, "<html><b>" + searchText + "</b>");
//        s = text.replace(searchText.toLowerCase(), "<html><b>" + searchText + "</b>");
        setText(s);
//        setText(text);
        setFont(new Font("Arial", Font.PLAIN, 12));
    }
}
