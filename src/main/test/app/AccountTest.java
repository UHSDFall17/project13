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

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testGetUserInfo_CorrectEmail(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getUsername(), testUser.getUsername());
    }

    @Test
    public void testGetUserInfo_CorrectPassword(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getPassword(), testUser.getPassword());
    }

    @Test
    public void testGetUserInfo_CorrectName(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getName(), testUser.getName());
    }

    @Test
    public void testGetUserInfo_CorrectCorporate(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getCorporate(), testUser.getCorporate());
    }

    @Test
    public void testGetUserInfo_CorrectSecurityQuestion1(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getQuestion1(), testUser.getQuestion1());
    }

    @Test
    public void testGetUserInfo_CorrectSecurityAnswer1(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getAnswer1(), testUser.getAnswer1());
    }

    @Test
    public void testGetUserInfo_SecurityQuestion2(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getQuestion2(), testUser.getQuestion2());
    }

    @Test
    public void testGetUserInfo_CorrectSecurityAnswer2(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertEquals(user.getAnswer2(), testUser.getAnswer2());
    }

    @Test
    public void testGetUserInfo_WrongEmail(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test99@gmail.com", "Asd123+","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongPassword(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "+123Asd","TEST11","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongName(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST99","0","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongCorporate(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST11","1","1","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongSQ1(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST11","0","2","MOM","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongSA1(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","FATHER","5","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongSQ2(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","8","HTX");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }

    @Test
    public void testGetUserInfo_WrongSA2(){
        if(!new File(System.getProperty("user.dir") + "/Accounts/test@gmail.com").exists()) {
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

        User user = new User("test@gmail.com", "Asd123+","TEST11","0","1","MOM","5","CANADA");
        User testUser = Account.getUserInfo("test@gmail.com");

        assertNotEquals(user, testUser);
    }
}
