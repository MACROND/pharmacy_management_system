package com.example.app.utils.algorithms;

import com.example.app.entities.Drug;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class Searching {
    public static <T> int binarySearch(List<T> list, Comparator<T> comparator, T key) {
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
                return mid; // Found the element
            }
        }
        return -1; // Element not found
    }


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
