package at.ac.fhcampuswien.craw.cli;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
}
