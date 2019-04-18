package org.dan.cydioticker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Process each ticker file to show the final price for each CUSIP.
 * With no parameters the file is read from standard in and written to standard
 * out.  If parameters are given, each is considered a file containing ticker
 * values.  The output is the same file name with '.out' appended.  The contents
 * include the CUSIP with the final prices for each per line.
 * @author Dan
 */
public class App {
    
    static TickerReader tr = new TickerReader();
    
    // sysin and sysout are useful with unit testing.
    static InputStream sysin = System.in;
    static PrintStream sysout = System.out;
    static PrintStream syserr = System.err;
    
    public static void main(String args[])
    {
        // If args are given, process each as a file.
        if (args.length > 0)
        {
            for (String filename: args)
            {
                // for each argument process the file to filename.out.
                try {
                    InputStream in = new FileInputStream(filename);
                    PrintStream out = new PrintStream(filename+".out");
                    tr.process(in, out);
                } catch (FileNotFoundException ex) {
                    syserr.println("Error: " + ex.getLocalizedMessage());
                }
            }
        } else
        {
            // process from stanndard in to standard out.
            tr.process(sysin, sysout);
        }
    }
}
