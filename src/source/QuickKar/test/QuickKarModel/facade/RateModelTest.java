/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickKarModel.facade;

import java.text.DecimalFormat;
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
public class RateModelTest {

    private static RateModel arrayTest;

    public RateModelTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

        System.out.println("@Begin Test method of RateModel class");
        arrayTest = new RateModel();


    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("@After Test method of RateModel Class");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of increaseStar method, of class RateModel.
     */
    @Test
    public void testIncreaseStar1() {
        System.out.println("Test increaseStar");
        arrayTest=new RateModel();
        int star = 3;
        int oldNoOfVote = arrayTest.getCount();
        int oldValueByIndex = arrayTest.getStartValue(star);
        arrayTest.increaseStar(star);
        int newNoOfVote = arrayTest.getCount();

        int newValueByIndex = arrayTest.getStartValue(star);
        // TODO review the generated test code and remove the default call to fail.

        if (oldNoOfVote != newNoOfVote && oldValueByIndex != newValueByIndex) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }

    }

    /**
     * Test of increaseStar method, of class RateModel.
     */
    @Test
    public void testIncreaseStar2() {
        System.out.println("Test increaseStar wrong");
        arrayTest=new RateModel();
        int star = 3;
        int star2=4;
        int oldNoOfVote = arrayTest.getCount();
        //int oldValueByIndex = arrayTest.getStartValue(star);
        int oldValueByIndex2=arrayTest.getStartValue(star2);

        arrayTest.increaseStar(star);
        int newNoOfVote = arrayTest.getCount();

        //int newValueByIndex = arrayTest.getStartValue(star);
        int newValueByIndex2=arrayTest.getStartValue(star2);
        // TODO review the generated test code and remove the default call to fail.

        if (oldNoOfVote != newNoOfVote) {
            if(oldValueByIndex2!=newValueByIndex2){
            assertFalse(true);
            }else{
                assertFalse(false);
            }

        } else {
            assertFalse(false);
        }

    }

    /**
     * Test of decreaseStar method, of class RateModel.
     */
    @Test
    public void testDecreaseStar() {
        System.out.println("decreaseStar");
        arrayTest=new RateModel();
        int star = 3;

        int oldNoOfVote = arrayTest.getCount();
        int oldValueByIndex = arrayTest.getStartValue(star);
        arrayTest.decreaseStar(star);
        int newNoOfVote = arrayTest.getCount();

        int newValueByIndex = arrayTest.getStartValue(star);
        // TODO review the generated test code and remove the default call to fail.

        if (oldNoOfVote != newNoOfVote && oldValueByIndex != newValueByIndex) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    /**
     * Test of getCount method, of class RateModel.
     */
    @Test
    public void testGetCount() {

        arrayTest=new RateModel();
        System.out.println("getCount");
        int expResult = 0;
        int result = arrayTest.getCount();
        
        assertTrue(expResult==result?true:false);
        
        
    }

    /**
     * Test of averageRate method, of class RateModel.
     */
    @Test
    public void testAverageRate() {

        System.out.println("averageRate");
        arrayTest=new RateModel();
        int star=3;
        //vote three times for the third star
        arrayTest.increaseStar(star);
        arrayTest.increaseStar(star);
        arrayTest.increaseStar(star);

        double expResult = 3.0;
        double result = arrayTest.averageRate();

        assertTrue(expResult==result?true:false);
        // TODO review the generated test code and remove the default call to fail.
        
    }
}
