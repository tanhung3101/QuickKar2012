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

import QuickKarController.SongInfoInterface;
import QuickKarModel.facade.QuickKarModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This is star bar in combobox. For use in JTable.
 * 
 * @author vuongdothanhhuy
 */
public class RatePanelForJComboBox extends JPanel implements SongInfoInterface {

    public static final int EMPTY_STAR = 0;
    public static final int FULL_STAR = 5;
    private JLabel[] stars;
    private int currentStar;
    private String username;
    private String songCode;

    public RatePanelForJComboBox(String username, String songCode) {

        this.username = username;
        this.songCode = songCode;
        this.currentStar = 0;
        stars = new JLabel[5];

        for (int i = 0; i < stars.length; i++) {
            stars[i] = new JLabel(EMPTY_STAR_ICON);
            add(stars[i]);
        }

        int star = QuickKarModel.getModel().getStar(username, songCode);

        if (star != EMPTY_STAR) {
            setStatus(star);
        }
    }

    public RatePanelForJComboBox(double status) {

        stars = new JLabel[5];

        for (int i = 0; i < stars.length; i++) {
            stars[i] = new JLabel();
            add(stars[i]);
        }
        setAverageStatus(status);
    }

    private void setStatus(int star) {

        int index = star - 1;

        for (int i = 0; i <= index; i++) {
            stars[i].setIcon(GREEN_STAR_ICON);
        }
        for (int i = index + 1; i < stars.length; i++) {
            stars[i].setIcon(EMPTY_STAR_ICON);
        }
    }

    private void setAverageStatus(double star) {

        double starDivide = star - Math.floor(star);
        int middleIndex = (int) Math.floor(star - 1);

        for (int i = 0; i <= middleIndex; i++) {
            stars[i].setIcon(GREEN_STAR_ICON);
        }
        if (middleIndex + 1 < 5) {
            if (starDivide < 0.1) {
                stars[middleIndex + 1].setIcon(EMPTY_STAR_ICON);
            } else if (0.1 <= starDivide && starDivide <= 0.3) {
                stars[middleIndex + 1].setIcon(ONE_THIRDS_STAR);
            } else if (0.4 <= starDivide && starDivide <= 0.6) {
                stars[middleIndex + 1].setIcon(HALF_STAR);
            } else if (0.7 <= starDivide && starDivide <= 0.9) {
                stars[middleIndex + 1].setIcon(TWO_THIRDS_STAR);
            }
            for (int i = middleIndex + 2; i < stars.length; i++) {
                stars[i].setIcon(EMPTY_STAR_ICON);
            }
        }
    }

    public void changeStar(int star) {
        currentStar = star;
        setStatus(currentStar);
        QuickKarModel.getModel().staffAndCustomerRating(username, songCode, currentStar);
    }
}
