package com.example.app.utils.helpers;

import com.example.app.entities.Drug;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Searching {

    public static int searchDrug(List<Drug> drugs, Comparator<Drug> comparator, Drug key) {
        int low = 0;
        int high = drugs.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = comparator.compare(drugs.get(mid), key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid; // Found the drug
            }
        }
        return -1; // Drug not found
    }
}
