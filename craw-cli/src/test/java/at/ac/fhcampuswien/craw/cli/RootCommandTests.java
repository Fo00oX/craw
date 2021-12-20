package at.ac.fhcampuswien.craw.cli;

import org.junit.jupiter.api.Test;

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

    @Test
    void testHelp() {
        // act
        int exitCode = cmd.execute("--help");
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
}
