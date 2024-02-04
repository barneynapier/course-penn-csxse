package hw1;

import java.util.LinkedList;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {

    public static String getMax(LinkedList<String> list) {
        /*
        Return max value in the LinkedList<String>
        */

        String max = list.getFirst();

        for (String str : list) {
            if (str.compareTo(max) > 0) {
                max = str;
            }
        }

        return max;

    }

    public static void insertSorted(LinkedList<Integer> list, int value) {

        /*
        This method assumes the input LinkedList is already sorted in non-descending order
        (i.e.,such that each element is greater than or equal to the one that is before it,
        and inserts the input int value into the correct location of the list.
        Note that the method does not return anything, but rather modifies the input LinkedList as a side effect.
        If the input LinkedList is null, this method should simply terminate.
        */

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).compareTo(value) > 0) {
                    list.add(i, value);
                    break;
                }
                else if (i == list.size()-1) {
                    list.addLast(value);
                }
            }
        }

    }


    public static void removeMaximumValues(LinkedList<String> list, int N) {

        /*
        This method removes all instances of the N largest values in the LinkedList.
        Because the values are Strings, you will need to use the
        String class’ compareTo method to find the largest elements;
        see the Java API for help with that method.
        If the input LinkedList is null or if N is non-positive,
        this method should simply return without any modifications to the input LinkedList.
        Keep in mind that if any of the N largest values appear more than once in the LinkedList,
        this method should return remove all instances, so it may remove more than N elements overall.
        The other elements in the LinkedList should not be modified and their order must not be changed.
        */

        if (list != null && list.size() > 0) {
            for (int n = 0; n < N; n++) {
                // Get max value in list
                String max = getMax(list);
                // Remove all instances of it
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(max)) {
                        list.remove(i);
                    }
                }
            }
        }
    }

    public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {

        /*
        This method determines whether any part of the first LinkedList contains all
        elements of the second in the same order with no other elements in the sequence,
        i.e. it should return true if the second LinkedList is a subsequence of the first,
        and false if it is not. The method should return false if either input is null or empty.
        */

        if (one == null || two == null || one.isEmpty() || two.isEmpty()) {
            return false;
        }

        // Loop through all subsets of one that have length two.size()
        for (int i = 0; i <= one.size() - two.size(); i++) {

            boolean hasSubset = true;

            for (int j = 0; j < two.size(); j++) {
                if (one.get(i+j) != two.get(j)) {
                    hasSubset = false;
                    break;
                }
            }

            if (hasSubset) {
                return true;
            }

        }

        return false; // this line is here only so this code will compile if you don't modify it
    }
}
