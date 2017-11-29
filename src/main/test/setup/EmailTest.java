package setup;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class EmailTest {

    private Email testEmail;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testNullInput(){
        testEmail = new Email();

        String testNull = ""; //NULL, EMPTY

        assertFalse(testEmail.goodEmail(testNull));
    }

    @Test
    public void testEmailWithoutUsername(){
        testEmail = new Email();

        String testNoUsername = "@gmail.com"; //NO USERNAME

        assertFalse(testEmail.goodEmail(testNoUsername));
    }

    @Test
    public void testEmailWithoutAtSymbol(){
        testEmail = new Email();

        String testNoAtSymbol = "johnsmithgmail.com"; //NO @-SYMBOL

        assertFalse(testEmail.goodEmail(testNoAtSymbol));
    }

    @Test
    public void testEmailWithOnlyAtSymbol(){
        testEmail = new Email();

        String testOnlyAtSymbol = "@"; //ONLY @-SYMBOL

        assertFalse(testEmail.goodEmail(testOnlyAtSymbol));
    }

    @Test
    public void testEmailWithOnlyExtension(){
        testEmail = new Email();

        String testOnlyExtension = ".com"; //ONLY EXTENSION

        assertFalse(testEmail.goodEmail(testOnlyExtension));
    }

    public void testEmailWithoutDomain(){
        testEmail = new Email();

        String testNoDomain = "username@.com"; //NO DOMAIN

        assertFalse(testEmail.goodEmail(testNoDomain));
    }

    @Test
    public void testEmailWithDomainWithPeriod(){
        testEmail = new Email();

        String testDomainWithPeriod = "username@g.mail.com"; //DOMAIN WITH PERIOD

        assertTrue(testEmail.goodEmail(testDomainWithPeriod));
    }

    @Test
    public void testEmailWithInvalidExtension(){
        testEmail = new Email();

        String testInvalidExt = "johnsmith@gmail.orgg";

        assertFalse(testEmail.goodEmail(testInvalidExt));
    }

    @Test
    public void testEmailNoPeriodNorExtension(){
        testEmail = new Email();

        String testNoPeriodNoExtension = "username@domain"; //NO PERIOD NOR EXTENSION

        assertFalse(testEmail.goodEmail(testNoPeriodNoExtension));
    }

    @Test
    public void testEmailWithPeriodButNoExtension(){
        testEmail = new Email();

        String testPeriodButNoExtension = "username@domain."; //PERIOD BUT NO EXTENSION

        assertFalse(testEmail.goodEmail(testPeriodButNoExtension));
    }

    @Test
    public void testGoodEmail(){
        testEmail = new Email();

        String testGoodEmail = "johnsmith@gmail.com";

        assertTrue(testEmail.goodEmail(testGoodEmail));
    }

    @Test
    public void testNotRegisteredEmail(){
        testEmail = new Email();

        String testNotRegisteredEmail = "clearSteven18plastic@gmail.com";

        assertFalse(testEmail.isRegistered(testNotRegisteredEmail));
    }

    @Test
    public void testIsRegistered(){
        testEmail = new Email();

        if(!new File(System.getProperty("user.dir") + "/Accounts/johnsmith@gmail.com").exists()) {
            File key = new File("Accounts/johnsmith@gmail.com");
            key.mkdirs();
        }
        String testRegisteredEmail = "johnsmith@gmail.com";

        assertTrue(testEmail.isRegistered(testRegisteredEmail));
    }

    @Test
    public void testNonCorporateUser(){
        testEmail = new Email();
        assertEquals("0", testEmail.getCorporate("johnsmith@yahoo.com"));
    }

    @Test
    public void testGetCorporate(){
        testEmail = new Email();
        assertEquals("1", testEmail.getCorporate("johnsmith@uh.edu"));
    }
}
