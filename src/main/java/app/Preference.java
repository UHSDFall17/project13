package app;

import Utilities.CommandUser;
import Utilities.Commands;
import Utilities.Stream;
import setup.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Preference implements CommandUser{
    private Stream stream;
    private User user;

    public Preference(){
        stream = new Stream();
    }

    private String getUserEmail(){
        try(BufferedReader lastLogin = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/LastLogin.txt"))) {
            return lastLogin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean commandHandler() {
        Commands commands = new Commands();

        commands.addCommand(1, "Change Account Name");
        commands.addCommand(2, "Change Password");
        commands.addCommand(3, "Change Security Questions");
        commands.addCommand(4, "Completed Tasks");
        commands.addCommand(5, "Send us your Feedback!");
        commands.addCommand(6, "Help");
        commands.addCommand(7, "Back to Dashboard");

        String availableCommands = commands.toString();

        boolean cont = true;
        int command;
        int commandReturn = 0;

        Stream stream = new Stream();
        stream.writeToConsole("\n(Preference) "+ availableCommands);
        do
        {
            stream.writeToConsole("\n(Preference) "); //DISPLAY PAGE NAME
            stream.writeToConsole("Press \"" + (commands.size() - 1) + "\" to Display Available Commands.\nEnter your command: ");

            try{
                command = stream.readIntFromConsole();

                if(command > commands.size() || command == 0)
                    throw new Exception();

                commandReturn = commandCenter(command, availableCommands);
                if(commandReturn == 1 || commandReturn == 2)  //return 1 means quit, return 2 means logout
                    cont = false;
            }
            catch (Exception e)
            {
                stream.writeToConsole("Unrecognized command. Try to use the command \"" + (commands.size() - 1) + "\" to get a list of the commands.\n");
            }
        }while(cont);

        if(commandReturn == 2) //logout
            return true;
        else
            return false;  //quit without logging out
    }

    @Override
    public int commandCenter(int command, String availableCommands) {
        Account account = new Account();

        switch(command)
        {
            case 1: account.changeName(); break;
            case 2: account.changePassword(); break;
            case 3: account.changeSQ(); break;
            case 4: stream.writeToConsole("\n(Completed Tasks) Sorry for the inconvenience. This feature is unavailable right now.\n"); break;
            case 5: contactUs(); break;
            case 6: stream.writeToConsole("\n(Preference) " + availableCommands); break; //display available commands
            case 7: return 1; //back to dashboard
        }

        return 0;
    }


    private void contactUs(){
        stream.writeToConsole("\n(Send Us Feedback) Have any questions, comments, or concerns? Feel free to contact us!\n");

        String ourCompanyName = "University of Houston";
        String ourDepartmentName = "College of Natural Sciences and Mathematics";
        String ourBusinessHours = "Monday-Friday 9AM - 5PM";
        String ourEmail = "skgiang@uh.edu, ramirez.roberto45@gmail.com, tom_t_huynh@outlook.com";
        String ourTelephone = "713-743-2611";
        String ourFacebook="https://www.facebook.com/uhnsm";

        stream.writeToConsole("\n" + ourCompanyName.toUpperCase() + "--" + ourDepartmentName);
        stream.writeToConsole("\nBusiness Hours: " + ourBusinessHours);
        stream.writeToConsole("\n\tEmail Us: " + ourEmail);
        stream.writeToConsole("\n\tCall Us: " + ourTelephone);
        stream.writeToConsole("\n\tJoin Us: " + ourFacebook);
        stream.writeToConsole("\nWe're looking forward to hearing from you! :)\n");
    }

    public User getUpdatedUser(){
        return user;
    }
}
