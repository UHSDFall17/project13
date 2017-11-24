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

    public int commandCenter(int command, String availableCommands)
    {
        switch (command)
        {
            case 1: loginHandler(); break;
            case 2: createAccountHandler(); break;
            case 3: stream.writeToConsole("\n(Any.do) "+ availableCommands); break;
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
        else{
            commandHandler();
        }
    }

    private void createAccountHandler()
    {
        account = new Account();
        account.createNewAccount();
    }

    private void dashboardHandler()
    {
        boolean loggedOut = false;

        /* READ OLD, WRITE UPDATED JSON FILE*/
        gson = new GsonBuilder().setPrettyPrinting().create();
        try{
            FileReader reader = new FileReader("Accounts/" + user.getUsername() + "/data.json");
            dashboard = gson.fromJson(reader, Dashboard.class);
            reader.close();

            loggedOut = dashboard.commandHandler();

            FileWriter writer = new FileWriter("Accounts/" + user.getUsername() + "/data.json");
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
            stream.writeToConsole("\nGoodbye, "+ user.getName() + "!");
            commandCenter(4, "");
        }
    }
}
