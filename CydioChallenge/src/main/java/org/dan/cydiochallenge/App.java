package org.dan.cydiochallenge;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Process a integer list to show the pairs that add to 7.
 * Input is a single line of comma separated integers, either as a parameter,
 * or from standard in.
 * Output is pairs of numbers from list that are unique (even inverted) that
 * total 7.
 * @author Dan
 */
public class App {
    
    public static void main(String args[])
    {
        Integer data[];
        switch (args.length) {
            case 0:
                // With no arguments read from standard in.
                data = readstdin(System.in);
                break;
            case 1:
                // Use the given argument as the number sequence.
                data = readstdin(new ByteArrayInputStream(args[0].getBytes()));
                break;
            default:
                // All other parameters are not valid.
                System.out.println("Invalid usage:");
                System.out.println(" args: {number sequence]");
                System.out.println(" number sequence is a list of number comma separted as a single argument.");
                return;
        }
        if (data == null)
        {
            System.exit(-1);
        }
        //  Run the PairProcessor.
        PairProcessor.Pair[] pairs = PairProcessor.makePairs(data);
        StringBuilder sb = new StringBuilder();
        for (PairProcessor.Pair pair : pairs)
        {
            if (sb.length() > 0)
            {
                sb.append(",");
            }
            sb.append(pair.toString());
        }
        System.out.println(sb);
    }

/**
 * From the input stream, create an array of integers.
 * 
 * @param in is a stream of comma separated integers (positive or negative).
 * @return An array of the integers.
 */    
    public static Integer[] readstdin(InputStream in)
    {
        Integer data[] = null;
        Scanner input = new Scanner(in);
        if (input.hasNext())
        {
            String line = input.nextLine();
            String[] elements = line.split(",");
            ArrayList<Integer> elemArr = new ArrayList();
            for (String element : elements)
            {
                // Verify integer format (negative or positive).
                if (element.trim().matches("[-]{0,1}[0-9]*"))
                {
                    elemArr.add(new Integer(element.trim()));
                } else
                {
                    System.err.println("Invalid number found: " + element);
                    return null;
                }
            }
            data = new Integer[elemArr.size()];
            data = elemArr.toArray(data);
        }
        return data;
    }
}
