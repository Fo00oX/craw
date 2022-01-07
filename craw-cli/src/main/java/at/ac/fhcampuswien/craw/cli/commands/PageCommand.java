package at.ac.fhcampuswien.craw.cli.commands;

import at.ac.fhcampuswien.craw.cli.commands.base.BaseLinkOutputCommand;
import at.ac.fhcampuswien.craw.lib.base.Crawler;
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
            List<Weblink> result = crawler.getLinks(url.getQuery());

            out().println(String.format("Found %d results:", result.size()));
            printWeblinks(result);

            outputWeblinksToFilesIfRequired(result);
        } catch (CrawException e) {
            raiseError(e.getMessage());
        }
    }
}
