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

import QuickKarModel.facade.QuickKarModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;
import java.util.Set;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the panel displaying top 10 songs.
 * 
 * @author vuongdothanhhuy
 */
public class TopRatePanel extends JPanel {

    private Font songCodeFont;
    private Font rateCountFont;
    private JPanel wrapPanel;

    public TopRatePanel() {

        TopRatePanel.this.setLayout(new BorderLayout());
        songCodeFont = new Font(Font.SERIF, Font.BOLD, 18);
        rateCountFont = new Font(Font.SERIF, Font.ITALIC, 10);
        initialization();
    }

    private void initialization() {
        wrapPanel = getTopRatePanel();
        wrapPanel.setOpaque(false);
        TopRatePanel.this.add(wrapPanel);
    }

    public void updateTopRate() {

        TopRatePanel.this.remove(wrapPanel);
        initialization();
        TopRatePanel.this.updateUI();
    }

    private JPanel getTopRatePanel() {

        Set<Map.Entry<String, Double>> topRate = QuickKarModel.getModel().getTopRate();

        JPanel contentPanel = new JPanel(new GridLayout(topRate.size(), 1));
        for (Map.Entry<String, Double> entry : topRate) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel.setOpaque(false);

            JLabel songCodeLabel = new JLabel(entry.getKey());
            songCodeLabel.setFont(songCodeFont);

            panel.add(songCodeLabel);
            panel.add(Box.createHorizontalGlue());
            panel.add(new RatePanelForJComboBox(entry.getValue()));

            JLabel rateCountLabel = new JLabel("[" + QuickKarModel.getModel().getSongRateCount(entry.getKey()) + "]");
            rateCountLabel.setFont(rateCountFont);

            panel.add(rateCountLabel);

            contentPanel.add(panel);
        }

        return contentPanel;
    }
}
