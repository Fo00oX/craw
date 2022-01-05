package at.ac.fhcampuswien.craw.cli;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageCommandTests extends CliTestBase {

    @Override
    protected void expectUsageHelp(String output) {
        assertTrue(output.startsWith("Usage: craw page"));
        assertTrue(output.contains("--help"));
    }

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
        expectUsageHelp(output);
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"\r", "\n", "\r\n", "\t", "      "})
    void testFailOnEmptyUrl(String url) {
        // act
        exitCode = cmd.execute("page", url);
        String output = err.toString();

        // assert
        expectFailedExitCode();
        assertTrue(output.startsWith("Invalid value for positional parameter at index 0 (url):")); // Verify correct error message is printed.
        expectUsageHelpOnEndOfString(output);
    }

    // TODO add more Tests
    // Successful scrape with links
    // Successful scrape without links present
}
