package app;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class DashboardTest
{
    Dashboard test;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void StoreNewListSuccess()
    {
        test = new Dashboard();
        assertTrue(test.storeNewList("Yes"));
    }

    @Test
    public void StoreNewListFailure()
    {
        test = new Dashboard();
        assertFalse(test.storeNewList(""));
    }

    @Test
    public void GetAllLists()
    {
        test = new Dashboard();
        assertEquals(test.displayLists(), "\nNo lists");
    }

    @Test
    public void GetAllListsNotEmpty()
    {
        test = new Dashboard();
        test.storeNewList("test list");

        assertNotEquals(test.displayLists(), "\nNo lists");
    }

    @Test
    public void DeleteList()
    {
        test = new Dashboard();
        test.storeNewList("test list");
        test.getLists().remove(0);

        assertEquals(test.displayLists(), "\nNo lists");
    }
}