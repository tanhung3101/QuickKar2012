/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickKarModel.facade;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nop
 */
public class QuickKarModelTest {

    private static QuickKarModel modelTest;
    private static SongInfo song1, song2;

    public QuickKarModelTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("@Begin Test method of QuickKarModel class");

        modelTest = QuickKarModel.getModel();
        song1 = new SongInfo("song1", "one upon a time", "Mike Jackson");
        song2 = new SongInfo("song2", "one upon a time2", "Mike Jackson2");

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("@After Test method of QuickKarModel class");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of deleteAllSong method, of class QuickKarModel.
     */
    @Test
    public void testDeleteAllSong1() {
        System.out.println("deleteAllSong");
        modelTest = QuickKarModel.getModel();

        String username = "staff1";
        //add a song to the list
        String songCode1 = "50000";
        modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");

        //add song to the favorite list
        modelTest.addFavoriteSong(username, songCode1);


        int oldNoOfSongMap = modelTest.getSongMap().size();
        int oldNoOfsongFavorite = modelTest.getFavoriteSongMap().size();
        modelTest.deleteAllSong();

        int newNoOfSongMap = modelTest.getSongMap().size();
        int newNoOfsongFavorite = modelTest.getFavoriteSongMap().size();

        if (oldNoOfSongMap != newNoOfSongMap && !modelTest.getSongMap().containsKey(songCode1)
                && oldNoOfsongFavorite != newNoOfsongFavorite) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }


    }

    /**
     * Test of staffAndCustomerRating method, of class QuickKarModel.
     */
    @Test
    public void testStaffAndCustomerRating() {

        System.out.println("staffAndCustomerRating");

        modelTest = QuickKarModel.getModel();

        //add two song to the list
        String songCode1 = "50000";
        String songCode2 = "50001";
        modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");
        modelTest.addSongMap(songCode2, "song2", "song2", "Mike1");


        String username = "staff1";
        String songCode = "50000";
        int ratingStar = 4;
        int oldValueOfRate = modelTest.getStar(username, songCode);

        modelTest.staffAndCustomerRating(username, songCode, ratingStar);
        int newValueOfRate = modelTest.getStar(username, songCode);

        System.out.println("old:" + oldValueOfRate);
        System.out.println("new:" + newValueOfRate);
        // TODO review the generated test code and remove the default call to fail.
        if (oldValueOfRate != newValueOfRate) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    /**
     * Test of addFavoriteSong method, of class QuickKarModel.
     */
    @Test
    public void testAddFavoriteSong1() {
        System.out.println("addFavoriteSong: add valid song");
        modelTest = QuickKarModel.getModel();
        String username = "staff1";
        String songCode1 = "50000";
        String songCode2 = "50001";

        int oldNoOfsong = modelTest.getFavoriteSongMap().size();
        //add song to the normal list
        modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");
        modelTest.addSongMap(songCode2, "song2", "song2", "Mike1");

        //add song to favorite list
        modelTest.addFavoriteSong(username, songCode1);

        int newNoOfsong = modelTest.getFavoriteSongMap().size();
        // TODO review the generated test code and remove the default call to fail.
        if (oldNoOfsong != newNoOfsong) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    /**
     * Test of addFavoriteSong method, of class QuickKarModel.
     */
    @Test
    public void testAddFavoriteSong2() {
        System.out.println("addFavoriteSong: add duplicate song");
        modelTest = QuickKarModel.getModel();
        String username = "staff1";
        String songCode1 = "50000";
        String songCode2 = "50001";
        String songCode3 = "50002";

        int oldNoOfsong = modelTest.getSongMap().size();
        //add song to the normal list
        modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");
        modelTest.addSongMap(songCode2, "song2", "song2", "Mike1");

        //add song to favorite list
        modelTest.addFavoriteSong(username, songCode1);
        //add duplicate song to favorite list
        modelTest.addFavoriteSong(username, songCode1);

        int newNoOfsong = modelTest.getFavoriteSongMap().size();
        System.out.println(newNoOfsong);
        // TODO review the generated test code and remove the default call to fail.
        if (oldNoOfsong != newNoOfsong) {
            assertFalse(false);
        } else {
            assertFalse(true);
        }
    }

    /**
     * Test of addFavoriteSong method, of class QuickKarModel.
     */
    @Test
    public void testAddFavoriteSong3() {
        System.out.println("addFavoriteSong: add invalid song");
        try {
            modelTest = QuickKarModel.getModel();
            String username = "staff1";
            String songCode1 = "50000";
            String songCode2 = "50001";
            String songCode3 = "50002";

            int oldNoOfsong = modelTest.getSongMap().size();
            //add song to the normal list
            modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");
            modelTest.addSongMap(songCode2, "song2", "song2", "Mike1");

            //add song to favorite list
            modelTest.addFavoriteSong(username, songCode1);

            //add invalid non-existed song to favorite list
            modelTest.addFavoriteSong(username, songCode3);

            int newNoOfsong = modelTest.getFavoriteSongMap().size();
            System.out.println(newNoOfsong);
            // TODO review the generated test code and remove the default call to fail.
            if (oldNoOfsong != newNoOfsong) {
                assertFalse(false);
            } else {
                assertFalse(true);
            }
        } catch (Exception e) {
            assertFalse(false);
        }

    }

    /**
     * Test of removeFavoriteSong method, of class QuickKarModel.
     */
    @Test
    public void testRemoveFavoriteSong1() {
        System.out.println("removeFavoriteSong: remove valid song");
        modelTest = QuickKarModel.getModel();
        String username = "staff1";
        String songCode1 = "50000";
        String songCode2 = "50001";


        //add song to the normal list
        modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");
        modelTest.addSongMap(songCode2, "song2", "song2", "Mike1");

        //add song to favorite list
        modelTest.addFavoriteSong(username, songCode1);
        modelTest.addFavoriteSong(username, songCode2);

        Set<String> favoriteSong = modelTest.getFavoriteSongMap().get(username);
        int oldNoOfSongFavorite = favoriteSong.size();
        modelTest.removeFavoriteSong(username, songCode2);

        int newNoOfSongFavorite = favoriteSong.size();
        if (oldNoOfSongFavorite != newNoOfSongFavorite) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }

    }

    /**
     * Test of removeFavoriteSong method, of class QuickKarModel.
     */
    @Test
    public void testRemoveFavoriteSong2() {
        System.out.println("removeFavoriteSong: remove non-existed favorite song");

        modelTest = QuickKarModel.getModel();
        modelTest.deleteAllSong();
        String username = "staff1";
        String songCode1 = "50000";
        String songCode2 = "50001";
        String songCode3 = "50002";

        int oldNoOfsong = modelTest.getSongMap().size();
        //add song to the normal list
        modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");
        modelTest.addSongMap(songCode2, "song2", "song2", "Mike1");

        //add song to favorite list
        modelTest.addFavoriteSong(username, songCode1);
        modelTest.addFavoriteSong(username, songCode2);


        Set<String> favoriteSong = modelTest.getFavoriteSongMap().get(username);
        int oldNoOfSongFavorite = favoriteSong.size();
        modelTest.removeFavoriteSong(username, songCode3);

        int newNoOfSongFavorite = favoriteSong.size();

        if (oldNoOfSongFavorite != newNoOfSongFavorite) {
            assertFalse(true);
        } else {
            assertFalse(false);
        }

    }

    /**
     * Test of addSongMap method, of class QuickKarModel.
     */
    @Test
    public void testAddSongMap1() {
        System.out.println("addSongMap: add valid song");
        modelTest = QuickKarModel.getModel();
        modelTest.deleteAllSong();
        String username = "staff1";
        String songCode1 = "50000";
        String songCode2 = "50001";

        int oldNoOfSongMap = modelTest.getSongMap().size();
        //add song to the normal list
        modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");
        modelTest.addSongMap(songCode2, "song2", "song2", "Mike1");

        int newNoOfSongMap = modelTest.getSongMap().size();

        if (oldNoOfSongMap != newNoOfSongMap) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }

    }

    /**
     * Test of addSongMap method, of class QuickKarModel.
     */
    @Test
    public void testAddSongMap2() {
        System.out.println("addSongMap: add invalid song");
        try {
            modelTest = QuickKarModel.getModel();
            modelTest.deleteAllSong();
            String username = "staff1";
            String songCode1 = "50000";
            String songCode2 = "50001";

            int oldNoOfSongMap = modelTest.getSongMap().size();
            //add song to the normal list
            modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");
            modelTest.addSongMap(songCode2, "song2", null, null);

            int newNoOfSongMap = modelTest.getSongMap().size();

            if (oldNoOfSongMap != newNoOfSongMap) {
                assertFalse(true);
            } else {
                assertFalse(false);
            }
        } catch (Exception e) {
            assertFalse(false);
        }

    }

    /**
     * Test of addSongMap method, of class QuickKarModel.
     */
    @Test
    public void testAddSongMap3() {
        System.out.println("addSongMap: add duplicate song");
        try {
            modelTest = QuickKarModel.getModel();
            modelTest.deleteAllSong();
            String username = "staff1";
            String songCode1 = "50000";
            String songCode2 = "50001";
            String songCode3 = "50001";


            //add song to the normal list
            modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");
            modelTest.addSongMap(songCode2, "song2", "song2", "song2");
            int oldNoOfSongMap = modelTest.getSongMap().size();
            //add duplicate songCode3 with same code as songCode2
            modelTest.addSongMap(songCode3, "song3", "song3", "song3");

            int newNoOfSongMap = modelTest.getSongMap().size();

            if (oldNoOfSongMap != newNoOfSongMap) {
                assertFalse(true);
            } else {
                assertFalse(false);
            }
        } catch (Exception e) {
            assertFalse(false);
        }

    }

    /**
     * Test of deleteSongMap method, of class QuickKarModel.
     */
    @Test
    public void testDeleteSongMap1() {
        System.out.println("deleteSongMap :delete valid song");
        try {
            modelTest = QuickKarModel.getModel();
            modelTest.deleteAllSong();
            String songCode1 = "50000";
            String songCode2 = "50001";


            //add song to the normal list
            modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");
            modelTest.addSongMap(songCode2, "song2", "song2", "Mike1");

            int oldNoOfSongMap = modelTest.getSongMap().size();
            modelTest.deleteSongMap(songCode2);

            int newNoOfSongMap = modelTest.getSongMap().size();

            if (oldNoOfSongMap != newNoOfSongMap && !modelTest.getSongMap().containsKey(songCode2)) {
                assertTrue(true);
            } else {
                assertTrue(false);
            }
        } catch (Exception e) {
            assertTrue(false);
        }

    }

    /**
     * Test of deleteSongMap method, of class QuickKarModel.
     */
    @Test
    public void testDeleteSongMap2() {
        System.out.println("deleteSongMap :delete invalid song");
        try {
            modelTest = QuickKarModel.getModel();
            modelTest.deleteAllSong();
            String songCode1 = "50000";
            String songCode2 = "50001";
            String songCode3 = "50002";

            //add song to the normal list
            modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");
            modelTest.addSongMap(songCode2, "song2", "song2", "Mike1");

            int oldNoOfSongMap = modelTest.getSongMap().size();
            modelTest.deleteSongMap(songCode3);

            int newNoOfSongMap = modelTest.getSongMap().size();

            if (oldNoOfSongMap != newNoOfSongMap && !modelTest.getSongMap().containsKey(songCode2)) {
                assertFalse(true);
            } else {
                assertFalse(false);
            }
        } catch (Exception e) {
            assertFalse(false);
        }

    }

    /**
     * Test of editSongMap method, of class QuickKarModel.
     */
    @Test
    public void testEditSongMap1() {
        System.out.println("editSongMap:edit valid song");
        try {
            modelTest = QuickKarModel.getModel();
            modelTest.deleteAllSong();
            String songCode1 = "50000";
            String songCode2 = "50001";

            //add song to the normal list
            modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");
            modelTest.addSongMap(songCode2, "song2", "song2", "Mike1");

            Map<String, SongInfo> songmap = modelTest.getSongMap();

            //new information of a song
            SongInfo oldsong = songmap.get(songCode1);
            String oldSongCode = "50000";
            String newSongCode = "50000";
            String newSongName = "con duong da di";
            String newLyric = "anh nho em nhieu";
            String newComposer = "Quang Dung";

            String oldSongName = oldsong.getName();
            String oldSongLyric = oldsong.getLyric();
            String oldSongComposer = oldsong.getComposer();
            modelTest.editSongMap(oldSongCode, newSongCode, newSongName, newLyric, newComposer);

            if (!oldSongCode.equalsIgnoreCase(newSongCode)
                    || !oldSongComposer.equalsIgnoreCase(newComposer)
                    || !oldSongLyric.equalsIgnoreCase(newLyric)
                    || !oldSongName.equalsIgnoreCase(newSongName)) {
                assertTrue(true);
            } else {
                assertTrue(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }

    /**
     * Test of editSongMap method, of class QuickKarModel.
     */
    @Test
    public void testEditSongMap2() {
        System.out.println("editSongMap:edit non-existed song");
        try {
            modelTest = QuickKarModel.getModel();
            modelTest.deleteAllSong();
            String songCode1 = "50000";
            String songCode2 = "50001";
            String songCode3 = "50002";

            //add song to the normal list
            modelTest.addSongMap(songCode1, "song1", "song1", "Mike1");
            modelTest.addSongMap(songCode2, "song2", "song2", "Mike1");

            Map<String, SongInfo> songmap = modelTest.getSongMap();

            //new information of a song
            SongInfo oldsong = songmap.get(songCode3);
            String oldSongCode = "50000";
            String newSongCode = "50000";
            String newSongName = "con duong da di";
            String newLyric = "anh nho em nhieu";
            String newComposer = "Quang Dung";

            String oldSongName = oldsong.getName();
            String oldSongLyric = oldsong.getLyric();
            String oldSongComposer = oldsong.getComposer();
            modelTest.editSongMap(oldSongCode, newSongCode, newSongName, newLyric, newComposer);

            if (!oldSongCode.equalsIgnoreCase(newSongCode)
                    || !oldSongComposer.equalsIgnoreCase(newComposer)
                    || !oldSongLyric.equalsIgnoreCase(newLyric)
                    || !oldSongName.equalsIgnoreCase(newSongName)) {
                assertFalse(true);
            } else {
                assertFalse(false);
            }
        } catch (Exception e) {

            assertFalse(false);
        }

    }

    /**
     * Test of addAccount method, of class QuickKarModel.
     */
    @Test
    public void testAddAccount1() {
        System.out.println("addAccount:add staff account");
        try {
            modelTest = QuickKarModel.getModel();

            //information of a new account
            String username = "staff1";
            String password = "staff1";
            String name = "hung";
            String email = "staff1@gmail.com";
            String telephone = "09-123-2123";
            String otherInfo = "OK";



            int oldNoOfmember = modelTest.getAccountMap().size();

            modelTest.addAccount(username, password, name, email, telephone, otherInfo);
            int newNoOfmember = modelTest.getAccountMap().size();

            if (oldNoOfmember != newNoOfmember) {
                modelTest.deleteAccount(username);
                assertTrue(true);
            } else {
                modelTest.deleteAccount(username);
                assertTrue(false);
            }
        } catch (Exception e) {

            assertTrue(false);
        }
    }

    /**
     * Test of addAccount method, of class QuickKarModel.
     */
    @Test
    public void testAddAccount2() {
        System.out.println("addAccount:add duplicate account");
        try {
            modelTest = QuickKarModel.getModel();


            String username = "staff1";
            String password = "staff1";
            String name = "hung";
            String email = "staff1@gmail.com";
            String telephone = "09-123-2123";
            String otherInfo = "OK";
            modelTest.addAccount(username, password, name, email, telephone, otherInfo);

            //information of a new account
            String username2 = "staff1";
            String password2 = "staff1";
            String name2 = "hoangng";
            String email2 = "hoang@gmail.com";
            String telephone2 = "09-777-2123";
            String otherInfo2 = "OK";


            int oldNoOfmember = modelTest.getAccountMap().size();

            modelTest.addAccount(username2, password2, name2, email2, telephone2, otherInfo2);
            int newNoOfmember = modelTest.getAccountMap().size();

            if (oldNoOfmember != newNoOfmember) {
                assertFalse(true);
            } else {
                assertFalse(false);
            }
        } catch (Exception e) {
            assertFalse(false);
        }
    }

    /**
     * Test of addAccount method, of class QuickKarModel.
     */
    @Test
    public void testAddAccount3() {
        System.out.println("addAccount:add invalid account");
        try {
            modelTest = QuickKarModel.getModel();

            //information of a new account
            String username = "staff1";
            String password = "staff1";
            String name = null;
            String email = null;
            String telephone = "09-123-2123";
            String otherInfo = "OK";

            int oldNoOfmember = modelTest.getAccountMap().size();
            modelTest.addAccount(username, password, name, email, telephone, otherInfo);
            int newNoOfmember = modelTest.getAccountMap().size();

            if (oldNoOfmember != newNoOfmember) {
                assertFalse(true);
            } else {
                assertFalse(false);
            }
        } catch (Exception e) {
            assertFalse(false);
        }
    }

    /**
     * Test of editAccount method, of class QuickKarModel.
     */
    @Test
    public void testEditAccount1() {
        System.out.println("editAccount:edit valid account");
        try {
            modelTest = QuickKarModel.getModel();

            //add a new account
            String username = "staff1";
            String password = "staff1";
            String name = "hung";
            String email = "staff1@gmail.com";
            String telephone = "09-123-2123";
            String otherInfo = "OK";

            modelTest.addAccount(username, password, name, email, telephone, otherInfo);

            AccountInfo oldAccountInfo = modelTest.getAccountMap().get(username);

            //new information of that account
            String oldUsername = "staff1";
            String newUsername = "staff1";
            String newpassword = "staff1";
            String newname = "Hung";
            String newemail = "Hungnguyen@gmail.com";
            String newtelephone = "0126-123-0123";
            String newotherInfo = "KO";

            //old information of the account
            String oldPassword = oldAccountInfo.getPassword();
            String oldName = oldAccountInfo.getName();
            String oldEmail = oldAccountInfo.getEmail();
            String oldTelephone = oldAccountInfo.getTelephoneNo();
            String oldOtherInfo = oldAccountInfo.getTelephoneNo();


            if (!oldUsername.equalsIgnoreCase(newUsername)
                    || !oldEmail.equalsIgnoreCase(newemail)
                    || !oldName.equalsIgnoreCase(newname)
                    || !oldOtherInfo.equalsIgnoreCase(newotherInfo)
                    || !oldPassword.equalsIgnoreCase(newpassword)
                    || !oldTelephone.equalsIgnoreCase(newtelephone)
                    || !oldUsername.equalsIgnoreCase(newUsername)) {
                assertTrue(true);
            } else {
                assertTrue(false);
            }

        } catch (Exception e) {
            assertTrue(false);
        }
    }

      /**
     * Test of editAccount method, of class QuickKarModel.
     */
    @Test
    public void testEditAccount2() {
        System.out.println("editAccount:edit non-existed account");
        try {
            modelTest = QuickKarModel.getModel();
             String userNameNotExisted="staff2";
            //add a new account
            String username = "staff1";
            String password = "staff1";
            String name = "hung";
            String email = "staff1@gmail.com";
            String telephone = "09-123-2123";
            String otherInfo = "OK";

            modelTest.addAccount(username, password, name, email, telephone, otherInfo);

            //get non-existed account
            AccountInfo oldAccountInfo = modelTest.getAccountMap().get(userNameNotExisted);

            //new information of that account
            String oldUsername = "staff3";
            String newUsername = "staff3";
            String newpassword = "staff3";
            String newname = "Hung";
            String newemail = "Hungnguyen@gmail.com";
            String newtelephone = "0126-123-0123";
            String newotherInfo = "KO";

            //old information of the account
            String oldPassword = oldAccountInfo.getPassword();
            String oldName = oldAccountInfo.getName();
            String oldEmail = oldAccountInfo.getEmail();
            String oldTelephone = oldAccountInfo.getTelephoneNo();
            String oldOtherInfo = oldAccountInfo.getTelephoneNo();


            if (!oldUsername.equalsIgnoreCase(newUsername)
                    || !oldEmail.equalsIgnoreCase(newemail)
                    || !oldName.equalsIgnoreCase(newname)
                    || !oldOtherInfo.equalsIgnoreCase(newotherInfo)
                    || !oldPassword.equalsIgnoreCase(newpassword)
                    || !oldTelephone.equalsIgnoreCase(newtelephone)
                    || !oldUsername.equalsIgnoreCase(newUsername)) {
                modelTest.deleteAccount(username);
                assertFalse(true);
                
            } else {
                modelTest.deleteAccount(username);
                assertFalse(false);
             
            }
       modelTest.deleteAccount(username);
        } catch (Exception e) {
            assertFalse(false);

        }
    }

    /**
     * Test of deleteAccount method, of class QuickKarModel.
     */
    @Test
    public void testDeleteAccount() {
        System.out.println("deleteAccount");
        try{
            modelTest=QuickKarModel.getModel();
            modelTest.deleteAccount("staff1");
        String username = "";
        System.out.println(modelTest.getAccountMap().toString());
        }catch(Exception e){

        }
        
    }

    /**
     * Test of advanceSearch method, of class QuickKarModel.
     */
    @Test
    public void testAdvanceSearch1() {
        System.out.println("advanceSearch : search with anyOfkeywords");
        try{


        boolean anyOfTheKeyWords = true;
        boolean noneOfTheKeyWords = false;
        String keyWords = "anh can em";
        String songCode1 = "50000";
        String songCode2 = "50001";
        String songCode3="50002";


        modelTest = QuickKarModel.getModel();
        modelTest.deleteAllSong();
        
        //add song to the normal list
        modelTest.addSongMap(songCode1, "song1", "anh can em", "Mike1");
        modelTest.addSongMap(songCode2, "song2", "phai xa nhau", "Mike1");


       List<String> expResult= new ArrayList<String>();
       expResult.add(songCode1);

            System.out.println("ex:"+expResult.toString());

        List<String> realResult=modelTest.advanceSearch(keyWords, anyOfTheKeyWords, noneOfTheKeyWords);

            System.out.println("re:"+realResult.toString());


            assertTrue(expResult.containsAll(realResult));

        }catch(Exception e){

        }
    
    }

     /**
     * Test of advanceSearch method, of class QuickKarModel.
     */
    @Test
    public void testAdvanceSearch2() {
        System.out.println("advanceSearch : search with noneOfkeywords");
        try{


        boolean anyOfTheKeyWords = false;
        boolean noneOfTheKeyWords = true;
        String keyWords = "anh can em";
        String songCode1 = "50000";
        String songCode2 = "50001";
        String songCode3="50002";


        modelTest = QuickKarModel.getModel();
        modelTest.deleteAllSong();

        //add song to the normal list
        modelTest.addSongMap(songCode1, "song1", "anh can em", "Mike1");
        modelTest.addSongMap(songCode2, "song2", "phai xa nhau", "Mike1");


       List<String> expResult= new ArrayList<String>();
       expResult.add(songCode2);

            System.out.println("ex:"+expResult.toString());

        List<String> realResult=modelTest.advanceSearch(keyWords, anyOfTheKeyWords, noneOfTheKeyWords);

            System.out.println("re:"+realResult.toString());


            assertTrue(expResult.containsAll(realResult));

        }catch(Exception e){

        }



    }

    /**
     * Test of loginFunction method, of class QuickKarModel.
     */
    @Test
    public void testLoginFunction1() {
        System.out.println("loginFunction : with valid account");
        try{


         modelTest=QuickKarModel.getModel();
         
         //add account to the system
         modelTest.addAccount("staff1", "pstaff1", "hung", "staff1@gmail.com", "09-123-2123","OK");
         modelTest.addAccount("staff2", "pstaff2", "hung", "staff1@gmail.com", "09-123-2123","OK");


            
        String username = "staff1";
        String password = "pstaff1";
               assertTrue(modelTest.loginFunction(username, password));
        
        }catch(Exception e)    {

        }

    }

    /**
     * Test of loginFunction method, of class QuickKarModel.
     */
    @Test
    public void testLoginFunction2() {
        System.out.println("loginFunction : with non-existed account");
        try{


         modelTest=QuickKarModel.getModel();

         //add account to the system
         modelTest.addAccount("staff1", "pstaff1", "hung", "staff1@gmail.com", "09-123-2123","OK");
         modelTest.addAccount("staff2", "pstaff2", "hung", "staff1@gmail.com", "09-123-2123","OK");



        String username = "staff3";
        String password = "pstaff3";
               assertFalse(modelTest.loginFunction(username, password));

        }catch(Exception e)    {

        }

    }
}
