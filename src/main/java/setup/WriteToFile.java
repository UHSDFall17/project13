package setup;

import java.io.*;

public class WriteToFile {

    public WriteToFile(){}

    public void saveNewAccount(String email, String pswd, String name, int Q1, String A1, int Q2, String A2) {
        try {
            File key = new File(System.getProperty("user.dir") + "/Accounts/" + email);
            key.mkdirs();

            PrintWriter account = new PrintWriter(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt");
            account.flush();

            account.println(pswd);
            account.println(name);

            account.println(Q1);
            account.println(A1);

            account.println(Q2);
            account.print(A2);

            account.close();
            System.out.println("\nWELCOME, " + name + "!\nYou have successfully created an account.\n");
        }
        catch(IOException e) {
            System.out.println("Something went wrong! Please try again.");
            System.exit(1);
        }
        //return to LOGIN PAGE
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

            System.out.println("\nSUCCESS! Your password has been updated.");//SUCCESS
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\nFAILED: Your password has not been updated. Program Terminated.");
        }
    }

    public void updateSQ(String email, String[] newSQA){
        /* newSQA: size = 4 {newSQ1, newAns1, newSQ2, newAns2} */

        try {
            BufferedReader inStream = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt"));
            StringBuffer getLine = new StringBuffer();
            String oldLine;

            /* GRAB ALL LINES AND OLD SECURITY QUESTIONS AND ANSWERS */
            int lineNum = 1;
            String[] oldSQA = new String[4];
            while((oldLine = inStream.readLine()) != null){
                getLine.append(oldLine);
                getLine.append("\n");

                if(lineNum > 2) //password: lineNum = 1 //name: lineNum = 2
                {
                    oldSQA[lineNum - 3] = oldLine;
                }
                lineNum++;
            }
            String line = getLine.toString();
            inStream.close();

            /* REPLACE OLD SECURITY QUESTIONS AND ANSWERS WITH NEW */
            FileOutputStream outStream = new FileOutputStream(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt");
            for(int i = 0; i < 4; i++){
                line = line.replace(oldSQA[i], newSQA[i]);
                outStream.write(line.getBytes());
            }
            outStream.close();

            System.out.println("\nSUCCESS! Your security questions and answers have been updated.");//SUCCESS
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\nFAILED: Your security questions and/or answers have not been updated. Program Terminated.");
        }
    }
}
