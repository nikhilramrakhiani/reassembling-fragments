package com.raulavila.fragments;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class LineReassemblerIT {

    private LineReassembler lineReassembler = new LineReassembler();
    
    @Test
    public void testShortExample() throws Exception {
        String input = getFileContent("/example1/input.txt");
        String expectedOutput = getFileContent("/example1/output.txt");

        String output = lineReassembler.reassemble(input);

        assertThat(output).isEqualTo(expectedOutput);

    }
  
    @Test
    public void testLongExample() throws Exception {
        String input = getFileContent("/example2/input.txt");
        String expectedOutput = getFileContent("/example2/output.txt");

        String output = lineReassembler.reassemble(input);

        assertThat(output).isEqualTo(expectedOutput);

    }
    
    private String getFileContent(String filePath) throws Exception {
        String input = IOUtils.toString(
                this.getClass().getResourceAsStream(filePath),
                "UTF-8");

        return input;
    }
}
