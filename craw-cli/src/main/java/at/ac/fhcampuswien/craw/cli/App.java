package at.ac.fhcampuswien.craw.cli;

import at.ac.fhcampuswien.craw.cli.commands.PageCommand;
import at.ac.fhcampuswien.craw.cli.commands.SearchCommand;
import picocli.AutoComplete;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import static picocli.CommandLine.Command;

/**
 * This class is the entry point for the cli application.
 */
@Command(
        version = {
                "Craw version " + App.VERSION,
                "Java version: ${java.version}",
                "OS: ${os.name} ${os.version}"},
        name = "craw",
        description = "Craw command description",
        subcommands = {
                PageCommand.class,
                SearchCommand.class,
                AutoComplete.GenerateCompletion.class
        }
)
public class App implements Runnable {

    public static final String VERSION = "v0.0.1";

    @Option(names = "--help", usageHelp = true, description = "Display this help and exit")
    boolean help;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit")
    boolean versionRequested;

    public static void main(String... args) {
        App app = new App();
        // If initialization is required, follow this guide: https://picocli.info/#_initialization_before_execution
        new CommandLine(app)
                .execute(args);
    }

    @Override
    public void run() {

    }
}
