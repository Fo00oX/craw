package at.ac.fhcampuswien.craw.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RootCommandTests extends CliTestBase {

    @Test
    void testVersion() {
        // act
        int exitCode = cmd.execute("--version");
        String output = out.toString();

        // assert
        assertEquals(0, exitCode); // expect successful completion
        assertTrue(output.contains(App.VERSION));
        assertTrue(output.contains("version"));
    }
}
