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

    @Override
    protected void expectUsageHelp(String output) {
        assertTrue(output.startsWith("Usage: craw"), "usageMessage should start with 'Usage: craw'");
        assertTrue(output.contains("--version"), "usageMessage should contain '--version'");
        assertTrue(output.contains("--help"), "usageMessage should contain '--help'");
        assertTrue(output.contains("Commands:"), "usageMessage should contain 'Commands:'");
        assertTrue(output.contains("page"), "usageMessage should contain 'page'");
        assertTrue(output.contains("check"), "usageMessage should contain 'check'");
        assertTrue(output.contains("generate-completion"), "usageMessage should contain 'generate-completion'");
    }

    @Test
    void testVersion() {
        // act
        exitCode = cmd.execute("--version");
        String output = out.toString();

        // assert
        expectSuccessExitCode();
        assertTrue(output.contains(App.VERSION), "should contain app version");
        assertTrue(output.contains("version"), "should contain 'version'");
    }

    /**
     * Tests the help text option with different options
     * The combinations with V check that the help option correctly activates even if the option is combined with other options
     */
    @ParameterizedTest
    @ValueSource(strings = {"--help", "-h", "-?", "-V?", "-Vh", "-?V", "-hV"})
    void testHelp(String arg) {
        // act
        exitCode = cmd.execute(arg);
        String output = out.toString();

        // assert
        expectSuccessExitCode();
        expectUsageHelp(output);
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"notACommand", "-1"})
    void failIfNoOrIncorrectCommandSpecified(String args) {
        // act
        exitCode = cmd.execute(args);
        String output = err.toString();

        // assert
        expectFailedExitCode();
        assertTrue(output.contains(args), "error message should contain the invalid arguments");
    }
}
