package at.ac.fhcampuswien.craw.cli.commands;

import at.ac.fhcampuswien.craw.lib.advanced.GoogleSearch;
import at.ac.fhcampuswien.craw.lib.model.Weblink;

import java.util.List;

import static picocli.CommandLine.*;

@Command(
        name = "search",
        description = "Run a Google search using a specified query."
)
public class SearchCommand extends BaseCommand {

    @Parameters(
            paramLabel = "query",
            index = "0..*",
            arity = "1..*",
            description = ""
    )
    List<String> queryStrings;

    @Option(
            names = {"-r", "-n", "--results"},
            paramLabel = "nrOfResults",
            description = "The number of results to fetch. Defaults to ${DEFAULT-VALUE}.",
            defaultValue = "10"
    )
    int nrResults;

    @Option(
            names = {"--linksOnly", "-l"},
            description = "If specified, prints only the discovered links without additional information."
    )
    boolean linksOnly = false;


    /**
     * @return the full query joined into a single String
     */
    private String getQuery() {
        return String.join(" ", queryStrings);
    }

    @Override
    public void run() {
        if (getQuery().isBlank()) // Empty values can bypass the picocli validation that otherwise ensures that a query is specified, so they have to be filtered here
            throw new ParameterException(spec.commandLine(), "The required parameter query was not specified.");

        GoogleSearch search = new GoogleSearch();
        List<Weblink> result = search.searchQuery(getQuery(), nrResults);

        if (!linksOnly) spec.commandLine().getOut().println(String.format("Found %d results:", result.size()));
        printWeblinks(result, linksOnly);
    }
}
