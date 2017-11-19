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

            BufferedReader userFile = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + userEmail + "accountInfo.txt"));
            userPassword = userFile.readLine(); // grabs password
            userName = userFile.readLine(); //grabs user's name
            userFile.close();

            stream.writeToConsole("Log-In Password: ");

            if(!stream.readLineFromConsole().matches(userPassword)){
                stream.writeToConsole("Incorrect Password.\n");
                changeName();
            }
            else{
                name = setAndGetNewName();

            /* REPLACE AND UPDATE IN FILE */
                FileOutstream updateName = new FileOutstream();
                updateName.updateName(userEmail, userName, name);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
