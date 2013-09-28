/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickKarTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author Nop
 */
public class InstantSearch extends KeyAdapter {

    private Timer newTimer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Search");
            newTimer.stop();
        }
    });

    @Override
    public void keyReleased(KeyEvent e) {

        newTimer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

        newTimer.stop();
    }
}
