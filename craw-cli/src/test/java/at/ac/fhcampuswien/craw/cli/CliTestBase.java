package at.ac.fhcampuswien.craw.cli;

import org.junit.jupiter.api.BeforeEach;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Provides common methods and initialization that all Cli test classes require.
 */
public abstract class CliTestBase {
    protected App app = new App();
    protected CommandLine cmd = new CommandLine(app);
    protected StringWriter out;
    protected StringWriter err;

    protected Integer exitCode;

    @BeforeEach
    void initCommandLine() {
        // Reset System.out and System.err
        out = new StringWriter();
        err = new StringWriter();
        cmd.setOut(new PrintWriter(out));
        cmd.setErr(new PrintWriter(err));

        // Reset errorCode
        exitCode = null;
    }

    /**
     * Verifies that the last exit code indicates a successful execution
     */
    protected void expectSuccessExitCode() {
        assertNotNull(exitCode);
        assertEquals(0, exitCode);
    }

    /**
     * Verifies that the last exit code indicates an execution failure.
     */
    protected void expectFailedExitCode() {
        assertNotNull(exitCode);
        assertNotEquals(0, exitCode);
    }

    /**
     * Verifies that the specified Output is equivalent to the specification for the Usage.
     *
     * @param output the output that contains the Usage information.
     */
    protected abstract void expectUsageHelp(String output);

    /**
     * Verifies that the specified Output contains the Usage information as verified by {@link #expectUsageHelp(String)} at the end of the String.
     *
     * @param output the output containing the Usage help at the end of the string.
     */
    protected void expectUsageHelpOnEndOfString(String output) {
        expectUsageHelp(output.substring(output.indexOf("Usage")));
    }
}
