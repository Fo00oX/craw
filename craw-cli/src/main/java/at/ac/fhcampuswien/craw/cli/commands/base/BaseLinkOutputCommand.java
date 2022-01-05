package at.ac.fhcampuswien.craw.cli.commands.base;

import at.ac.fhcampuswien.craw.lib.base.Exporter;
import at.ac.fhcampuswien.craw.lib.model.Weblink;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static picocli.CommandLine.Option;

public abstract class BaseLinkOutputCommand extends BaseCommand {

    @Option(
            names = {"--linksOnly", "-l"},
            description = "If specified, prints only the discovered links without additional output.",
            defaultValue = "false"
    )
    protected boolean linksOnly;

    @Option(
            names = {"-j", "--json"},
            paramLabel = "jsonFile",
            description = "Output the collected links to a specified JSON file. " +
                    "Automatically adds the .json file ending if it is not specified already. " +
                    "Can be specified more than once."
    )
    protected File[] jsonFiles;

    @Option(
            names = {"-y", "--yaml", "--yml"},
            paramLabel = "yamlFile",
            description = "Output the collected links to a specified YAML file. " +
                    "Automatically adds the .yml file ending if it is not specified already. " +
                    "Can be specified more than once."
    )
    protected File[] yamlFiles;

    @Parameters(
            paramLabel = "url",
            description = "The URL to analyze."
    )
    protected URL url;

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
            out().println(outStr);
        }
    }

    /**
     * Outputs the collected {@link Weblink}s to JSON or YML if the user requested it.
     *
     * @param links the {@link List} of {@link Weblink} containing the collected links.
     */
    protected void outputWeblinksToFilesIfRequired(List<Weblink> links) {
        Exporter exporter = new Exporter();
        if (jsonFiles.length > 0) {
            for (File f : jsonFiles) {
                exporter.writeJSON(f.getAbsolutePath(), links);
            }
        }
        if (yamlFiles.length > 0) {
            for (File f : yamlFiles) {
                exporter.writeJSON(f.getAbsolutePath(), links);
            }
        }
    }
}
