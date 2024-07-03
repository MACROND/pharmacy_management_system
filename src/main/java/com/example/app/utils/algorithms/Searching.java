package com.example.app.utils.algorithms;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class Searching {
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
