package at.ac.fhcampuswien.craw.cli.commands;

import at.ac.fhcampuswien.craw.cli.commands.base.BaseLinkOutputCommand;

import java.util.List;

import static picocli.CommandLine.*;

@Command(
        name = "check",
        description = "Check a page for broken links."
)
public class CheckCommand extends BaseLinkOutputCommand {

    @Parameters(
            paramLabel = "query",
            index = "0..*",
            arity = "1..*",
            description = ""
    )
    private List<String> queryStrings;

    @Option(
            names = {"-r", "-n", "--results"},
            paramLabel = "nrOfResults",
            description = "The number of results to fetch. Defaults to ${DEFAULT-VALUE}.",
            defaultValue = "10"
    )
    private int nrResults;

    /**
     * @return the full query joined into a single String
     */
    private String getQuery() {
        return String.join(" ", queryStrings);
    }

    @Override
    public void run() {
    }
}
