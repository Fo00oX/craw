package at.ac.fhcampuswien.craw.cli;

import org.junit.jupiter.api.BeforeEach;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;

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
}
