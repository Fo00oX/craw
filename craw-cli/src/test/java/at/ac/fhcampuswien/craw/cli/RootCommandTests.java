package at.ac.fhcampuswien.craw.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RootCommandTests {
    private App app = new App();
    private CommandLine cmd = new CommandLine(app);
    private StringWriter out;
    private StringWriter err;

    @BeforeEach
    void initCommandLine() {
        out = new StringWriter();
        err = new StringWriter();
        cmd.setOut(new PrintWriter(out));
        cmd.setErr(new PrintWriter(err));
    }

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
