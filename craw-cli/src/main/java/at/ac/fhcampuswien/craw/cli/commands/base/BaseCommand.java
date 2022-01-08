package at.ac.fhcampuswien.craw.cli.commands.base;

import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;

import java.io.PrintWriter;

import static picocli.CommandLine.Option;
import static picocli.CommandLine.Spec;

public abstract class BaseCommand implements Runnable {
    @Spec
    protected CommandSpec spec;

    @Option(names = {"--help", "-h", "-?"}, usageHelp = true, description = "Display this help and exit")
    boolean help;

    /**
     * Raise an error in the application. This will TERMINATE EXECUTION and throw a {@link ParameterException} with the specified error message.
     *
     * @param errorMessage the error message that should be output to the UI.
     */
    protected void raiseError(String errorMessage) {
        throw new ParameterException(spec.commandLine(), errorMessage);
    }

    /**
     * Wraps the output statement.
     *
     * @return the PrintWriter for printing to the Command Line
     */
    protected PrintWriter out() {
        return spec.commandLine().getOut();
    }
}
