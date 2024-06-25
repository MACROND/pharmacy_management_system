package com.example.app.utils.algorithms;

import com.example.app.entities.Drug;

import java.util.Comparator;
import java.util.List;

public class Searching {
    public static int search(List<?> list, <?> key, Comparator<? super T> comparator ) {
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
                return mid; // Found the drug
            }
        }
        return; // Drug not found
    }

    /**
     * The function `searchDrug` uses binary search to find a specific drug in a
     * list based on a given
     * comparator and returns the index of the drug if found, otherwise returns -1.
     * 
     * @param drugs      The `drugs` parameter is a List of Drug objects. This
     *                   method is designed to search for
     *                   a specific Drug object within this list based on a given
     *                   key and a Comparator.
     * @param comparator The `Comparator` interface in Java is used to define a
     *                   comparison function that
     *                   can be used to compare objects of a specific type. In the
     *                   context of your `searchDrug` method, the
     *                   `Comparator<Drug>` parameter is used to compare `Drug`
     *                   objects based on a specific criteria defined
     *                   by the
     * @param key        The `key` parameter in the `searchDrug` method represents
     *                   the drug that you are searching
     *                   for within the list of drugs. The method uses the
     *                   `Comparator<Drug>` to compare the `key` drug with
     *                   the drugs in the list to determine its position or
     *                   existence in the list. If the
     * @return The method `searchDrug` returns the index of the drug in the list if
     *         it is found, or -1 if
     *         the drug is not found in the list.
     */
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
