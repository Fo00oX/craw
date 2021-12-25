package at.ac.fhcampuswien.craw.cli;

import at.ac.fhcampuswien.craw.cli.commands.BaseCommand;
import at.ac.fhcampuswien.craw.cli.commands.PageCommand;
import at.ac.fhcampuswien.craw.cli.commands.SearchCommand;
import picocli.AutoComplete;
import picocli.CommandLine;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;

import static picocli.CommandLine.Command;

/**
 * Represents the base command "craw", from which all subcommands and options branch off.
 * The Options to print help text and version information is located here.
 */
@Command(
        name = "craw",
        description = "Craw command description",
        version = {
                "Craw version " + App.VERSION,
                "Java version: ${java.version}",
                "OS: ${os.name} ${os.version}"
        },
        synopsisSubcommandLabel = "COMMAND",
        subcommands = {
                PageCommand.class,
                SearchCommand.class,
                AutoComplete.GenerateCompletion.class
        }
)
public class App extends BaseCommand {
    @Spec
    private CommandSpec spec;

    public static final String VERSION = "v0.0.1-Alpha";

    @Option(names = {"--version", "-V"}, versionHelp = true, description = "Print version information and exit")
    boolean versionRequested;

    public static void main(String... args) {
        App app = new App();
        // If initialization is required, follow this guide: https://picocli.info/#_initialization_before_execution
        new CommandLine(app)
                .execute(args);
    }

    @Override
    public void run() {} // picocli raises an error automatically if no command can be matched.
}
