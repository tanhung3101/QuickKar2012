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

/**
 * Detect and switch to another GUI
 * 
 * @author vuongdothanhhuy
 */
public class SwitchGUI {

    private int themeNo;

    public SwitchGUI(int themeNo) {
        this.themeNo = themeNo;
    }

    public int getThemeNo() {
        return themeNo;
    }

    public int switchThemeNo() {
        
        if (themeNo == 1) {
            themeNo = 2;
            return themeNo;
        } else {
            themeNo = 1;
            return themeNo;
        }
    }
}
