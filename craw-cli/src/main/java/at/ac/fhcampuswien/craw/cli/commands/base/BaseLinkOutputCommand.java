package at.ac.fhcampuswien.craw.cli.commands.base;

import at.ac.fhcampuswien.craw.lib.model.Weblink;

import java.util.List;
import java.util.stream.Collectors;

import static picocli.CommandLine.Option;

public abstract class BaseLinkOutputCommand extends BaseCommand {

    @Option(
            names = {"--linksOnly", "-l"},
            description = "If specified, prints only the discovered links without additional information.",
            defaultValue = "false"
    )
    protected boolean linksOnly;

    /**
     * Prints a List of Weblinks to the Cli
     *
     * @param links a {@link List} of {@link Weblink}s to be printed to StdOut
     */
    protected void printWeblinks(List<Weblink> links) {
        List<String> result = links.stream()
                .map(x -> linksOnly ? x.getURL() : x.prettifiedString())
                .collect(Collectors.toList());
        for (String outStr : result) {
            spec.commandLine().getOut().println(outStr);
        }
    }
}
