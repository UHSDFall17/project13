package setup;

import java.io.*;

public class WriteToFile {

    public WriteToFile(){}

    protected void saveNewAccount(String email, String pswd, String name, int Q1, String A1, int Q2, String A2) {
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
            String oldline;

            /* GRAB LINES */
            while((oldline = inStream.readLine()) != null){
                getLine.append(oldline);
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

}
