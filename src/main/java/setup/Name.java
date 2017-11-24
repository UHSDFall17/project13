package setup;

import Utilities.Stream;

import java.io.*;

public class Name {
    Stream stream;

    private String userEmail, userPassword, userName; //FOR verifying user
    protected String name;

    public Name(){
        stream = new Stream();
    }

    public String setAndGetNewName(){
        do {
            stream.writeToConsole("Name: ");
            name = stream.readLineFromConsole().toUpperCase();
        }while(name.isEmpty());

        return name;
    }

    public void changeName(){
        try{
            BufferedReader lastLogin = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/LastLogin.txt"));
            userEmail = lastLogin.readLine();
            lastLogin.close();

            BufferedReader userFile = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + userEmail + "/accountInfo.txt"));
            userPassword = userFile.readLine(); // grabs password
            userName = userFile.readLine(); //grabs user's name
            userFile.close();

            boolean accessGranted;
            do{
                stream.writeToConsole("Log-In Password: ");
                accessGranted = stream.readLineFromConsole().equals(userPassword);
                if(!accessGranted)
                    stream.writeToConsole("Incorrect Password.\n");
            } while(!accessGranted);

            stream.writeToConsole("\n(Change Account Name) Access Granted.\n");
            name = setAndGetNewName();

            /* REPLACE AND UPDATE IN FILE */
            FileOutstream updateName = new FileOutstream();
            updateName.updateName(userEmail, userName, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
