package setup;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class SecurityQuestionsTest {
    private SecurityQuestions securityQuestions;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testNull() {
        securityQuestions = new SecurityQuestions();
        assertEquals(null, securityQuestions.getQuestion(0));
    }

    @Test
    public void testNotNull() {
        securityQuestions = new SecurityQuestions();
        assertNotEquals("?", securityQuestions.getQuestion(0));
    }

    @Test
    public void testQuestion1() {
        securityQuestions = new SecurityQuestions();
        assertEquals("What is your mother's maiden name?", securityQuestions.getQuestion(1));
    }

    @Test
    public void testNotQuestion1() {
        securityQuestions = new SecurityQuestions();
        assertNotEquals("What is your mother's maiden name", securityQuestions.getQuestion(1));
    }

    @Test
    public void testQuestion2() {
        securityQuestions = new SecurityQuestions();
        assertEquals("What is the name of the street that you lived on as a child?", securityQuestions.getQuestion(2));
    }

    @Test
    public void testNotQuestion2() {
        securityQuestions = new SecurityQuestions();
        assertNotEquals("What is the name of the street that you lived on as a child", securityQuestions.getQuestion(2));
    }

    @Test
    public void testQuestion3() {
        securityQuestions = new SecurityQuestions();
        assertEquals("What was the make and model of your first car?", securityQuestions.getQuestion(3));
    }

    @Test
    public void testNotQuestion3() {
        securityQuestions = new SecurityQuestions();
        assertNotEquals("What was the make and model of your first car", securityQuestions.getQuestion(3));
    }

    @Test
    public void testQuestion4() {
        securityQuestions = new SecurityQuestions();
        assertEquals("What was the name of your first pet?", securityQuestions.getQuestion(4));
    }

    @Test
    public void testNotQuestion4() {
        securityQuestions = new SecurityQuestions();
        assertNotEquals("What was the name of your first pt?", securityQuestions.getQuestion(4));
    }

    @Test
    public void testQuestion5() {
        securityQuestions = new SecurityQuestions();
        assertEquals("In what city or town did your mother and father meet?", securityQuestions.getQuestion(5));
    }

    @Test
    public void testNotQuestion5() {
        securityQuestions = new SecurityQuestions();
        assertNotEquals("In what city or town did your mother and fathe meet?", securityQuestions.getQuestion(5));
    }

    @Test
    public void testQuestion6() {
        securityQuestions = new SecurityQuestions();
        assertEquals("What is the last name of your favorite childhood teacher?", securityQuestions.getQuestion(6));
    }

    @Test
    public void testNotQuestion6() {
        securityQuestions = new SecurityQuestions();
        assertNotEquals("What is the last name of your favorite childhod teacher?", securityQuestions.getQuestion(6));
    }

    @Test
    public void testQuestion7() {
        securityQuestions = new SecurityQuestions();
        assertEquals("What is the first name of the person you first kissed?", securityQuestions.getQuestion(7));
    }

    @Test
    public void testNotQuestion7() {
        securityQuestions = new SecurityQuestions();
        assertNotEquals("What is the first name of the person you first kised?", securityQuestions.getQuestion(7));
    }

    @Test
    public void testQuestion8() {
        securityQuestions = new SecurityQuestions();
        assertEquals("What elementary/ primary school did you go to?", securityQuestions.getQuestion(8));
    }

    @Test
    public void testNotQuestion8() {
        securityQuestions = new SecurityQuestions();
        assertNotEquals("What elementary/ primary schooldid you go to?", securityQuestions.getQuestion(8));
    }
}
