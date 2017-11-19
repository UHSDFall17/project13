package setup;

import java.io.*;
import java.util.Scanner;

public class Name {
    Scanner input = new Scanner(System.in);

    private String userEmail, userPassword, userName; //FOR verifying user
    protected String name;

    public Name(){}

    public String setAndGetNewName(){
        do {
            System.out.print("Name: ");
            name = input.nextLine().toUpperCase();
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

            System.out.print("Log-In Password: ");

            if(!input.nextLine().matches(userPassword)){
                System.out.println("Incorrect Password.");
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
