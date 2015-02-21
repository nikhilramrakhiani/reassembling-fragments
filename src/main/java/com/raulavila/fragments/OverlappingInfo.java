package com.raulavila.fragments;

import org.apache.commons.lang3.StringUtils;

public class OverlappingInfo implements Comparable<OverlappingInfo>{

    public static OverlappingInfo NO_OVERLAPPING =  
                            new OverlappingInfo(StringUtils.EMPTY,
                                                StringUtils.EMPTY,
                                                OverlappingState.NO_OVERLAPPING, 0);

    private final String first;
    private final String second;
    private final OverlappingState overlappingSate;
    private final int overlappingLength;

    public OverlappingInfo(String first, String second,
                            OverlappingState overlappingType, int overlappingLength) {
        this.first = first;
        this.second = second;
        this.overlappingSate = overlappingType;
        this.overlappingLength = overlappingLength;
    }

    @Override
    public int compareTo(OverlappingInfo other) {
        return overlappingLength - other.overlappingLength;
    }

    public String getOverlappedFragments() {
        return overlappingSate.overlapFragments(first, second, overlappingLength);
    }

}
