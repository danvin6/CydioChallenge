package org.dan.cydiochallenge;

import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author Dan
 */
public class PairProcessor {

    /**
     * Given a list of numbers, find all the pairs that add to 7.  This 
     * includes negative numbers.  Pairs exclude duplicates even if inverted.
     * @param data array of integers.
     * @return array of integer pairs.
     */
    public static Pair[] makePairs(Integer[] data) {

        HashSet<Pair> pairs = new HashSet();
        for (Integer data1 : data) {
            for (Integer data2 : data) {
                if (data1 + data2 == 7)
                {
                    Pair p = new Pair(data1, data2);
                    if (!pairs.contains(p))
                    {
                        pairs.add(p);
                    }
                }
            }
        }
        Pair[] pairArr = new Pair[pairs.size()];
        pairs.toArray(pairArr);
        return pairArr;
    }
    
    /**
     * POJO of integer pairs.  Each pair is considered unique even if inverted.
     * That is (1,6) is equal to (6,1).
     */
    public static class Pair {
        private Integer i1;
        private Integer i2;
        
        Pair(Integer i1, Integer i2)
        {
            this.i1 = i1;
            this.i2 = i2;
        }
        
        @Override
        public boolean equals(Object p)
        {
            if (!(p instanceof Pair))
                return false;
            Pair p1 = (Pair)p;
            return (Objects.equals(p1.getI1(), this.getI1()) &&
                    Objects.equals(p1.getI2(), this.getI2()))
                    ||
                    (Objects.equals(p1.getI1(), this.getI2()) &&
                    Objects.equals(p1.getI2(), this.getI1()));
        }
        
        @Override
        public int hashCode()
        {
            return getI1().hashCode() + getI2().hashCode();
        }
        
        @Override
        public String toString()
        {
            return "(" + getI1() + "," + getI2() + ")";
        }

        /**
         * @return the i1
         */
        public Integer getI1() {
            return i1;
        }

        /**
         * @param i1 the i1 to set
         */
        public void setI1(Integer i1) {
            this.i1 = i1;
        }

        /**
         * @return the i2
         */
        public Integer getI2() {
            return i2;
        }

        /**
         * @param i2 the i2 to set
         */
        public void setI2(Integer i2) {
            this.i2 = i2;
        }
    }
}
