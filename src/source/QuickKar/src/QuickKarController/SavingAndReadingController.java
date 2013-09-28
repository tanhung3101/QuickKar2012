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
package QuickKarController;

import QuickKarInput.ReadingDatabaseDialog;
import QuickKarInput.SavingDatabaseDialog;
import QuickKarModel.facade.*;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class SavingAndReadingController {

    private static ExecutorService threadPool = ThreadController.getThreadPool();
    private static QuickKarModel model = QuickKarModel.getModel();
    private static boolean readAccountDatabase;
    private static boolean readSongMapDatabase;
    private static boolean readTagMapDatabase;
    private static boolean readTagMapWithoutAccentDatabase;
    private static boolean readRateMap;
    private static boolean readFavoriteSongMap;
    private static boolean readStaffAndCustomerRateMap;
    private static boolean saveAccountDatabase;
    private static boolean saveSongMapDatabase;
    private static boolean saveTagMapDatabase;
    private static boolean saveTagMapWithoutAccentDatabase;
    private static boolean saveRateMap;
    private static boolean saveFavoriteSongMap;
    private static boolean saveStaffAndCustomerRateMap;

    public static void readFile() {

        ReadingDatabaseDialog loadingScreen = new ReadingDatabaseDialog();
        readAccountDatabase = false;
        threadPool.execute(new Runnable() {

            public void run() {
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("database/account_database.dat"));
                    model.setAccountMap((Map<String, AccountInfo>) inputStream.readObject());
                } catch (IOException ex) {
                } catch (ClassNotFoundException ex) {
                } finally {
                    readAccountDatabase = true;
                }
            }
        });
        readSongMapDatabase = false;
        threadPool.execute(new Runnable() {

            public void run() {
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("database/song_database.dat"));
                    model.setSongMap((Map<String, SongInfo>) inputStream.readObject());
                } catch (IOException ex) {
                } catch (ClassNotFoundException ex) {
                } finally {
                    readSongMapDatabase = true;
                }
            }
        });
        readTagMapDatabase = false;
        threadPool.execute(new Runnable() {

            public void run() {
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("database/tag_database.dat"));
                    model.setTagMap((Map<String, HashSet<String>>) inputStream.readObject());
                } catch (IOException ex) {
                } catch (ClassNotFoundException ex) {
                } finally {
                    readTagMapDatabase = true;
                }
            }
        });
        readTagMapWithoutAccentDatabase = false;
        threadPool.execute(new Runnable() {

            public void run() {
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("database/tag_without_accent_database.dat"));
                    model.setTagMapWithoutAccent((Map<String, HashSet<String>>) inputStream.readObject());
                } catch (IOException ex) {
                } catch (ClassNotFoundException ex) {
                } finally {
                    readTagMapWithoutAccentDatabase = true;
                }
            }
        });
        readRateMap = false;
        threadPool.execute(new Runnable() {

            public void run() {
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("database/rate_map_database.dat"));
                    model.setRateMap((Map<String, RateModel>) inputStream.readObject());
                } catch (IOException ex) {
                } catch (ClassNotFoundException ex) {
                } finally {
                    readRateMap = true;
                }
            }
        });
        readFavoriteSongMap = false;
        threadPool.execute(new Runnable() {

            public void run() {
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("database/favorite_song_database.dat"));
                    model.setFavoriteSongMap((Map<String, HashSet<String>>) inputStream.readObject());
                } catch (IOException ex) {
                } catch (ClassNotFoundException ex) {
                } finally {
                    readFavoriteSongMap = true;
                }
            }
        });
        readStaffAndCustomerRateMap = false;
        threadPool.execute(new Runnable() {

            public void run() {
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("database/staff_customer_database.dat"));
                    model.setStaffAndCustomerRateMap((Map<String, HashMap<String, SongRateModel>>) inputStream.readObject());
                } catch (IOException ex) {
                } catch (ClassNotFoundException ex) {
                } finally {
                    readStaffAndCustomerRateMap = true;
                }
            }
        });
        loadingScreen.disposeAfterReadingComplete();
    }

    public static void saveAccount() {

        SavingDatabaseDialog savingAccountPanel = new SavingDatabaseDialog(SavingDatabaseDialog.ACCOUNT);
        saveAccountDatabase = false;
        threadPool.execute(new Runnable() {

            public void run() {
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("database/account_database.dat"));
                    outputStream.writeObject(QuickKarModel.getModel().getAccountMap());
                } catch (IOException ex) {
                } finally {
                    saveAccountDatabase = true;
                }
            }
        });
        savingAccountPanel.disposeAfterSavingComplete();
    }

    public static void saveSong() {

        SavingDatabaseDialog savingSongPanel = new SavingDatabaseDialog(SavingDatabaseDialog.SONG);
        saveSongMapDatabase = false;
        threadPool.execute(new Runnable() {
            //write to song_database.dat

            public void run() {
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("database/song_database.dat"));
                    outputStream.writeObject(QuickKarModel.getModel().getSongMap());
                } catch (IOException ex) {
                } finally {
                    saveSongMapDatabase = true;
                }
            }
        });
        saveTagMapDatabase = false;
        threadPool.execute(new Runnable() {
            //write to tag_database.dat

            public void run() {
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("database/tag_database.dat"));
                    outputStream.writeObject(QuickKarModel.getModel().getTagMap());
                } catch (IOException ex) {
                } finally {
                    saveTagMapDatabase = true;
                }
            }
        });
        saveTagMapWithoutAccentDatabase = false;
        threadPool.execute(new Runnable() {
            //write to tag_database.dat

            public void run() {
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("database/tag_without_accent_database.dat"));
                    outputStream.writeObject(QuickKarModel.getModel().getTagMapWithoutAccent());
                } catch (IOException ex) {
                } finally {
                    saveTagMapWithoutAccentDatabase = true;
                }
            }
        });
        savingSongPanel.disposeAfterSavingComplete();
    }

    public static void saveRateMap() {

        SavingDatabaseDialog savingRateDialog = new SavingDatabaseDialog(SavingDatabaseDialog.RATE);
        saveRateMap = false;
        threadPool.execute(new Runnable() {
            //write to song_database.dat

            public void run() {
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("database/rate_map_database.dat"));
                    outputStream.writeObject(QuickKarModel.getModel().getRateMap());
                } catch (IOException ex) {
                } finally {
                    saveRateMap = true;
                }
            }
        });
        saveStaffAndCustomerRateMap = false;
        threadPool.execute(new Runnable() {
            //write to song_database.dat

            public void run() {
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("database/staff_customer_database.dat"));
                    outputStream.writeObject(QuickKarModel.getModel().getStaffAndCustomerRateMap());
                } catch (IOException ex) {
                } finally {
                    saveStaffAndCustomerRateMap = true;
                }
            }
        });
        savingRateDialog.disposeAfterSavingComplete();
    }

    public static void saveFavoriteMap() {

        SavingDatabaseDialog savingFavoriteDialog = new SavingDatabaseDialog(SavingDatabaseDialog.FAVORITE);
        saveFavoriteSongMap = false;
        threadPool.execute(new Runnable() {

            public void run() {
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("database/favorite_song_database.dat"));
                    outputStream.writeObject(QuickKarModel.getModel().getFavoriteSongMap());
                } catch (IOException ex) {
                } finally {
                    saveFavoriteSongMap = true;
                }
            }
        });
        savingFavoriteDialog.disposeAfterSavingComplete();
    }

    public static boolean isDoneReadingDatabase() {
        return readAccountDatabase && readSongMapDatabase && readTagMapDatabase && readFavoriteSongMap
                && readTagMapWithoutAccentDatabase && readRateMap && readSongMapDatabase && readStaffAndCustomerRateMap;
    }

    public static boolean isDoneSavingAccountDatabase() {
        return saveAccountDatabase;
    }

    public static boolean isDoneSavingSongDatabase() {
        return saveSongMapDatabase && saveTagMapDatabase && saveTagMapWithoutAccentDatabase;
    }

    public static boolean isDoneSavingRateDatabase() {
        return saveRateMap && saveStaffAndCustomerRateMap;
    }

    public static boolean isDoneSavingFavoriteDatabase() {
        return saveFavoriteSongMap;
    }
}
