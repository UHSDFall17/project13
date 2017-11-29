package Utilities;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CommandsTest {
    private Commands commands;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testGetSizeOfCommandsOffered_Correct(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        assertEquals(3, commands.size());
    }

    @Test
    public void testGetSizeOfCommandsOffered_Wrong(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        assertNotEquals(4, commands.size());
    }

    @Test
    public void testAddCommand_Wrong(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        commands.addCommand(testCommandsOffered.length+1,"Command D");
        assertNotEquals("List of commands available:\n1 - Command A\n2 - Command B\n3 - Command C\n", commands.toString()); //missing new command
    }

    @Test
    public void testAddCommand_Correct(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        commands.addCommand(testCommandsOffered.length+1,"Command D");
        assertEquals("List of commands available:\n1 - Command A\n2 - Command B\n3 - Command C\n4 - Command D\n", commands.toString());
    }

    @Test
    public void testCommandsToString_Correct(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        assertEquals("List of commands available:\n1 - Command A\n2 - Command B\n3 - Command C\n", commands.toString());
    }

    @Test
    public void testCommandsToString_Wrong(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        assertNotEquals("List of commands available:\n1 - Command A\n2 - Command B\n3 - Command C", commands.toString()); //missing \n (end line)
    }

    @Test
    public void testCommandsHashMapPut_Correct(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        Map<Integer, String> hashMap = new HashMap<Integer, String>();
        hashMap.put(1, "Command A");
        hashMap.put(2, "Command B");
        hashMap.put(3, "Command C");
        assertEquals(commands.getCommands(), hashMap);
    }

    @Test
    public void testCommandsHashMapPut_Wrong(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C", "Command D"};
        commands = new Commands(testCommandsOffered);
        Map<Integer, String> hashMap = new HashMap<Integer, String>();
        hashMap.put(1, "Command A");
        hashMap.put(2, "Command B");
        hashMap.put(3, "Command C");
        assertNotEquals(commands.getCommands(), hashMap); //commands has 4 commands, hashmap has 3
    }
}
