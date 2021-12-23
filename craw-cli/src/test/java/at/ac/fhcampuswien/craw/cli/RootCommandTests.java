package at.ac.fhcampuswien.craw.cli;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verifies functionality provided directly by the "craw" command.
 * This command is implemented in the {@link App} class.
 */
public class RootCommandTests extends CliTestBase {

    @Test
    void testVersion() {
        // act
        int exitCode = cmd.execute("--version");
        String output = out.toString();

        // assert
        expectSuccessExitCode(exitCode);
        assertTrue(output.contains(App.VERSION));
        assertTrue(output.contains("version"));
    }

    /**
     * Tests the help text option with different options
     * The combinations with V check that the help option correctly activates even if the option is combined with other options
     */
    @ParameterizedTest
    @ValueSource(strings = {"--help", "-h", "-?", "-V?", "-Vh", "-?V", "-hV"})
    void testHelp(String arg) {
        // act
        int exitCode = cmd.execute(arg);
        String output = out.toString();

        // assert
        expectSuccessExitCode(exitCode);
        assertTrue(output.contains("Usage: craw"));
        assertTrue(output.contains("--version"));
        assertTrue(output.contains("--help"));
        assertTrue(output.contains("Commands:"));
        assertTrue(output.contains("page"));
        assertTrue(output.contains("search"));
        assertTrue(output.contains("generate-completion"));
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"notACommand", "-1"})
    void failIfNoOrIncorrectCommandSpecified(String args) {
        // act
        int exitCode = cmd.execute(args);
        String output = err.toString();

        // assert
        expectFailedExitCode(exitCode);
        assertTrue(output.contains(args)); // ensure that the error message actually contains the invalid arguments
    }
}
