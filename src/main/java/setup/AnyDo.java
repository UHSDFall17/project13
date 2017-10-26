package setup;
import Utilities.Commands;
import app.*;
import Utilities.Stream;
import Utilities.CommandUser;

import java.io.*;

import static java.lang.System.exit;
import static java.lang.System.out;


public class AnyDo implements CommandUser
{
    private String lastLogin;
    private Dashboard dashboard;
    private Account create;
    private Login login;
    private Stream stream;
    private Commands commands;
    private String User;

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

            dashboard = new Dashboard(fileUserName);
            dashboard.commandHandler();
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
        out.println("\n(Any.do) "+commands.toString());

        do {
            stream.writeToConsole("(Any.do)Enter your command: ");

            try{
                command = stream.readIntFromConsole();

                if(command > commands.size())
                    throw new Exception();

                commandReturn = commandCenter(command);
                out.println("Test");
            }
            catch (Exception e)
            {
                out.println("Unrecognized command. Try to use the command \"" + (commands.size() - 1) + "\" to get a list of the commands");
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
            case 3: out.println("(Any.do) "+commands.toString()); break;
            case 4: exit(0); break;
        }

        return 0;
    }

    private void loginHandler()
    {
        login = new Login();
        User = login.access();

        if(User != "") {
            dashboard = new Dashboard();
            dashboard.commandHandler();
        }
    }

    private void createAccountHandler()
    {
        create = new Account();
        //stream.readLineFromConsole();
    }
}
