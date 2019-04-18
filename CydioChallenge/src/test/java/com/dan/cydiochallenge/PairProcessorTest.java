package com.dan.cydiochallenge;

import org.dan.cydiochallenge.PairProcessor;
import org.dan.cydiochallenge.PairProcessor.Pair;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Loretta
 */
public class PairProcessorTest {
    
    /**
     * Show the basic list of integers that create the appropriate pairs.
     */
    @Test
    public void success()
    {
        Integer data[] = {1,2,3,4,5,6};
        Pair pairs[] = PairProcessor.makePairs(data);
        assertEquals("There are only 3 pairs: ", 3, pairs.length);
        StringBuilder sb = new StringBuilder();
        for (Pair pair : pairs)
        {
            if (sb.length() > 0)
            {
                sb.append(",");
            }
            sb.append(pair.toString());
            // Each pair must total 7.
            assertEquals("is 7", 7, pair.getI1() + pair.getI2());
        }
        // Display the results
        System.out.println("Pairs: " + sb);
    }
    
    /**
     * Include testing for negative values in the sequence.
     */
    @Test
    public void successNegative()
    {
        Integer data[] = {1,2,3,4,5,6,12,-5};
        Pair pairs[] = PairProcessor.makePairs(data);
        assertEquals("There are only 4 pairs: ", 4, pairs.length);
        StringBuilder sb = new StringBuilder();
        for (Pair pair : pairs)
        {
            if (sb.length() > 0)
            {
                sb.append(",");
            }
            sb.append(pair.toString());
            // Each pair must total 7.
            assertEquals("is 7", 7, pair.getI1() + pair.getI2());
        }
        // Display the results
        System.out.println("Pairs: " + sb);
    }
}
