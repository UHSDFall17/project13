package setup;
import Utilities.Commands;
import app.*;
import Utilities.Stream;
import Utilities.CommandUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

import static java.lang.System.exit;
import static java.lang.System.out;


public class AnyDo implements CommandUser
{
    private String lastLogin;
    private Dashboard dashboard;
    private Account account;
    private Stream stream;
    private Commands commands;
    private User user;
    private Gson gson;

    public AnyDo()
    {
        stream = new Stream();
        splashScreen();
        initCommands();
        startUp();
    }

    private void splashScreen()
    {
        stream.writeToConsole(" __    _   _                              _,                     \n" +
                "( /   /   //                   _/_       / |                  /  \n" +
                " / / /_  // _, __ _ _ _   _    /  __    /--|  _ _   __  ,  __/ __\n" +
                "(_/_/(/_(/_(__(_)/ / / /_(/_  (__(_)  _/   |_/ / /_/ (_/_o(_/_(_)\n" +
                "                                                      /          \n" +
                "                                                     '           \n");
    }

    private void initCommands()
    {
        commands = new Commands();
        commands.addCommand(1,"Login");
        commands.addCommand(2,"Create account");
        commands.addCommand(3,"Help");
        commands.addCommand(4,"Quit");
    }

    //handles startup of app, will handle whether to go to login screen or dashboard
    public void startUp()
    {
        String fileUserName = "";

        try(BufferedReader fileReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + "LastLogin.txt"))) {

            fileUserName = fileReader.readLine();

            if(fileUserName == null || fileUserName.isEmpty()) //checking if valid file name is found
                throw new IOException();

            //dashboard = new Dashboard(fileUserName);
            //dashboard.commandHandler();
            user = Account.getUserInfo(fileUserName);
            dashboardHandler();
            commandHandler();
            //System.out.println(fileUserName);
        }
        catch(IOException e)
        {
            //go to login screen here
            commandHandler();
        }

    }

    public boolean commandHandler()
    {
        boolean cont = true;
        int command = 0;
        int commandReturn = 0;

        String availableCommands = commands.toString();

        do {
            stream.writeToConsole("\n(Any.do) "+ availableCommands);
            stream.writeToConsole("\n(Any.do) "); //DISPLAY PAGE NAME
            stream.writeToConsole("Press \"" + (commands.size() - 1) + "\" to Display Available Commands.\nEnter your command: ");

            try{
                command = stream.readIntFromConsole();

                if(command > commands.size())
                    throw new Exception();

                commandReturn = commandCenter(command, availableCommands);
            }
            catch (Exception e)
            {
                stream.writeToConsole("Unrecognized command. Try to use the command \"" + (commands.size() - 1) + "\" to get a list of the commands.\n");
            }

        }while(cont);

        return false;
    }

    public int commandCenter(int command, String availableCommands) throws IOException {
        switch (command)
        {
            case 1: loginHandler(); break;
            case 2: createAccountHandler(); break;
            case 3: stream.writeToConsole("\n(Any.do) "+ availableCommands); break;
            case 4: exit(0); break;
        }

        return 0;
    }

    private void loginHandler() throws IOException {
        account = new Account();
        user = account.logIn();

        if(user != null) {
            //dashboard = new Dashboard();
            //dashboard.commandHandler();
            try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/Accounts/" + "LastLogin.txt"))) {

                fileWriter.write(user.getUsername());
            }
            catch(IOException e)
            {
                stream.writeToConsole("Error occurred while logging in!");
            }

            dashboardHandler();
        }
        else{
            commandHandler();
        }
    }

    private void createAccountHandler() throws IOException {
        account = new Account();
        account.createNewAccount();
    }

    private void dashboardHandler() throws IOException {
        boolean loggedOut = false;

        /* READ OLD, WRITE UPDATED JSON FILE*/
        gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = "Accounts/" + user.getUsername() + "/data.json";
        try{
            /* CHECK FOR EMPTY FILE */
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            if (reader.readLine() == null) {
                stream.writeToConsole("Something's wrong!\nPlease contact our Office to restore your data.\nWe apologize for the inconvenience.\n");

                /*CONTACT INFO*/
                String ourBusinessHours = "Monday-Friday 9AM - 5PM";
                String ourEmail = "skgiang@uh.edu, ramirez.roberto45@gmail.com, tom_t_huynh@outlook.com";
                String ourTelephone = "713-743-2611";
                String ourFacebook="https://www.facebook.com/uhnsm";
                // PRINT CONTACT INFO
                stream.writeToConsole("\nBusiness Hours: " + ourBusinessHours);
                stream.writeToConsole("\n\tEmail Us: " + ourEmail);
                stream.writeToConsole("\n\tCall Us: " + ourTelephone);
                stream.writeToConsole("\n\tJoin Us: " + ourFacebook);

                stream.writeToConsole("\n\nPress Enter to exit...");
                stream.readLineFromConsole();
                System.exit(0);
            }
            reader.close();


            FileReader fileReader = new FileReader(filePath);
            dashboard = gson.fromJson(fileReader, Dashboard.class);
            reader.close();

            loggedOut = dashboard.commandHandler();

            FileWriter writer = new FileWriter(filePath);
            gson.toJson(dashboard, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* EXITING DASHBOARD*/
        if(loggedOut)
        {
            try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/Accounts/" + "LastLogin.txt"))) {

                fileWriter.write("");
            }
            catch(IOException e)
            {
                //go to login screen here
                stream.writeToConsole("Error occurred while logging out!");
            }
        }
        else
        {
            user = Account.getUserInfo(user.getUsername());
            stream.writeToConsole("\nGOODBYE, "+ user.getName() + "!");
            commandCenter(4, "");
        }
    }
}
