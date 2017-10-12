package app;

import static org.junit.Assert.*;
import org.junit.Test;

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
}