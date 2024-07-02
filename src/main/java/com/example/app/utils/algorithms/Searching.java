package com.example.app.utils.algorithms;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class Searching {
    //   Used when searching an object with a numeric ID
    public static <T> Object binarySearch(List<T> list, Comparator<T> comparator, T key) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = comparator.compare(list.get(mid), key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return list.get(mid); // Found the element
            }
        }
        return list; // Element not found
    }


    // Used when looking for an object
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
