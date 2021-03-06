package at.ac.fhcampuswien.craw.cli.commands;

import at.ac.fhcampuswien.craw.cli.commands.base.BaseLinkOutputCommand;
import at.ac.fhcampuswien.craw.lib.services.Crawler;
import at.ac.fhcampuswien.craw.lib.exceptions.CrawException;
import at.ac.fhcampuswien.craw.lib.model.Weblink;

import java.util.List;

import static picocli.CommandLine.Command;

@Command(
        name = "page",
        description = "Fetch a list of all Links present on a Webpage."
)
public class PageCommand extends BaseLinkOutputCommand {

    @Override
    public void run() {
        try {
            Crawler crawler = new Crawler();
            List<Weblink> result = crawler.getLinks(url.toExternalForm());

            out().println(String.format("Found %d results:", result.size()));
            result.stream()
                    .map(Weblink::prettifiedString)
                    .forEach(x -> out().println(x));

            outputWeblinksToFilesIfRequired(result);
        } catch (CrawException e) {
            raiseError(e.getMessage());
        }
    }
}
