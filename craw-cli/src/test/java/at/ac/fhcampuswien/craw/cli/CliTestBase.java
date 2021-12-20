package at.ac.fhcampuswien.craw.cli;

import org.junit.jupiter.api.BeforeEach;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Provides methods and initialization that all Cli test classes require.
 */
public class CliTestBase {
    protected App app = new App();
    protected CommandLine cmd = new CommandLine(app);
    protected StringWriter out;
    protected StringWriter err;

    @BeforeEach
    void initCommandLine() {
        out = new StringWriter();
        err = new StringWriter();
        cmd.setOut(new PrintWriter(out));
        cmd.setErr(new PrintWriter(err));
    }

    /**
     * Verifies that the provided exit code indicates a successful execution
     * @param exitCode the exit code of the application execution
     */
    protected void expectSuccessExitCode(int exitCode) {
        assertEquals(0, exitCode);
    }

    /**
     * Verifies that the provided exit code indicates an execution failure.
     * @param exitCode the exit code of the application execution
     */
    protected void expectFailedExitCode(int exitCode) {
        assertNotEquals(0, exitCode);
    }
}
