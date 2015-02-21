package com.raulavila.fragments;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class LineReassembler {

    private static final int FIRST = 0;

    public String reassemble(String line) {
        validateLine(line);

        List<String> fragments = FragmentHelper.getFragmentsList(line);

        while(fragments.size() > 1) {
            FragmentHelper.joinMostOverlappedFragments(fragments);
        }

        return fragments.get(FIRST);
    }

    private void validateLine(String line) {
        if(isEmpty(line))
            throw new IllegalArgumentException("Precondition violation. Empty line");
    }
}
