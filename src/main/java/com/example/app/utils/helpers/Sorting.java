package com.example.app.utils.helpers;

import com.example.app.entities.Drug;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;

public class Sorting{
    public static void sortDrugs(List<Drug> drugs, Comparator<Drug> comparator) {
        int n = drugs.size();
        for (int i = 1; i < n; i++) {
            Drug key = drugs.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(drugs.get(j), key) > 0) {
                drugs.set(j + 1, drugs.get(j));
                j = j - 1;
            }
            drugs.set(j + 1, key);
        }
    }
}

