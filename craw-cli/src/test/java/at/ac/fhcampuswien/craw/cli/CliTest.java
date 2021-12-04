package at.ac.fhcampuswien.craw.cli;

import at.ac.fhcampuswien.craw.cli.exceptions.CliException;
import at.ac.fhcampuswien.craw.cli.model.TestOutput;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CliTest {
    private static TestOutput sysOut;

    @BeforeAll
    static void init() {
        sysOut = new TestOutput();
        App.sysOut = sysOut;
    }

    @Test
    void errorMessageWhenNoCommandSpecified() throws Exception {
        // arrange
        CliException e = CliException.ForNoArgumentsProvided();

        // act
        App.main(new String[]{});

        // assert
        assertEquals(e.getErrorMessage(), sysOut.popErrOut());
    }

    @Test
    void printErrorTextWhenIncorrectCommand() {
        // arrange

    }
}
