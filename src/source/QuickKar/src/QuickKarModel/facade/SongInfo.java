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
 * Responsible for handling song data
 * @author vuongdothanhhuy
 */
public class SongInfo implements Serializable {

    private String name;
    private String lyric;
    private String composer;

    public SongInfo(String name, String lyric, String composer) {
        this.name = name;
        this.lyric = lyric;
        this.composer = composer;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
