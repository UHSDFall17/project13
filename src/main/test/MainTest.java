import static org.junit.Assert.*;
import org.junit.Test;
import org.omg.CORBA.Any;
import setup.AnyDo;

public class MainTest {
    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testAnyDoInitialization()
    {
        _main test = new _main();
        assertNotNull(test);
    }
}
