package setup;

import org.junit.Test;
import static org.junit.Assert.*;

public class CheckEmailTest {

    CheckEmail testEmail;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testNullInput(){
        testEmail = new CheckEmail();

        String testNull = ""; //NULL, EMPTY

        assertFalse(testEmail.goodEmail(testNull));
    }

    @Test
    public void testEmailWithoutUsername(){
        testEmail = new CheckEmail();

        String testNoUsername = "@gmail.com"; //NO USERNAME

        assertFalse(testEmail.goodEmail(testNoUsername));
    }

    @Test
    public void testEmailWithoutAtSymbol(){
        testEmail = new CheckEmail();

        String testNoAtSymbol = "johnsmithgmail.com"; //NO @-SYMBOL

        assertFalse(testEmail.goodEmail(testNoAtSymbol));
    }

    @Test
    public void testEmailWithOnlyAtSymbol(){
        testEmail = new CheckEmail();

        String testOnlyAtSymbol = "@"; //ONLY @-SYMBOL

        assertFalse(testEmail.goodEmail(testOnlyAtSymbol));
    }

    @Test
    public void testEmailWithOnlyExtension(){
        testEmail = new CheckEmail();

        String testOnlyExtension = ".com"; //ONLY EXTENSION

        assertFalse(testEmail.goodEmail(testOnlyExtension));
    }

    public void testEmailWithoutDomain(){
        testEmail = new CheckEmail();

        String testNoDomain = "username@.com"; //NO DOMAIN

        assertFalse(testEmail.goodEmail(testNoDomain));
    }

    @Test
    public void testEmailWithDomainWithPeriod(){
        testEmail = new CheckEmail();

        String testDomainWithPeriod = "username@g.mail.com"; //DOMAIN WITH PERIOD

        assertTrue(testEmail.goodEmail(testDomainWithPeriod));
    }

    @Test
    public void testEmailWithInvalidExtension(){
        testEmail = new CheckEmail();

        String testInvalidExt = "johnsmith@gmail.orgg";

        assertFalse(testEmail.goodEmail(testInvalidExt));
    }

    @Test
    public void testEmailNoPeriodNorExtension(){
        testEmail = new CheckEmail();

        String testNoPeriodNoExtension = "username@domain"; //NO PERIOD NOR EXTENSION

        assertFalse(testEmail.goodEmail(testNoPeriodNoExtension));
    }

    @Test
    public void testEmailWithPeriodButNoExtension(){
        testEmail = new CheckEmail();

        String testPeriodButNoExtension = "username@domain."; //PERIOD BUT NO EXTENSION

        assertFalse(testEmail.goodEmail(testPeriodButNoExtension));
    }

    @Test
    public void testGoodEmail(){
        testEmail = new CheckEmail();

        String testGoodEmail = "johnsmith@gmail.com";

        assertTrue(testEmail.goodEmail(testGoodEmail));
    }

    @Test
    public void testNotRegisteredEmail(){
        testEmail = new CheckEmail();

        String testNotRegisteredEmail = "clearSteven18plastic@gmail.com";

        assertFalse(testEmail.isRegistered(testNotRegisteredEmail));
    }

    @Test
    public void testRegisteredEmail(){
        testEmail = new CheckEmail();

        String testRegisteredEmail = "johnsmith@gmail.com";

        assertTrue(testEmail.isRegistered(testRegisteredEmail));
    }
}
