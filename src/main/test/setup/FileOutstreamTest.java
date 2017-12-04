package setup;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class FileOutstreamTest {
    private FileOutstream fileOutstream;

    @Test
    public void Canary() { //verifies good env
        assertTrue(true);
    }

    @Test
    public void testSaveNewAccount_FolderCreated_Success() throws IOException {
        fileOutstream = new FileOutstream();
        String[] sqa1 = {"1", "MOM"};
        String[] sqa2 = {"5", "HTX"};
        fileOutstream.saveNewAccount("testEmail@hotmail.com", "testPswd123+", "testName", "0", sqa1, sqa2);

        File file = new File("Accounts/testEmail@hotmail.com");
        assertTrue(file.exists());
    }

    @Test
    public void testSaveNewAccount_FileCreated_Success() throws IOException {
        fileOutstream = new FileOutstream();
        String[] sqa1 = {"1", "MOM"};
        String[] sqa2 = {"5", "HTX"};
        fileOutstream.saveNewAccount("testEmail@hotmail.com", "testPswd123+", "testName", "0", sqa1, sqa2);

        File file = new File("Accounts/testEmail@hotmail.com/accountInfo.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testSaveNewAccount_Password_Correct() throws IOException {
        fileOutstream = new FileOutstream();
        String[] sqa1 = {"1", "MOM"};
        String[] sqa2 = {"5", "HTX"};
        fileOutstream.saveNewAccount("testEmail@hotmail.com", "testPswd123+", "testName", "0", sqa1, sqa2);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts/testEmail@hotmail.com/accountInfo.txt"));
            assertEquals("testPswd123+", reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveNewAccount_Name_Correct() throws IOException {
        fileOutstream = new FileOutstream();
        String[] sqa1 = {"1", "MOM"};
        String[] sqa2 = {"5", "HTX"};
        fileOutstream.saveNewAccount("testEmail@hotmail.com", "testPswd123+", "testName", "0", sqa1, sqa2);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts/testEmail@hotmail.com/accountInfo.txt"));
            reader.readLine(); //password
            assertEquals("testName", reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveNewAccount_Corporate_Correct() throws IOException {
        fileOutstream = new FileOutstream();
        String[] sqa1 = {"1", "MOM"};
        String[] sqa2 = {"5", "HTX"};
        fileOutstream.saveNewAccount("testEmail@hotmail.com", "testPswd123+", "testName", "0", sqa1, sqa2);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts/testEmail@hotmail.com/accountInfo.txt"));
            reader.readLine(); //password
            reader.readLine(); //name
            assertEquals("0", reader.readLine()); //not corporate
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveNewAccount_Question1_Correct() throws IOException {
        fileOutstream = new FileOutstream();
        String[] sqa1 = {"1", "MOM"};
        String[] sqa2 = {"5", "HTX"};
        fileOutstream.saveNewAccount("testEmail@hotmail.com", "testPswd123+", "testName", "0", sqa1, sqa2);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts/testEmail@hotmail.com/accountInfo.txt"));
            reader.readLine(); //password
            reader.readLine(); //name
            reader.readLine(); //corporate
            assertEquals("1", reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveNewAccount_Answer1_Correct() throws IOException {
        fileOutstream = new FileOutstream();
        String[] sqa1 = {"1", "MOM"};
        String[] sqa2 = {"5", "HTX"};
        fileOutstream.saveNewAccount("testEmail@hotmail.com", "testPswd123+", "testName", "0", sqa1, sqa2);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts/testEmail@hotmail.com/accountInfo.txt"));
            reader.readLine(); //password
            reader.readLine(); //name
            reader.readLine(); //corporate
            reader.readLine(); //question 1
            assertEquals("MOM", reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveNewAccount_Question2_Correct() throws IOException {
        fileOutstream = new FileOutstream();
        String[] sqa1 = {"1", "MOM"};
        String[] sqa2 = {"5", "HTX"};
        fileOutstream.saveNewAccount("testEmail@hotmail.com", "testPswd123+", "testName", "0", sqa1, sqa2);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts/testEmail@hotmail.com/accountInfo.txt"));
            reader.readLine(); //password
            reader.readLine(); //name
            reader.readLine(); //corporate
            reader.readLine(); //question 1
            reader.readLine(); //answer 1
            assertEquals("5", reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveNewAccount_Answer2_Correct() throws IOException {
        fileOutstream = new FileOutstream();
        String[] sqa1 = {"1", "MOM"};
        String[] sqa2 = {"5", "HTX"};
        fileOutstream.saveNewAccount("testEmail@hotmail.com", "testPswd123+", "testName", "0", sqa1, sqa2);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts/testEmail@hotmail.com/accountInfo.txt"));
            reader.readLine(); //password
            reader.readLine(); //name
            reader.readLine(); //corporate
            reader.readLine(); //question 1
            reader.readLine(); //answer 1
            reader.readLine(); //question 2
            assertEquals("HTX", reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateName_SUCCESS() {
        fileOutstream = new FileOutstream();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts/testEmail@hotmail.com/accountInfo.txt"));
            reader.readLine(); //password
            String oldName = reader.readLine();
            reader.close();

            fileOutstream.updateName("testEmail@hotmail.com", oldName, "updatedTestName");

            BufferedReader reader2 = new BufferedReader(new FileReader("Accounts/testEmail@hotmail.com/accountInfo.txt"));
            reader2.readLine(); //password
            assertEquals("updatedTestName", reader2.readLine());
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdatePswd_SUCCESS() {
        fileOutstream = new FileOutstream();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts/testEmail@hotmail.com/accountInfo.txt"));
            String oldPassword = reader.readLine();
            reader.close();

            fileOutstream.updatePswd("testEmail@hotmail.com", oldPassword, "newTestPassword123+");

            BufferedReader reader2 = new BufferedReader(new FileReader("Accounts/testEmail@hotmail.com/accountInfo.txt"));
            assertEquals("newTestPassword123+", reader2.readLine());
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateSQ_SUCCESS() {
        fileOutstream = new FileOutstream();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts/testEmail@hotmail.com/accountInfo.txt"));
            String password = reader.readLine();
            String name = reader.readLine();
            String corporate = reader.readLine();
            reader.close();

            String[] newSQA = {"4","NEW ANSWER TO 4","8","NEW ANSWER TO 8"};
            fileOutstream.updateSQ("testEmail@hotmail.com", password, name, corporate, newSQA);

            BufferedReader reader2 = new BufferedReader(new FileReader("Accounts/testEmail@hotmail.com/accountInfo.txt"));
            reader2.readLine(); //password
            reader2.readLine(); //name
            reader2.readLine(); //corporate
            assertEquals("4", reader2.readLine());
            assertEquals("NEW ANSWER TO 4", reader2.readLine());
            assertEquals("8", reader2.readLine());
            assertEquals("NEW ANSWER TO 8", reader2.readLine());
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
