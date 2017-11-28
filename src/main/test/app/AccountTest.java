package app;

import org.junit.Test;
import setup.SecurityQuestions;
import setup.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class AccountTest {

    private Account account;

    public AccountTest() {createExpectedFile();}

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    public void createExpectedFile(){
        File key = new File("Accounts/test@gmail.com");
        key.mkdirs();

        try {
            PrintWriter account = new PrintWriter(System.getProperty("user.dir") + "/Accounts/test@gmail.com/accountInfo.txt");

            account.println("Asd123+");
            account.println("TEST11");

            account.println("0"); //1 for corporate user -- 0 for non-corporate user

            account.println("1");
            account.println("MOM");

            account.println("5");
            account.print("HTX");

            account.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUserInfo_CorrectEmail(){
        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getUsername(), testUser.getUsername());
    }

    @Test
    public void testGetUserInfo_CorrectPassword(){
        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getPassword(), testUser.getPassword());
    }

    @Test
    public void testGetUserInfo_CorrectName(){
        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getName(), testUser.getName());
    }

    @Test
    public void testGetUserInfo_CorrectCorporate(){
        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getCorporate(), testUser.getCorporate());
    }

    @Test
    public void testGetUserInfo_CorrectSecurityQuestion1(){
        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getQuestion1(), testUser.getQuestion1());
    }

    @Test
    public void testGetUserInfo_CorrectSecurityAnswer1(){
        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getAnswer1(), testUser.getAnswer1());
    }

    @Test
    public void testGetUserInfo_SecurityQuestion2(){
        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getQuestion2(), testUser.getQuestion2());
    }

    @Test
    public void testGetUserInfo_CorrectSecurityAnswer2(){
        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getAnswer2(), testUser.getAnswer2());
    }

    @Test
    public void testGetUserInfo_WrongEmail(){
        User user = new User("test99@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongPassword(){
        User user = new User("test@gmail.com", "+123Asd","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongName(){
        User user = new User("test@gmail.com", "Asd123+","TEST99","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongCorporate(){
        User user = new User("test@gmail.com", "Asd123+","TEST11","1","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongSQ1(){
        User user = new User("test@gmail.com", "Asd123+","TEST11","0","2","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongSA1(){
        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","FATHER","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongSQ2(){
        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","8","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongSA2(){
        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","CANADA");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }
}
