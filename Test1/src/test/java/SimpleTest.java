import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleTest {

    @Test
    public void testStart()
    {
        Simple simple = new Simple();
        boolean result = simple.CreatePostMethod();
        assertEquals(true,result);
    }

}