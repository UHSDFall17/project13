import app.Dashboard;
import setup.*;

public class _main
{
    public static void main(String args[])
    {
        //main.java.setup.anydo start = new main.java.setup.anydo();
        //Create newAccount = new Create();
        //ResetPswd recoverForgotten = new ResetPswd();
        //Dashboard test = new Dashboard();
        WriteToFile test = new WriteToFile();
        test.saveNewAccount("gmail.com","gello", "ramon", 1,"yes", 7, "no");
    }
}
