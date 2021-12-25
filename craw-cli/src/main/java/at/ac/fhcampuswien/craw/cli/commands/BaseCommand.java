package at.ac.fhcampuswien.craw.cli.commands;

import at.ac.fhcampuswien.craw.lib.model.Weblink;
import picocli.CommandLine.Model.CommandSpec;

import java.util.List;
import java.util.stream.Collectors;

import static picocli.CommandLine.Option;
import static picocli.CommandLine.Spec;

public abstract class BaseCommand implements Runnable {
    @Spec
    CommandSpec spec;

    @Option(names = {"--help", "-h", "-?"}, usageHelp = true, description = "Display this help and exit")
    boolean help;

    /**
     * Prints a List of Weblinks to the Cli
     *
     * @param links a {@link List} of {@link Weblink}s to be printed to StdOut
     * @return the {@link List} of {@link String}s that were printed
     */
    protected List<String> printWeblinks(List<Weblink> links, boolean linksOnly) {
        List<String> result = links.stream()
                .map(x -> linksOnly ? x.getURL() : x.prettifiedString())
                .collect(Collectors.toList());
        for (String outStr : result) {
            spec.commandLine().getOut().println(outStr);
        }
        return result;
    }
}
