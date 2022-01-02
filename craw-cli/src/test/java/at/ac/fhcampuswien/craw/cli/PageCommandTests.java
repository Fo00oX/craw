package at.ac.fhcampuswien.craw.cli;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageCommandTests extends CliTestBase {
    /**
     * Tests the help text option with different options
     * The combinations with V check that the help option correctly activates even if the option is combined with other options
     */
    @ParameterizedTest
    @ValueSource(strings = {"--help", "-h", "-?"})
    void testHelp(String arg) {
        // act
        exitCode = cmd.execute("page", arg);
        String output = out.toString();

        // assert
        expectSuccessExitCode();
        assertTrue(output.contains("Usage: craw page"));
        assertTrue(output.contains("--help"));
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    void testInvalidProtocol(String url) {
        // act
        exitCode = cmd.execute("page", url);
        String output = out.toString();

        // assert
    }
}
