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
    public void testEmailWithoutAtSymbol(){
        testEmail = new CheckEmail();

        String testNoAtSymbol = "johnsmithgmail.com"; //NO @-SYMBOL

        assertFalse(testEmail.goodEmail(testNoAtSymbol));
    }

    @Test
    public void testEmailWithInvalidExtension(){
        testEmail = new CheckEmail();

        String testInvalidExt = "johnsmith@gmail.orgg";

        assertFalse(testEmail.goodEmail(testInvalidExt));
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
