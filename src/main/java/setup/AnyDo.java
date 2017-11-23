package setup;
import Utilities.Commands;
import app.*;
import Utilities.Stream;
import Utilities.CommandUser;
import com.google.gson.Gson;

import java.io.*;

import static java.lang.System.exit;
import static java.lang.System.out;


public class AnyDo implements CommandUser
{
    private String lastLogin;
    private Dashboard dashboard;
    private Account account;
    //private Login login;
    private Stream stream;
    private Commands commands;
    private User user;

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

        do {
            stream.writeToConsole("\n(Any.do) "+commands.toString() + "\n");
            stream.writeToConsole("(Any.do)Enter your command: ");

            try{
                command = stream.readIntFromConsole();

                if(command > commands.size())
                    throw new Exception();

                commandReturn = commandCenter(command);
            }
            catch (Exception e)
            {
                stream.writeToConsole("Unrecognized command. Try to use the command \"" + (commands.size() - 1) + "\" to get a list of the commands.\n");
            }

        }while(cont);

        return false;
    }

    public int commandCenter(int command)
    {
        switch (command)
        {
            case 1: loginHandler(); break;
            case 2: createAccountHandler(); break;
            case 3: stream.writeToConsole("(Any.do) "+commands.toString() + "\n"); break;
            case 4: exit(0); break;
        }

        return 0;
    }

    private void loginHandler()
    {
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
    }

    private void createAccountHandler()
    {
        account = new Account();
        account.createNewAccount();
    }

    private void dashboardHandler()
    {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("Accounts/" + user.getUsername() + "/data.json")) {
            // Convert JSON to Java Object
            dashboard = gson.fromJson(reader, Dashboard.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean loggedOut = dashboard.commandHandler();

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
            stream.writeToConsole("Goodbye, "+ user.getName());
            commandCenter(4);
        }

        //saving will go here...
    }
}
