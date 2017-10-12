import app.Dashboard;
import setup.*;

public class _main
{
    public static void main(String args[])
    {
        //main.java.setup.anydo start = new main.java.setup.anydo();
        //createAccount newAccount = new createAccount("WELCOME!");
        //forgotPswd recover = new forgotPswd();
        Dashboard test = new Dashboard();
        test.commandHandler();
        System.out.println("Logged out successully!");
    }
}
