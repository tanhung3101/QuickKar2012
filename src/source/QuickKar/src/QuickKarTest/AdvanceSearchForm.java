/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author s3255203
 */
public class AdvanceSearchForm extends JFrame {

    JCheckBox chkboxAdvSearch;
    JRadioButton option1;
    JRadioButton option2;
    ButtonGroup radioGroup;
    JPanel panelRadioBtn;

    public AdvanceSearchForm() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel panelChkbox = new JPanel();
        chkboxAdvSearch = new JCheckBox("Advance Search");
        chkboxAdvSearch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (chkboxAdvSearch.isSelected()) {
                    panelRadioBtn.setVisible(true);
                } else {
                    panelRadioBtn.setVisible(false);
                }
            }
        });
        JLabel wrapper = new JLabel("");
        panelChkbox.add(chkboxAdvSearch);
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
        option1 = new JRadioButton("any of the word");
        option2 = new JRadioButton("none of the word");
        panelRadioBtn.add(option1);
        panelRadioBtn.add(option2);
        radioGroup = new ButtonGroup();
        radioGroup.add(option1);
        radioGroup.add(option2);
        panelRadioBtn.setVisible(false);

        this.add(panelChkbox);
        // This one for test layout only. Del after that.
        this.add(panelRadioBtn);
        //
        this.add(panelBtn);

    }

    public static void main(String[] args) {
        JFrame m = new AdvanceSearchForm();
        m.setSize(300, 200);
//        m.pack();
//        m.setResizable(false);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setLocationRelativeTo(null);
        m.setVisible(true);
    }
}
