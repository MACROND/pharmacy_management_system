package com.example.app.utils.algorithms;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * The class `Searching` contains a generic method `customSearch` that searches
 * for an item in a list
 * using a custom comparator.
 */
public class Searching {
    /**
     * The function `customSearch` searches for an item in a list based on a key
     * using a custom comparator.
     * 
     * @param list       A list of elements of type T that you want to search
     *                   through.
     * @param key        The `key` parameter is the value that you are searching for
     *                   within the list. It is used
     *                   to compare against the elements in the list using the
     *                   provided `Comparator` to determine if a match
     *                   is found.
     * @param comparator The `comparator` parameter in the `customSearch` method is
     *                   used to compare
     *                   elements in the list with the specified key. It is a
     *                   `Comparator<T>` type parameter, which means it
     *                   is a functional interface that defines a comparison
     *                   function for objects of type `T`.
     * @return The method `customSearch` is returning an item of type `T` from the
     *         input list based on the
     *         provided key and comparator. If the item is found in the list, it
     *         will return that item. If the item
     *         is not found, it will return `null`.
     */
    public static <T, K> T customSearch(List<T> list, K key, Comparator<T> comparator) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (comparator.equals(key)) {
                return item;
            }
        }
        return null; // Item not found
    }
}
