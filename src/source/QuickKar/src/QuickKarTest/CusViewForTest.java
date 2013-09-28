/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package QuickKarTest;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Nop
 */
public class CusViewForTest extends JFrame{

    private InstantSearch instantSearch;

    CusViewForTest()
    {
        
        JPanel p1=new JPanel();
        JTextField searchField=new JTextField(20);
        setVisible(true);
        setSize(300,300);
        add(p1);
        p1.add(searchField);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        instantSearch = new InstantSearch();
        //searchField.addActionListener(new InstantSearch());
        searchField.addKeyListener(instantSearch);
//        searchField.addKeyListener(new KeyAdapter() {
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                super.keyPressed(e);
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                super.keyReleased(e);
//            }
//
//            @Override
//            public void keyTyped(KeyEvent e) {
//                super.keyTyped(e);
//                System.out.println("gdfd");
//            }
//
//
//        });
    }
    public static void main(String []args)
    {
       JFrame test=new CusViewForTest();
       
    }
}
