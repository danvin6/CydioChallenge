package org.dan.cydioticker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomStringUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

/**
 *
 * @author Dan
 */
public class TickerReaderTest {

    /**
     * A very simple test for the ticker reader to show one CUSIP with the final
     * prices. Also includes one line that is not formatted correctly and will
     * be skipped.
     */
    @Test
    public void tickerReader() {
        TickerReader tr = new TickerReader();
        InputStream in = new ByteArrayInputStream("test\n12345678\n10.2\n12.2".getBytes());
        OutputStream output = new ByteArrayOutputStream();
        PrintStream outstr = new PrintStream(output);
        tr.process(in, outstr);
        String result = output.toString();
        assertEquals("Checking final ticker", "12345678: 12.2\n", result);
    }

    /**
     * Shows being able read a ticker file and creating the output file.
     *
     * @throws FileNotFoundException
     */
    @Test
    public void tickerReadFiles() throws FileNotFoundException, IOException {
        File testFile = new File("target/test-classes/Ticker.txt");
        PrintStream printFile = new PrintStream(testFile);
        printFile.print("12345678\n"
                + "1.23\n"
                + "1.234\n"
                + "1.3\n"
                + "1.54\n"
                + "3828AA33\n"
                + "28.332\n"
                + "28.223\n"
                + "24.423\n");
        printFile.close();
        File inputfile = new File("target/test-classes/Ticker.txt");
        InputStream in = new FileInputStream(inputfile);
        File outputfile = new File("target/test-classes/Ticker.out");
        PrintStream outstr = new PrintStream(outputfile);
        TickerReader tr = new TickerReader();
        tr.process(in, outstr);
        in.close();
        outstr.close();
        File outFile = new File("target/test-classes/Ticker.out");
        InputStream testStream = new FileInputStream(outFile);
        Scanner scanner = new Scanner(testStream);
        String test = scanner.nextLine();
        assertEquals("first line", "12345678: 1.54", test);
        test = scanner.nextLine();
        assertEquals("second line", "3828AA33: 24.423", test);
        assertFalse("not more lines", scanner.hasNext());
    }

    /**
     * Generates a large file to be processed by TickerReader.
     *
     * @throws FileNotFoundException
     */
    @Test
    public void largeFileGenerator() throws FileNotFoundException, IOException {
        Random r = new Random();
        IntStream intstr = r.ints(100_000, 1, 50);
        PrintStream out = new PrintStream("target/test-classes/LargeTicker.txt");
        PrimitiveIterator.OfInt intiter = intstr.iterator();
        while (intiter.hasNext()) {
            int nPrices = intiter.nextInt();
            String cusip = RandomStringUtils.random(8, true, true);
            out.println(cusip);
            for (int j = 0; j < nPrices; j++) {
                double random = Math.random() * 1000;
                out.println(random);
            }
        }
        File inputfile = new File("target/test-classes/LargeTicker.txt");
        InputStream in = new FileInputStream(inputfile);
        File outputfile = new File("target/test-classes/LargeTicker.out");
        PrintStream outstr = new PrintStream(outputfile);
        TickerReader tr = new TickerReader();
        tr.process(in, outstr);
        in.close();
        outstr.close();
    }

}
