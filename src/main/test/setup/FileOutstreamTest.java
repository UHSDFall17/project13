package setup;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileOutstreamTest {
    private FileOutstream fileOutstream;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testSaveNewAccount_FolderCreated_Success(){
        fileOutstream = new FileOutstream();
        String[] sqa1 = {"1", "MOM"};
        String[] sqa2 = {"5", "HTX"};
        fileOutstream.saveNewAccount("testEmail@hotmail.com", "testPswd123+","testName","0",sqa1,sqa2);

        File file = new File("Accounts/testEmail@hotmail.com/accountInfo.txt");
        assertTrue(file.exists());
    }
}
