package setup;

import app.Preference;
import org.junit.Test;

import static org.junit.Assert.*;

public class PreferenceTest {
    private Preference preference;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testContactUsConsolePrint(){
        preference = new Preference();
        preference.contactUs();
    }


}
