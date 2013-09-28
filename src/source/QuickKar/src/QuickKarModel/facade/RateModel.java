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
package QuickKarModel.facade;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Responsible for handing the "star" data.
 * 
 * @author vuongdothanhhuy
 */
public class RateModel implements Serializable {

    private DecimalFormat df = new DecimalFormat("#.#");
    private int[] array = new int[5];
    private int count = 0;

    public void increaseStar(int star) {
        array[star - 1]++;
        count++;
    }
    //method to return the value of index in the arrray
    public int getStartValue(int star){
        return array[star-1];
    }


    public void decreaseStar(int star) {
        array[star - 1]--;
        count--;
    }
    
    public int getCount() {
        return count;
    }

    public double averageRate() {

        double total = 0;

        for (int i = 0; i < array.length; i++) {
            total = array[i] * (i + 1) + total;
        }
        return Double.parseDouble(df.format(total / count));
    }
}
