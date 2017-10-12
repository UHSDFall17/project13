package setup;

import org.junit.Test;
import static org.junit.Assert.*;

public class CheckSecurityQATest {

    CheckSecurityQA testSecure;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testQuestion1_OutsideOfRange(){
        testSecure = new CheckSecurityQA();

        int questionNum = 1;
        char index = '5'; //FALSE b/c options range only from NUMBERS 1-4

        assertFalse(testSecure.validSelection(questionNum, index));
    }

    @Test
    public void testQuestion2_OutsideOfRange(){
        testSecure = new CheckSecurityQA();

        int questionNum = 2;
        char index = 'a'; //FALSE b/c options only range from NUMBERS 5-8

        assertFalse(testSecure.validSelection(questionNum, index));
    }

    @Test
    public void testQuestion1_WithinRange(){
        testSecure = new CheckSecurityQA();

        int questionNum = 1;
        char index = '4'; //TRUE b/c options range from 1-4

        assertTrue(testSecure.validSelection(questionNum, index));
    }

    @Test
    public void testQuestion2_WithinRange(){
        testSecure = new CheckSecurityQA();

        int questionNum = 2;
        char index = '5'; //TRUE b/c options range from 5-8

        assertTrue(testSecure.validSelection(questionNum, index));
    }


}
