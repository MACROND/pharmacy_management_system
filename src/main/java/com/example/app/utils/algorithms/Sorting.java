package com.example.app.utils.algorithms;

import java.util.Comparator;
import java.util.List;

public class Sorting {
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
}
