package QuickKarModel.facade;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Colorvisa
 */
public class SongListSortFunction {

    public static final int SONG_CODE = 0;
    public static final int SONG_NAME = 1;
    public static final int SONG_LYRIC = 2;
    public static final int SONG_COMPOSER = 3;
    public static final int SONG_RATE = 4;

    public void sortBy(List<String> songList, int type, boolean descendingOrder) {
        Collections.sort(songList, new SongInfoComparator(descendingOrder, type));
    }

    private class SongInfoComparator implements Comparator<String> {

        private Map<String, SongInfo> songMap = QuickKarModel.getModel().getSongMap();
        private Map<String, RateModel> rateMap = QuickKarModel.getModel().getRateMap();
        private boolean descendingOrder;
        private int type;

        public SongInfoComparator(boolean reverseOrder, int type) {
            this.descendingOrder = reverseOrder;
            this.type = type;
        }

        private int compareString(String o1, String o2, boolean descendingOrder) {

            if (descendingOrder) {
                return -o1.compareTo(o2);
            } else {
                return o1.compareTo(o2);
            }
        }

        private int compareRate(String o1, String o2, boolean descendingOrder) {

            RateModel r1 = rateMap.get(o1);
            RateModel r2 = rateMap.get(o2);

            if (r1 != null || r2 != null) {

                int returnValue;

                if (r1 != null && r2 != null) {
                    returnValue = Double.compare(r1.averageRate(), r2.averageRate());
                } else if (r1 != null) {
                    returnValue = 1;
                } else {
                    returnValue = -1;
                }
                if (descendingOrder) {
                    return -returnValue;
                } else {
                    return returnValue;
                }
            } else {
                return 0;
            }
        }

        public int compare(String o1, String o2) {

            switch (type) {
                case SONG_NAME:
                    return compareString(songMap.get(o1).getName(), songMap.get(o2).getName(), descendingOrder);
                case SONG_LYRIC:
                    return compareString(songMap.get(o1).getLyric(), songMap.get(o2).getLyric(), descendingOrder);
                case SONG_COMPOSER:
                    return compareString(songMap.get(o1).getComposer(), songMap.get(o2).getComposer(), descendingOrder);
                case SONG_RATE:
                    return compareRate(o1, o2, descendingOrder);
                default:
                    return compareString(o1, o2, descendingOrder);
            }
        }
    }
}
