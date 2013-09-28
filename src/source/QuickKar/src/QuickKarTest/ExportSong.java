package QuickKarTest;

import QuickKarModel.facade.QuickKarModel;
import QuickKarModel.facade.SongInfo;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s3342135
 */
public class ExportSong {

    public static void main(String[] args) {
        // do what?
    }

    public static void export() {

        FileWriter f = null;
        Map<String, SongInfo> songMap = QuickKarModel.getModel().getSongMap();
        try {
            f = new FileWriter("/export.csv");

            for (Map.Entry<String, SongInfo> entry : songMap.entrySet()) {
                String songCode = entry.getKey();

                SongInfo songInfo = entry.getValue();
                String songName = songInfo.getName();
                String songLyric = songInfo.getLyric();
                String songComposer = songInfo.getComposer();

                f.write(songCode);
                f.write(",");
                f.write("\"");
                f.write(songName);
                f.write("\"");
                f.write(",");
                f.write("\"");
                f.write(songLyric);
                f.write("\"");
                f.write(",");
                f.write("\"");
                f.write(songComposer);
                f.write("\"");
                f.write("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(ExportSong.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (f != null) {
                try {
                    f.close();
                } catch (IOException ex) {
                    Logger.getLogger(ExportSong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
