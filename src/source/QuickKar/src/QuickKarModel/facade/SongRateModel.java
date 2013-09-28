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

/**
 * Responsible for handling rating data.
 * 
 * @author vuongdothanhhuy
 */
public class SongRateModel implements Serializable {

    private int ratingStar;

    public SongRateModel(int ratingStar) {
        this.ratingStar = ratingStar;
    }

    public int getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(int ratingStar) {
        this.ratingStar = ratingStar;
    }
}
