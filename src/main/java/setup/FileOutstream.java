package setup;

import Utilities.Stream;

import java.io.*;

public class FileOutstream {

    Stream stream;

    public FileOutstream(){ stream = new Stream();}

    public void saveNewAccount(String email, String pswd, String name, String corporate, String[] QA1, String[] QA2)
            throws IOException {
        File key = new File(System.getProperty("user.dir") + "/Accounts/" + email);
        key.mkdirs();

        PrintWriter account = new PrintWriter(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt");

        account.println(pswd);
        account.println(name);

        account.println(corporate); //1 for corporate user -- 0 for non-corporate user

        account.println(QA1[0]);
        account.println(QA1[1]);

        account.println(QA2[0]);
        account.print(QA2[1]);

        account.close();
    }

    public void updateName(String email, String oldName, String newName) throws IOException {
        BufferedReader inStream = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt"));
        StringBuffer getLine = new StringBuffer();
        String oldLine;

        /* GRAB LINES AND OLD NAME*/
        while((oldLine = inStream.readLine()) != null){
            getLine.append(oldLine);
            getLine.append("\n");
        }
        String line = getLine.toString();
        inStream.close();

        /* REPLACE OLD PSWD WITH NEW PSWD */
        line = line.replace(oldName, newName);
        FileOutputStream outStream = new FileOutputStream(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt");
        outStream.write(line.getBytes());
        outStream.close();

        stream.writeToConsole("\nSUCCESS! HELLO, " + newName + ".");//SUCCESS
    }

    public void updatePswd(String email, String oldPswd, String newPswd) throws IOException {
        BufferedReader inStream = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt"));
        StringBuffer getLine = new StringBuffer();
        String oldLine;

        /* GRAB LINES */
        while((oldLine = inStream.readLine()) != null){
            getLine.append(oldLine);
            getLine.append("\n");
        }
        String line = getLine.toString();
        inStream.close();

        /* REPLACE OLD PSWD WITH NEW PSWD */
        line = line.replace(oldPswd, newPswd);
        FileOutputStream outStream = new FileOutputStream(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt");
        outStream.write(line.getBytes());
        outStream.close();

        stream.writeToConsole("\nSUCCESS! Your password has been updated.");//SUCCESS
    }

    public void updateSQ(String email, String pswd, String name, String corporate, String[] newSQA) throws FileNotFoundException {
        /* newSQA: size = 4 {newSQ1, newAns1, newSQ2, newAns2} */
        PrintWriter account = new PrintWriter(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt");

        account.println(pswd);
        account.println(name);

        account.println(corporate);

        account.println(newSQA[0]);
        account.println(newSQA[1]);

        account.println(newSQA[2]);
        account.print(newSQA[3]);

        account.close();

        stream.writeToConsole("\nSUCCESS! Your security questions and answers have been updated.");//SUCCESS
    }
}
