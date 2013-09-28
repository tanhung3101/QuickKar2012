/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickKarController;

import java.util.HashMap;
import java.util.HashSet;
import QuickKarModel.facade.*;
import java.util.Map;
import QuickKarModel.facade.AccountInfo;
import QuickKarModel.facade.QuickKarModel;
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
public class SavingAndReadingControllerTest {

    private static QuickKarModel modelTest = QuickKarModel.getModel();
//    private static SavingAndReadingController saveAndLoadController;
//    private static Map<String, AccountInfo> accountMap;
//    private static Map<String, SongInfo> songMap;
//    private static Map<String, HashSet<String>> tagMap;
//    private static Map<String, RateModel> rateMap;
//    private static Map<String, HashSet<String>> favoriteMap;
//    private static Map<String, HashMap<String, SongRateModel>> staffAndCustomerRateMap;
//    private Map<String, HashSet<String>> tagMapWithoutAccent;

    public SavingAndReadingControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("@Before Test method of SAvingAndReadingControleer");

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of readFile method, of class SavingAndReadingController.
     * In here, first we will get the size of the list of account,
     * songs, favorite song, tagsong and RatesSong in the system before loading
     * data from bin files.Then after loading data to these list, get the size
     * of those list to compare that number in order to know that data is loaded or not.
     */
    @Test
    public void testReadFile() {
        System.out.println("Test readFiles");
       modelTest=QuickKarModel.getModel();
        SavingAndReadingController.readFile();
        if(SavingAndReadingController.isDoneReadingDatabase()==true)
        {
            assertTrue(true);
        }else
        {
            assertTrue(false);
        }
     }

    /**
     * Test of saveAccount method, of class SavingAndReadingController.
     */
    @Test
    public void testSaveAccount() {
        System.out.println("saveAccount");

        SavingAndReadingController.readFile();
        SavingAndReadingController.saveAccount();
        assertTrue((SavingAndReadingController.isDoneSavingAccountDatabase()));
                
                
    }
   
    /**
     * Test of saveSong method, of class SavingAndReadingController.
     */
    @Test
    public void testSaveSong() {
        System.out.println("saveSong");
        SavingAndReadingController.readFile();
        SavingAndReadingController.saveSong();

        assertTrue(SavingAndReadingController.isDoneSavingSongDatabase());
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of saveRateMap method, of class SavingAndReadingController.
     */
    @Test
    public void testSaveRateMap() {
        System.out.println("saveRateMap");

        SavingAndReadingController.readFile();
        SavingAndReadingController.saveRateMap();


        assertTrue(SavingAndReadingController.isDoneSavingRateDatabase());
        // TODO review the generated test code and remove the default call to fail.
     
    }

    /**
     * Test of saveFavoriteMap method, of class SavingAndReadingController.
     */
    @Test
    public void testSaveFavoriteMap() {
        System.out.println("saveFavoriteMap");
        SavingAndReadingController.readFile();
        SavingAndReadingController.saveFavoriteMap();

        assertTrue(SavingAndReadingController.isDoneSavingFavoriteDatabase());
        // TODO review the generated test code and remove the default call to fail.
        
    }

}
