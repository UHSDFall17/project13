package setup;

import app.Account;
import app.Preference;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class PreferenceTest {
    private Preference preference;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testGetUserEmail() throws IOException {
        preference = new Preference();
        /* SET AS ACTIVE, LOGGED IN USER */
        BufferedWriter writer = new BufferedWriter(new FileWriter("Accounts/LastLogin.txt"));
        writer.write("testEmail@gmail.com");
        writer.close();

        assertEquals("testEmail@gmail.com", preference.getUserEmail());
    }

    @Test
    public void testGetUpdatedUser_GetUserName_Correct() throws IOException {
        /*CREATE ACCOUNT FILE*/
        File key = new File(System.getProperty("user.dir") + "/Accounts/testEmail@gmail.com");
        key.mkdirs();
        PrintWriter account = new PrintWriter(System.getProperty("user.dir") + "/Accounts/testEmail@gmail.com/accountInfo.txt");
        account.println("testPassword123+");
        account.println("testName");
        account.println("0"); //1 for corporate user -- 0 for non-corporate user
        account.println("1");
        account.println("MOM");
        account.println("5");
        account.print("HTX");
        account.close();

        /* SET AS ACTIVE, LOGGED IN USER */
        BufferedWriter writer = new BufferedWriter(new FileWriter("Accounts/LastLogin.txt"));
        writer.write("testEmail@gmail.com");
        writer.close();

        preference = new Preference();
        assertEquals(Account.getUserInfo("testEmail@gmail.com").getUsername(), preference.getUpdatedUser().getUsername());
    }

    @Test
    public void testGetUpdatedUser_GetPassword_Correct() throws IOException {
        /*CREATE ACCOUNT FILE*/
        File key = new File(System.getProperty("user.dir") + "/Accounts/testEmail@gmail.com");
        key.mkdirs();
        PrintWriter account = new PrintWriter(System.getProperty("user.dir") + "/Accounts/testEmail@gmail.com/accountInfo.txt");
        account.println("testPassword123+");
        account.println("testName");
        account.println("0"); //1 for corporate user -- 0 for non-corporate user
        account.println("1");
        account.println("MOM");
        account.println("5");
        account.print("HTX");
        account.close();

        /* SET AS ACTIVE, LOGGED IN USER */
        BufferedWriter writer = new BufferedWriter(new FileWriter("Accounts/LastLogin.txt"));
        writer.write("testEmail@gmail.com");
        writer.close();

        preference = new Preference();
        assertEquals(Account.getUserInfo("testEmail@gmail.com").getPassword(), preference.getUpdatedUser().getPassword());
    }

    @Test
    public void testContactUsConsolePrint() throws IOException {
        preference = new Preference();
        preference.contactUs();
    }
}
