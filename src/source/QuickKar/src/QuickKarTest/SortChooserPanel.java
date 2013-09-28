package QuickKarTest;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author s3342135
 */
public class SortChooserPanel extends JPanel {

    private JCheckBox chkboxSortType;
    private JRadioButton optionByCode;
    private JRadioButton optionByTitle;
    private JRadioButton optionByLyric;
    private JRadioButton optionByComposer;
    private JRadioButton optionByRating;
    private ButtonGroup radioGroup;
    private JPanel panelRadioBtn;

    public SortChooserPanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel panelChkbox = new JPanel();
        chkboxSortType = new JCheckBox("Sort");
        chkboxSortType.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (chkboxSortType.isSelected()) {
                    panelRadioBtn.setVisible(true);
                } else {
                    panelRadioBtn.setVisible(false);
                }
            }
        });
        JLabel wrapper = new JLabel("");
        panelChkbox.add(chkboxSortType);
        panelChkbox.add(wrapper);

        // This one for test layout only. Del after that.
        JPanel panelBtn = new JPanel();
        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        panelBtn.setLayout(new FlowLayout());
        panelBtn.add(btn1);
        panelBtn.add(btn2);
        //

        panelRadioBtn = new JPanel();
        optionByCode = new JRadioButton("by Code");
        optionByTitle = new JRadioButton("by Title");
        optionByLyric = new JRadioButton("by Lyric");
        optionByComposer = new JRadioButton("by Composer");
        optionByRating = new JRadioButton("by Rate");
        panelRadioBtn.add(optionByCode);
        panelRadioBtn.add(optionByTitle);
        panelRadioBtn.add(optionByLyric);
        panelRadioBtn.add(optionByComposer);
        panelRadioBtn.add(optionByRating);
        radioGroup = new ButtonGroup();
        radioGroup.add(optionByCode);
        radioGroup.add(optionByTitle);
        radioGroup.add(optionByLyric);
        radioGroup.add(optionByComposer);
        radioGroup.add(optionByRating);
        panelRadioBtn.setVisible(false);

        this.add(panelChkbox);
        // This one for test layout only. Del after that.
        this.add(panelRadioBtn);
        //
        this.add(panelBtn);

    }

    public static void main(String[] args) {
        JFrame m = new JFrame();
        m.add(new SortChooserPanel());
        m.setSize(500, 200);
//        m.pack();
//        m.setResizable(false);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setLocationRelativeTo(null);
        m.setVisible(true);
    }

}
