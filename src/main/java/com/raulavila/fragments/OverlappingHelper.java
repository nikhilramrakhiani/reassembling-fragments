package com.raulavila.fragments;

import com.google.common.collect.Sets;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class OverlappingHelper {

    private static final Comparator<String> stringLengthComparator = 
            new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.length() - s2.length();
                }
            };
    
    
    public static OverlappingInfo getMaxOverlappingInfo(String first, String second) {

        if(first.equals(second))
            return new OverlappingInfo(first, second, OverlappingState.EQUALS, first.length());

        OverlappingState overlappingType = null;
        int maxOverlappingLength = 0;

        if(first.length() > second.length() &&  first.contains(second)) {
            overlappingType = OverlappingState.FIRST_CONTAINS_SECOND;
            maxOverlappingLength = second.length();
        }
        else if(second.length() > first.length() &&  second.contains(first)) {
            overlappingType = OverlappingState.SECOND_CONTAINS_FIRST;
            maxOverlappingLength = first.length();
        }

        int overlappingLength = getMaxOverlappingLength(first, second);
        if(overlappingLength > maxOverlappingLength) {
            overlappingType = OverlappingState.FIRST_PRECEDING_SECOND;
            maxOverlappingLength = overlappingLength;
        }

        overlappingLength = getMaxOverlappingLength(second, first);
        if(overlappingLength > maxOverlappingLength) {
            overlappingType = OverlappingState.SECOND_PRECEDING_FIRST;
            maxOverlappingLength = overlappingLength;
        }

        if(maxOverlappingLength == 0)
            return OverlappingInfo.NO_OVERLAPPING;
        else
            return new OverlappingInfo(first, second, overlappingType, maxOverlappingLength);

    }

    private static int getMaxOverlappingLength(String begin, String end) {

        Set<String> suffixes = getSuffixes(begin);
        Set<String> prefixes = getPrefixes(end);

        suffixes.retainAll(prefixes);

        if(suffixes.isEmpty()) {
            return 0;
        }
        else {
            SortedSet<String> overlappedSequences = new TreeSet<String>(stringLengthComparator);
            overlappedSequences.addAll(suffixes);

            return overlappedSequences.last().length();
        }
    }

    private static Set<String> getPrefixes(String fragment) {

        Set<String> prefixes = Sets.newHashSet();

        int size = fragment.length();

        StringBuilder builder = new StringBuilder(size);

        for (int i = 0; i < size - 1; i++) {
            builder.append(fragment.charAt(i));
            prefixes.add(builder.toString());
        }

        return prefixes;
    }

    private static Set<String> getSuffixes(String fragment) {

        Set<String> suffixes = Sets.newHashSet();

        int size = fragment.length();

        StringBuilder builder = new StringBuilder(size);

        for (int i = size - 1; i >= 1; i--) {
            builder.insert(0, fragment.charAt(i));
            suffixes.add(builder.toString());
        }

        return suffixes;
    }
}
