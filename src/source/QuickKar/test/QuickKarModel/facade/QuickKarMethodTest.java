/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickKarModel.facade;

import java.util.Arrays;
import java.util.HashSet;
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
public class QuickKarMethodTest {

    private static QuickKarMethod quickKarMethodTest;

    public QuickKarMethodTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("@Begin test method of QuickKarMethod Class");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("@After test method of QuickKarMethod Class");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of removeDuplicateWords method, of class QuickKarMethod.
     */
    @Test
    public void testRemoveDuplicateWords() {
        System.out.println("Test removeDuplicateWords");
        String keywords = "anh can em den truong em";
        Set<String> expectResult = new HashSet<String>(Arrays.asList(keywords.split("\\s")));

        String[] result = quickKarMethodTest.removeDuplicateWords(keywords);
        Set<String> newresult = new HashSet<String>(Arrays.asList(result));

        assertTrue(expectResult.containsAll(newresult));

        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of stringProcess method, of class QuickKarMethod.
     */
    @Test
    public void testStringProcess() {
        System.out.println("stringProcess");
        String input = "anh cần em đến trường";
        boolean removeSymbolOrSpecialCharacter = false;
        //String[] expResult =new String[]{ "anh", "can", "em", "den", "truong"};
        String expResult = "anh can em den truong";
        expResult.trim();

        String result = QuickKarMethod.stringProcess(input, removeSymbolOrSpecialCharacter);
        result.trim();
//      
        if(expResult.equalsIgnoreCase(result)){
          assertTrue(true);
        }else{
               assertTrue(false);
        }


    }

    /**
     * Test of formatDouble method, of class QuickKarMethod.
     */
    @Test
    public void testFormatDouble() {
        System.out.println("formatDouble");
        double value = 1.3;
        String expResult = "1.300000";
        String result = QuickKarMethod.formatDouble(value);

        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expResult, result);
    }
}
