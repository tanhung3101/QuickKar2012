
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Create and handle event for star bar rating system.
 *
 * Work much like star bar in Windows Media Player.
 *
 * @author vuongdothanhhuy
 */
public class RatePanelWithMouseAction extends JPanel implements SongInfoInterface{

    public static final int EMPTY_STAR = 0;
    public static final int FULL_STAR = 5;
    private JLabel[] stars;
    private RateStarListener listener;
    private int currentStar;
    private String username;
    private String songCode;

    public RatePanelWithMouseAction(String username, String songCode) {

        this.username = username;
        this.songCode = songCode;
        this.currentStar = 0;
        stars = new JLabel[5];
        listener = new RateStarListener();

        for (int i = 0; i < stars.length; i++) {
            stars[i] = new JLabel(EMPTY_STAR_ICON);
            stars[i].addMouseListener(listener);
            add(stars[i]);
        }

        int star = QuickKarModel.getModel().getStar(username, songCode);

        if (star != EMPTY_STAR) {
            setStatus(star);
            listener.setChosen(true);
        }
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

    private void addRateToMap(int star) {
        if (!songCode.isEmpty()) {
            QuickKarModel.getModel().staffAndCustomerRating(username, songCode, star);
        }
    }

    private class RateStarListener extends MouseAdapter {

        private boolean chosen;

        public void setChosen(boolean chosen) {
            this.chosen = chosen;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            for (int i = 0; i < stars.length; i++) {
                if (e.getSource() == stars[i]) {
                    currentStar = i + 1;
                    setStatus(currentStar);
                    addRateToMap(currentStar);
                    chosen = true;
                    break;
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

            if (!chosen) {
                for (int i = 0; i < stars.length; i++) {
                    if (e.getSource() == stars[i]) {
                        setStatus(i + 1);
                        break;
                    }
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {

            if (!chosen) {
                for (int i = 0; i < stars.length; i++) {
                    if (e.getSource() == stars[i]) {
                        setStatus(EMPTY_STAR);
                    }
                }
            }
        }
    }
}
