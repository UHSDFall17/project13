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
    public void testContactUsConsolePrint() throws IOException {
        preference = new Preference();
        preference.contactUs();
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


}
