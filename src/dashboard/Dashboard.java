package dashboard;

import static java.lang.System.*;

public class Dashboard
{
    private String userFileName;
    private String userName;

    public Dashboard()
    {
        this("default.txt");
    }

    public Dashboard(String fileName)
    {
        userFileName = fileName;
    }

    private String commandHandler()
    {
        out.println("Hello " + userName);

        return "";
    }

    private boolean GetUserData()
    {

        return false;
    }
}
