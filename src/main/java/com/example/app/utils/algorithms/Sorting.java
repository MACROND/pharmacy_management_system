package com.example.app.utils.algorithms;

import java.util.Comparator;
import java.util.List;

/**
 * The `Sorting` class provides a generic method to sort a list of elements
 * using the insertion sort
 * algorithm.
 * 
 * @params list is a list of generic type T
 * @params comparator is a comparator of generic type T,
 *         which allows sorting the list in ascending or descending order based
 *         on the
 *         specified class attribute.
 */
public class Sorting {
    /**
     * The function `sort` implements insertion sort algorithm to sort a list of
     * elements using a provided
     * comparator.
     * 
     * @param list       A list of elements of type T that you want to sort.
     * @param comparator A `Comparator` is an interface in Java that is used to
     *                   define a comparison
     *                   function that can be used to compare objects of a specific
     *                   type. It provides a way to sort elements
     *                   in a collection based on a custom ordering defined by the
     *                   comparator.
     * @return The `sort` method is returning the sorted `List<T>` after performing
     *         insertion sort on the
     *         input list.
     */
    public static <T> List<T> sort(List<T> list, Comparator<? super T> comparator) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            T key = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
        return list;
    }

    /**
     * The function `sortDescending` sorts a given list in descending order using
     * the provided
     * comparator.
     * 
     * @param list       A list of elements that you want to sort in descending
     *                   order.
     * @param comparator A `Comparator` object that defines the ordering of the
     *                   elements in the list.
     *                   It is used to compare elements in the list to determine
     *                   their relative order for sorting.
     * @return The `sortDescending` method returns a sorted list in descending order
     *         based on the
     *         provided comparator.
     */
    public static <T> List<T> sortDescending(List<T> list, Comparator<? super T> comparator) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            T key = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), key) < 0) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
        return list;
    }

}
