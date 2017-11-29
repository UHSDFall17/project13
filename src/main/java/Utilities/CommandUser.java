package Utilities;

import java.io.IOException;

public interface CommandUser {

    public boolean commandHandler();

    public int commandCenter(int command, String availableCommands) throws IOException;

}
