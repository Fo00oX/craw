package at.ac.fhcampuswien.craw.cli.commands.base;

import at.ac.fhcampuswien.craw.lib.base.Exporter;
import at.ac.fhcampuswien.craw.lib.exceptions.CrawException;
import at.ac.fhcampuswien.craw.lib.model.Weblink;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static picocli.CommandLine.Option;
import static picocli.CommandLine.Parameters;

public abstract class BaseLinkOutputCommand extends BaseCommand {

    @Option(
            names = {"--overwrite", "-o"},
            description = "By default, files are not overwritten if a specified output file already exists. By specifying this option, existing files will be overwritten if required.",
            defaultValue = "false"
    )
    protected boolean overwriteFile;

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
        links.stream()
                .map(Weblink::prettifiedString)
                .forEach(x -> out().println(x));
    }

    /**
     * Outputs the collected {@link Weblink}s to JSON or YML if the user requested it.
     *
     * @param links the {@link List} of {@link Weblink} containing the collected links.
     */
    protected void outputWeblinksToFilesIfRequired(List<Weblink> links) throws CrawException {
        Exporter exporter = new Exporter();

        // If a file is specified
        if (!overwriteFile) {
            List<File> conflictingFiles = Stream.concat(
                            Arrays.stream(yamlFiles),
                            Arrays.stream(jsonFiles)
                    )
                    .filter(x -> !x.exists())
                    .collect(Collectors.toList());
            raiseError(String.format(
                    "%d of the files specified for output already exist. Use the --overwrite option to overwrite the following files.%s%s%s",
                    conflictingFiles.size(),
                    System.lineSeparator(),
                    conflictingFiles.stream()
                            .map(x -> String.format("%s%s",
                                    x.getAbsolutePath(),
                                    System.lineSeparator()
                            ))
                            .collect(Collectors.joining()),
                    "No files were created or changed."
            ));
        }

        if (jsonFiles.length > 0) {
            for (File f : jsonFiles) {
                exporter.writeJSON(f, links);
            }
        }
        if (yamlFiles.length > 0) {
            for (File f : yamlFiles) {
                exporter.writeYAML(f, links);
            }
        }
    }
}
