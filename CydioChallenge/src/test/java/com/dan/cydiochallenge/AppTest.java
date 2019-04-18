package com.dan.cydiochallenge;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.dan.cydiochallenge.App;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author Dan
 */
public class AppTest {

    /**
     * Show that invalid data will not be processed.
     */
    @Test
    public void textError() {
        InputStream in = new ByteArrayInputStream("1,X,Y,4,5 ,6".getBytes());
        Integer[] data = App.readstdin(in);
        assertNull("not data returned:", data);
    }

    /**
     * Given a comma separated set of integers, give the data in an Integer array.
     */
    @Test
    public void readIn() {
        InputStream in = new ByteArrayInputStream("1,2,3,4,5 ,6".getBytes());
        Integer[] data = App.readstdin(in);
        assertEquals("Length: ", 6, data.length);
        for (int i = 1; i <= 6; i++) {
            assertEquals("data test:", new Integer(i), data[i - 1]);
        }
    }

    /**
     * Verify negative numbers will be accepted.
     */
    @Test
    public void readnegative() {
        InputStream in = new ByteArrayInputStream("1,2,3,4,5 ,6,12,-5".getBytes());
        Integer[] data = App.readstdin(in);
        assertEquals("Length: ", 8, data.length);
        for (int i = 1; i <= 6; i++) {
            assertEquals("data test:", new Integer(i), data[i - 1]);
        }
        assertEquals("data test:", (int) 12, (int) data[6]);
        assertEquals("data test:", (int) -5, (int) data[7]);
    }
}
