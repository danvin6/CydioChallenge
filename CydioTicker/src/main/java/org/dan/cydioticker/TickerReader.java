package org.dan.cydioticker;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * TickerReader reads through the input stream looking for each CUSIP and finds
 * the final price of the day.
 * CUSIP is an 8 character alpha numeric value (it is assumed no puncuation.)
 * The ticker price is a positive decimal value that includes a decimal point.
 * The output stream will be the CUSIP value with a colon and the final price
 * for that item shown per line in the output file.
 * @author Dan
 */
public class TickerReader {

    public void process(InputStream in, PrintStream out) {
        Scanner scanner = new Scanner(in);
        String cusip = null;
        String price = null;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            // Regex format for alpha numeric of exactly 8 characters.
            if (line.matches("[A-Za-z0-9]{8}")) {
                // If the new cusip is found, the print the final price
                // of the previous CUSIP.
                if (cusip != null && price != null) {
                    out.println(cusip + ": " + price);
                }
                cusip = line;
            }
            // Regex for decimal value that includes the decimal point.
            if (line.matches("[0-9].*\\.[0-9].*")) {
                price = line;
            }

        }
        // when the end of the file is reached, print the final CUSIP and price.
        if (cusip != null && price != null) {
            out.println(cusip + ": " + price);
        }
    }
}
