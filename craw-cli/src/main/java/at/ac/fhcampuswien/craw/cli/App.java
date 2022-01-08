package at.ac.fhcampuswien.craw.cli;

import at.ac.fhcampuswien.craw.cli.commands.CheckCommand;
import at.ac.fhcampuswien.craw.cli.commands.PageCommand;
import at.ac.fhcampuswien.craw.cli.commands.base.BaseCommand;
import picocli.AutoComplete.GenerateCompletion;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import static picocli.CommandLine.Command;

/**
 * Represents the base command "craw", from which all subcommands and options branch off.
 * The Options to print help text and version information is located here.
 */
@Command(
        name = "craw",
        description = "The craw command line app provides useful functions for crawling websites analyzing the links they contain.",
        version = {
                "Craw version " + App.VERSION,
                "Java version: ${java.version}",
                "OS: ${os.name} ${os.version}"
        },
        synopsisSubcommandLabel = "COMMAND",
        subcommands = {
                PageCommand.class,
                CheckCommand.class,
                GenerateCompletion.class
        }
)
public class App extends BaseCommand {
    public static final String VERSION = "v0.0.1-Alpha";

    @Option(names = {"--version", "-V"}, versionHelp = true, description = "Print version information and exit")
    boolean versionRequested;

    public static void main(String... args) {
        App app = new App();
        new CommandLine(app)
                .execute(args);
        // TODO add handling for unexpected Exceptions / Custom Exception Handling
        // MalformedURLException
    }

    @Override
    public void run() {
    } // picocli raises an error automatically if no command can be matched.
}
