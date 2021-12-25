package at.ac.fhcampuswien.craw.cli;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchCommandTests extends CliTestBase {
    /**
     * Tests the help text option with different options
     * The combinations with V check that the help option correctly activates even if the option is combined with other options
     */
    @ParameterizedTest
    @ValueSource(strings = {"--help", "-h", "-?"})
    void testHelp(String arg) {
        // act
        exitCode = cmd.execute("search", arg);
        String output = out.toString();

        // assert
        expectSuccessExitCode();
        assertTrue(output.contains("Usage: craw search"));
        assertTrue(output.contains("--help"));
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"  ", "\n", "\r"})
    void testFailOnNoArguments(String arg) {
        // act
        exitCode = cmd.execute("search", arg);
        String output = err.toString();

        // assert
        expectFailedExitCode();
        assertEquals(
                "The required parameter query was not specified.",
                output.substring(0, output.indexOf('\r'))
        ); // Verify correct error message is printed. After the error message Usage help is automatically printed after a newline.
        assertTrue(output.contains("Usage: craw search"));
        assertTrue(output.contains("--help"));
    }
}
