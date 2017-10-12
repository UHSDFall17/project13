package setup;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.*;

public class CheckPasswordTest {
    CheckPassword testPswd;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    //write test for network failure

    @Test
    public void testPasswordIsTooShort(){
        testPswd = new CheckPassword();

        char[] testValue = "12345".toCharArray(); //too short: MIN of 6 characters, ACTUAL has 5 characters

        assertFalse(testPswd.goodPswd(testValue));
    }

    @Test
    public void testPasswordIsTooLong(){
        testPswd = new CheckPassword();

        char[] testValue = "abcdefghijklmnopqrstuvwxyz".toCharArray(); //too long: MAX 20 character, ACTUAL has 26 characters

        assertFalse(testPswd.goodPswd(testValue));
    }

    @Test
    public void testPasswordHasNoCapitalLetter(){
        testPswd = new CheckPassword();

        char[] testValue = "abc123+".toCharArray(); //no capitalized letter

        assertFalse(testPswd.goodPswd(testValue));
    }

    @Test
    public void testPasswordHasNoDigits(){
        testPswd = new CheckPassword();

        char[] testValue = "AbcDef+".toCharArray(); //no numbers or digits

        assertFalse(testPswd.goodPswd(testValue));
    }

    @Test
    public void testPasswordHasNoSpecialCharacter(){
        testPswd = new CheckPassword();

        char[] testValue = "Abc123".toCharArray(); //no special character

        assertFalse(testPswd.goodPswd(testValue));
    }

    @Test
    public void testPasswordMeetsAllRequirements(){
        testPswd = new CheckPassword();

        char[] testValue = "Abc123+".toCharArray(); //Good, acceptable password
        assertTrue(testPswd.goodPswd(testValue));
    }

}
