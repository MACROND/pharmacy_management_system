package com.example.app.utils.algorithms;

import com.example.app.entities.Drug;

import java.util.Comparator;
import java.util.List;


public class Searching {
    public static <T> int search(List<T> list, Comparator<T> comparator, T key) {
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

}
