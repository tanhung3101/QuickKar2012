/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickKarModel.facade;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nop
 */
public class AccountInfoTest {

    private static AccountInfo adminTest, staffTest1, staffTest2;

    public AccountInfoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

        System.out.println("Begin Test method of AccountInfo");
        adminTest = new AccountInfo("admin", "admin", "admin@admin.com", "08-123-1234", "OK");
        staffTest1 = new AccountInfo("staff1", "staff1", "staff@staff.com", "08-123-1235", "qww");

        staffTest2 = new AccountInfo(null, null, null, null, null);

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("AfterClass ");
    }

    /**
     * Test of getEmail method, of class AccountInfo for adminTest account
     */
    @Test
    public void testGetEmail1() {
        System.out.println("Test existed getEmail for admin");

        // TODO review the generated test code and remove the default call to fail.
        assertTrue((adminTest.getEmail() != null) ? true : false);
    }

    /**
     * Test of getEmail method, of class AccountInfo for staffTest1 account
     */
    @Test
    public void testGetEmail2() {
        System.out.println("Test existed getEmail for staffTest1");

        // TODO review the generated test code and remove the default call to fail.
        assertTrue((staffTest1.getEmail() != null) ? true : false);
    }

    /**
     * Test of getEmail method, of class AccountInfo for staffTest2 account
     */
    @Test
    public void testGetEmail3() {
        System.out.println("Test non-existed getEmail for staffTest2");

        // TODO review the generated test code and remove the default call to fail.
        assertFalse((staffTest2.getEmail() != null) ? true : false);
    }

    /**
     * Test of setEmail method, of class AccountInfo.
     */
    @Test
    public void testSetEmail1() {
        System.out.println("Test setEmail for admin");
        String expectEmail = "admin@gmail.com";
        String oldEmail = adminTest.getEmail();

        expectEmail.equalsIgnoreCase(adminTest.getEmail());
        adminTest.setEmail(expectEmail);
        if (oldEmail != null) {
            if ((expectEmail.equalsIgnoreCase(adminTest.getEmail())
                    && !oldEmail.equalsIgnoreCase(adminTest.getEmail()))) {
                assertTrue(true);

            } else {
                assertTrue(false);
            }
           
        }else
        {
             assertTrue(false);
        }


    }

    /*Test setEmail of staffTest1 in class AccountInfo */
    @Test
    public void testSetEmail2() {
        System.out.println("Test setEmail for staffTest1");
        String expectEmail = "staff@gmail.com";
        String oldEmail = staffTest1.getEmail();

        expectEmail.equalsIgnoreCase(staffTest1.getEmail());
        staffTest1.setEmail(expectEmail);
        if (oldEmail != null) {
            if ((expectEmail.equalsIgnoreCase(staffTest1.getEmail())
                    && !oldEmail.equalsIgnoreCase(staffTest1.getEmail()))) {
                assertTrue(true);

            } else {
                assertTrue(false);
            }

        }else
        {
            assertTrue(false);
        }


    }
    /*Test setEmail of staffTest2 with null information in class AccountInfo */

    @Test
    public void testSetEmail3() {
        System.out.println("Test setEmail for staffTest2");
        String expectEmail = "staff2@gmail.com";
        String oldEmail = staffTest2.getEmail();

        System.out.println(oldEmail);
        expectEmail.equalsIgnoreCase(staffTest2.getEmail());
        staffTest2.setEmail("abc@abc.com");

        System.out.println(staffTest2.getEmail());
            if ((expectEmail.equalsIgnoreCase(staffTest2.getEmail())  
                    && !oldEmail.equalsIgnoreCase(staffTest2.getEmail()))) {
                
                assertFalse(true);

            } else {
                
                assertFalse(false);
            }
               
        

    }

    /**
     * Test of getName method, of class AccountInfo for adminTest account
     */
    @Test
    public void testGetName1() {
        System.out.println("Test getName for admin");
      assertTrue(adminTest.getName()!=null?true:false);
    }

    /**
     * Test of getName method, of class AccountInfo for staffTest1 account
     */
    @Test
    public void testGetName2() {
        System.out.println("Test getName for staffTest1");
       assertTrue(staffTest1.getName()!=null?true:false);
    }

    /**
     * Test of getName method, of class AccountInfo for staffTest2 account
     */
    @Test
    public void testGetName3() {
        System.out.println("Test getName for staffTest2");
         assertFalse(staffTest2.getName()==null?true:false);
    }

    /**
     * Test of setName method, of class AccountInfo for adminTest account
     */
    @Test
    public void testSetName1() {
        System.out.println("Test setName for adminTest");
        String expectResult="admin2";
        String oldResult=adminTest.getName();


         System.out.println("aaa :"+adminTest.getName());
          System.out.println("old : "+oldResult);
        adminTest.setName(expectResult);
        System.out.println(adminTest.getName());

        if((expectResult.equalsIgnoreCase(adminTest.getName())
            && !oldResult.equalsIgnoreCase(adminTest.getName()))){

            assertTrue(true);

        }else
        {
            assertTrue(false);
        }

    }

    /**
     * Test of setName method, of class AccountInfo for staffTest1 account
     */
    @Test
    public void testSetName2() {
        System.out.println("Test setName for staffTest1");
        String expectResult="Nstaff1";
        String oldResult=staffTest1.getName();

        staffTest1.setName(expectResult);
        System.out.println(staffTest1.getName());

        if((expectResult.equalsIgnoreCase(staffTest1.getName())
            && !oldResult.equalsIgnoreCase(staffTest1.getName()))){

            assertTrue(true);

        }else
        {
            assertTrue(false);
        }

    }

    /**
     * Test of setName method, of class AccountInfo for staffTest2 account
     */
    @Test
    public void testSetName3() {
        System.out.println("Test setName for adminTest");
        String expectResult="Nstaff2";
        String oldResult=staffTest2.getName();

        staffTest2.setName(expectResult);
        System.out.println(staffTest2.getName());

        if((expectResult.equalsIgnoreCase(staffTest2.getName())
            )){

            assertFalse(false);

        }else
        {
            assertFalse(true);
        }

    }

    /**
     * Test of getOtherInfo method, of class AccountInfo for adminTest account
     */
    @Test
    public void testGetOtherInfo1() {
        System.out.println("Test getOtherInfo for adminTest account ");
        assertTrue(adminTest.getOtherInfo()!=null?true:false);
    }

    /**
     * Test of getOtherInfo method, of class AccountInfo for satffTest1 account
     */
    @Test
    public void testGetOtherInfo2() {
        System.out.println("Test getOtherInfo for staffTest1 account");
        assertTrue(staffTest1.getOtherInfo()!=null?true:false);
    }

    /**
     * Test of getOtherInfo method, of class AccountInfo for staffTest2 account
     */
    @Test
    public void testGetOtherInfo3() {
        System.out.println("Test getOtherInfo for staffTest2 account");
        assertFalse(staffTest2.getOtherInfo()!=null?true:false);
    }

    /**
     * Test of setOtherInfo method, of class AccountInfo for adminTest account
     */
    @Test
    public void testSetOtherInfo1() {
        System.out.println("setOtherInfo");
        String expectResult="I'm admin";
        String oldResult=adminTest.getOtherInfo();

        adminTest.setOtherInfo(expectResult);
          if((expectResult.equalsIgnoreCase(adminTest.getOtherInfo())
            && !oldResult.equalsIgnoreCase(adminTest.getOtherInfo()))){
            assertTrue(true);
        }else{
            assertTrue(false);
        }

    }

    /**
     * Test of setOtherInfo method, of class AccountInfo for staffTest2 account
     */
    @Test
    public void testSetOtherInfo2() {
        System.out.println("setOtherInfo");
        String expectResult="I'm staff1";
        String oldResult=staffTest1.getOtherInfo();

        staffTest1.setOtherInfo(expectResult);
          if((expectResult.equalsIgnoreCase(staffTest1.getOtherInfo())
            && !oldResult.equalsIgnoreCase(staffTest1.getOtherInfo()))){
            assertTrue(true);
        }else{
            assertTrue(false);
        }

    }

    /**
     * Test of setOtherInfo method, of class AccountInfo for staffTest2 account
     */
    @Test
    public void testSetOtherInfo3() {
        System.out.println("setOtherInfo");
        String expectResult="I'm staff2";
        String oldResult=staffTest2.getOtherInfo();

        staffTest2.setOtherInfo(expectResult);
          if((expectResult.equalsIgnoreCase(staffTest2.getOtherInfo())
            )){
            assertFalse(false);
        }else{
            assertFalse(true);
        }

    }

    /**
     * Test of getPassword method, of class AccountInfo for adminTest account
     */
    @Test
    public void testGetPassword1() {
        System.out.println("Test getPassword for amdinTest account");

        assertTrue(adminTest.getPassword()!=null?true:false);
    }

    /**
     * Test of getPassword method, of class AccountInfo for staffTest1 account
     */
    @Test
    public void testGetPassword2() {
        System.out.println("Test getPassword for staffTest1 account");
         assertTrue(staffTest1.getPassword()!=null?true:false);
    }

    /**
     * Test of getPassword method, of class AccountInfo  for staffTest2 account
     */
    @Test
    public void testGetPassword3() {
        System.out.println("Test getPassword for staffTest2 account");
        assertFalse(staffTest2.getPassword()!=null?true:false);
    }

    /**
     * Test of setPassword method, of class AccountInfo for adminTest account
     */
    @Test
    public void testSetPassword1() {
        System.out.println("setPassword");
        String expectResult="newadmin";
        String oldResult=adminTest.getPassword();

        adminTest.setPassword(expectResult);
      if((expectResult.equalsIgnoreCase(adminTest.getPassword())
            && !oldResult.equalsIgnoreCase(adminTest.getPassword()))){
          assertTrue(true);
      }else{
            assertTrue(false);
      }

    }

    /**
     * Test of setPassword method, of class AccountInfo for staffTest1 account
     */
    @Test
    public void testSetPassword2() {
        System.out.println("setPassword");
        String expectResult="newadmin";
        String oldResult=adminTest.getPassword();

        adminTest.setPassword(expectResult);
      if((expectResult.equalsIgnoreCase(adminTest.getPassword())
            && !oldResult.equalsIgnoreCase(adminTest.getPassword()))){
          assertTrue(true);
      }else{
            assertTrue(false);
      }

    }

    /**
     * Test of setPassword method, of class AccountInfo for staffTest2 account
     */
    @Test
    public void testSetPassword3() {
        System.out.println("setPassword");
        String expectResult="newadmin";
        String oldResult=adminTest.getPassword();

        adminTest.setPassword(expectResult);
      if((expectResult.equalsIgnoreCase(adminTest.getPassword())
            && !oldResult.equalsIgnoreCase(adminTest.getPassword()))){
          assertTrue(true);
      }else{
            assertTrue(false);
      }

    }

    /**
     * Test of getTelephoneNo method, of class AccountInfo for adminTest account
     */
    @Test
    public void testGetTelephoneNo1() {
        System.out.println("getTelephoneNo");
         assertTrue(adminTest.getTelephoneNo()!=null?true:false);
    }

     /**
     * Test of getTelephoneNo method, of class AccountInfo for adminTest account
     */
    @Test
    public void testGetTelephoneNo2() {
        System.out.println("getTelephoneNo");
         assertTrue(staffTest1.getTelephoneNo()!=null?true:false);
    }

     /**
     * Test of getTelephoneNo method, of class AccountInfo for adminTest account
     */
    @Test
    public void testGetTelephoneNo3() {
        System.out.println("getTelephoneNo");
         assertFalse(staffTest2.getTelephoneNo()!=null?true:false);
    }

    /**
     * Test of setTelephoneNo method, of class AccountInfo.
     */
    @Test
    public void testSetTelephoneNo() {
        System.out.println("setTelephoneNo");

    }
}
