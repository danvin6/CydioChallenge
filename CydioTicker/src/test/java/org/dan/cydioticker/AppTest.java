package org.dan.cydioticker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import static org.dan.cydioticker.App.main;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dan
 */
public class AppTest {
    
    @Test
    public void noFile()
    {
        App.sysin = new ByteArrayInputStream("test\n12345678\n10.2\n12.2".getBytes());
        OutputStream output = new ByteArrayOutputStream();
        App.sysout = new PrintStream(output);
        App.main(new String[0]);
        String results = output.toString();
        assertEquals("standard in/out test", "12345678: 12.2\n", results);
    }

    @Test
    public void oneFile()
    {
        File oldFile = new File("target/test-classes/Ticker.txt.out");
        oldFile.delete();
        App.main(new String[] {"target/test-classes/Ticker.txt"});
        assertTrue("outfile created:", oldFile.exists());
    }
}
