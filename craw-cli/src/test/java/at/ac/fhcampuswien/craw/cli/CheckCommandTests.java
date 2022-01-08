package at.ac.fhcampuswien.craw.cli;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckCommandTests extends CliTestBase {

    @Override
    protected void expectUsageHelp(String output) {
        assertTrue(output.startsWith("Usage: craw check"), "usageMessage should start with 'Usage: craw check'");
        assertTrue(output.contains("--help"), "usageMessage should contain '--help'");
    }

    /**
     * Tests the help text option with different options
     * The combinations with V check that the help option correctly activates even if the option is combined with other options
     */
    @ParameterizedTest
    @ValueSource(strings = {"--help", "-h", "-?"})
    void testHelp(String arg) {
        // act
        exitCode = cmd.execute("check", arg);
        String output = out.toString();

        // assert
        expectSuccessExitCode();
        expectUsageHelp(output);
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"\r", "\n", "\r\n", "\t", "      "})
    void testFailOnNoArguments(String arg) {
        // act
        exitCode = cmd.execute("check", arg);
        String output = err.toString();

        // assert
        expectFailedExitCode();
        assertTrue(output.startsWith("Invalid value for positional parameter at index 0 (url):"), "incorrect error message printed");
        expectUsageHelpOnEndOfString(output);
    }

    //TODO add more invalid protocols
    @ParameterizedTest
    @CsvSource({"ftp,ftp://example.org"})
    void testInvalidProtocol(String protocol, String url) {
        // act
        exitCode = cmd.execute("check", url);
        String output = err.toString();

        // assert
        expectFailedExitCode();
        assertTrue(output.startsWith(String.format("Unsupported protocol '%s'. Only the protocols 'http' and 'https' are supported.", protocol)), "incorrect error message printed");
        expectUsageHelpOnEndOfString(output);
    }

    // TODO The following Tests are still pending (require the completion of base features and proper testdata)
    // No links on Page
    // All valid links on page
    // Mixed Link success

}
