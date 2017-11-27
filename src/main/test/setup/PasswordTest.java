package setup;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.*;

public class PasswordTest {
    Password testPswd;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    //write test for network failure

    @Test
    public void testPasswordIsTooShort(){
        testPswd = new Password();

        char[] testTooShort = "12345".toCharArray(); //too short: MIN of 6 characters, ACTUAL has 5 characters

        assertFalse(testPswd.meetsRequirements(testTooShort));
    }

    @Test
    public void testPasswordIsTooLong(){
        testPswd = new Password();

        char[] testTooLong = "abcdefghijklmnopqrstuvwxyz".toCharArray(); //too long: MAX 20 character, ACTUAL has 26 characters

        assertFalse(testPswd.meetsRequirements(testTooLong));
    }

    @Test
    public void testPasswordHasNoCapitalLetter(){
        testPswd = new Password();

        char[] testNoCapitalizedLetter = "abc123+".toCharArray(); //no capitalized letter

        assertFalse(testPswd.meetsRequirements(testNoCapitalizedLetter));
    }

    @Test
    public void testPasswordHasNoDigits(){
        testPswd = new Password();

        char[] testNoDigit = "AbcDef+".toCharArray(); //no numbers or digits

        assertFalse(testPswd.meetsRequirements(testNoDigit));
    }

    @Test
    public void testPasswordHasNoSpecialCharacter(){
        testPswd = new Password();

        char[] testNoSpecial = "Abc123".toCharArray(); //no special character

        assertFalse(testPswd.meetsRequirements(testNoSpecial));
    }

    @Test
    public void testPasswordMeetsAllRequirements(){
        testPswd = new Password();

        char[] testGood = "Abc123+".toCharArray(); //Good, acceptable password
        assertTrue(testPswd.meetsRequirements(testGood));
    }
}
