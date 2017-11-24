package setup;

import Utilities.Stream;

import java.io.*;

public class FileOutstream {

    Stream stream;

    public FileOutstream(){ stream = new Stream();}

    public void saveNewAccount(String email, String pswd, String name, String Q1, String A1, String Q2, String A2) {
        try {
            File key = new File(System.getProperty("user.dir") + "/Accounts/" + email);
            key.mkdirs();

            PrintWriter account = new PrintWriter(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt");

            account.println(pswd);
            account.println(name);

            account.println(Q1);
            account.println(A1);

            account.println(Q2);
            account.print(A2);

            account.close();
        }
        catch(IOException e) {
            stream.writeToConsole("Something went wrong! Please try again.");
            System.exit(1);
        }
        //return to LOGIN PAGE
    }

    public void updateName(String email, String oldName, String newName){
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
            stream.writeToConsole("\nFAILED: Your name has not been updated. Program Terminated.");
        }
    }

    public void updatePswd(String email, String oldPswd, String newPswd){
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
            stream.writeToConsole("\nFAILED: Your password has not been updated. Program Terminated.");
        }
    }

    public void updateSQ(String email, String pswd, String name, String[] newSQA){
        /* newSQA: size = 4 {newSQ1, newAns1, newSQ2, newAns2} */
        try{
            PrintWriter account = new PrintWriter(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt");

            account.println(pswd);
            account.println(name);

            account.println(newSQA[0]);
            account.println(newSQA[1]);

            account.println(newSQA[2]);
            account.print(newSQA[3]);

            account.close();

            stream.writeToConsole("\nSUCCESS! Your security questions and answers have been updated.");//SUCCESS
        } catch (IOException e) {
            e.printStackTrace();
            stream.writeToConsole("\nFAILED: Your security questions and/or answers have not been updated. Program Terminated.");
        }
    }
}
