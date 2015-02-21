package com.raulavila.fragments;

public enum OverlappingState {
    
    NO_OVERLAPPING {
        public String overlapFragments(String first, String second, int overlappingLength) {
            throw new UnsupportedOperationException("Impossible to overlap two fragments which don't overlap!");
        }
    },
    EQUALS {
        public String overlapFragments(String first, String second, int overlappingLength) {
            return first;
        }
    },
    FIRST_PRECEDING_SECOND {
        public String overlapFragments(String first, String second, int overlappingLength) {
            return overlapFragmentsHelper(first, second, overlappingLength);
        }
    },
    SECOND_PRECEDING_FIRST  {
        public String overlapFragments(String first, String second, int overlappingLength) {
            return overlapFragmentsHelper(second, first, overlappingLength);
        }
    },
    FIRST_CONTAINS_SECOND  {
        public String overlapFragments(String first, String second, int overlappingLength) {
            return first;
        }
    },
    SECOND_CONTAINS_FIRST  {
        public String overlapFragments(String first, String second, int overlappingLength) {
            return second;
        }
    };

    protected String overlapFragmentsHelper(String begin, String end, int overlappingLength) {
        StringBuilder builder = new StringBuilder(begin.length() + end.length() - overlappingLength);
        builder.append(begin);
        builder.append(end.substring(overlappingLength));
        return builder.toString();
    }

    public abstract String overlapFragments(String first, String second, int overlappingLength);
}
