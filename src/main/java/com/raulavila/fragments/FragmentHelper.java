package com.raulavila.fragments;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.StringTokenizer;

public class FragmentHelper {
    private static final String TOKEN_SEPARATOR = ";";
    
    public static List<String> getFragmentsList(String line) {
        List<String> fragments = Lists.newArrayList();

        StringTokenizer tokens = new StringTokenizer(line, TOKEN_SEPARATOR);

        while(tokens.hasMoreTokens()) {
            String token = tokens.nextToken();

            if(token.length() < 2)
                throw new IllegalArgumentException("Precondition violation. One of the fragments " +
                        "contains less than 2 characters");
            else
                fragments.add(token);
        }

        if(fragments.isEmpty())
            throw new IllegalArgumentException("Precondition violation. The line does not contain full " +
                    "valid fragments (invalid line example: ';;;')");

        return fragments;
    }

    public static void joinMostOverlappedFragments(List<String> fragments) {

        OverlappingInfo maxOverlappingInfo = OverlappingInfo.NO_OVERLAPPING;

        int firstMaxOverlapIndex = 0;
        int secondMaxOverlapIndex = 0;

        //from first element to next-to-last
        for(int i = 0, sizeList = fragments.size(); i < sizeList - 1; i++) {
            String first = fragments.get(i);

            //Check overlapping with the rest of the list (omitting previous positions,
            // they have been checked before)
            for(int j = i + 1; j < sizeList; j++) {
                String second = fragments.get(j);

                OverlappingInfo overlappingInfo =
                        OverlappingHelper.getMaxOverlappingInfo(first, second);

                if(overlappingInfo.compareTo(maxOverlappingInfo) > 0){
                    maxOverlappingInfo = overlappingInfo;
                    firstMaxOverlapIndex = i;
                    secondMaxOverlapIndex = j;
                }
            }
        }

        if(maxOverlappingInfo == OverlappingInfo.NO_OVERLAPPING) {
            throw new IllegalArgumentException("Precondition violation. It wasn't possible " +
                                                "to find two overlapped fragments");
        }
        else {
            String overlappedFragment = maxOverlappingInfo.getOverlappedFragments();

            //replace the first fragment with the result of the overlapping
            fragments.set(firstMaxOverlapIndex, overlappedFragment);

            //remove the second fragment
            fragments.remove(secondMaxOverlapIndex);
        }

    }
}
