package QuickKarTest;

import QuickKarController.SongInfoInterface;
import QuickKarModel.facade.QuickKarModel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RatePanel extends JPanel implements SongInfoInterface{

    public static final int EMPTY_STAR = 0;
    public static final int FULL_STAR = 5;
    private JLabel[] stars;
    private RateStarListener listener;
    private int currentStar;
    private String username;
    private String songCode;

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(5, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.add(new RatePanel(3.5));
        frame.add(new RatePanel(3.0));
        frame.add(new RatePanel(3.2));
        frame.add(new RatePanel(3.8));
        frame.add(new RatePanel(3.9));
        frame.add(new RatePanel(4.0));
        frame.add(new RatePanel(4.8));
        frame.add(new RatePanel(0.0));
        frame.validate();
        frame.setVisible(true);
    }

    public RatePanel(String username, String songCode) {

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

    public RatePanel(double status) {

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
        int middleIndex = (int) Math.floor(star) - 1;

        for (int i = 0; i <= middleIndex; i++) {
            stars[i].setIcon(GREEN_STAR_ICON);
        }
        if (0 <= starDivide && starDivide < 0.3) {
            stars[middleIndex + 1].setIcon(ONE_THIRDS_STAR);
        } else if (0.4 <= starDivide && starDivide < 0.7) {
            stars[middleIndex + 1].setIcon(HALF_STAR);
        } else {
            stars[middleIndex + 1].setIcon(TWO_THIRDS_STAR);
        }
        for (int i = middleIndex + 2; i < stars.length; i++) {
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
