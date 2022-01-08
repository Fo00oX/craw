package at.ac.fhcampuswien.craw.cli.commands;

import at.ac.fhcampuswien.craw.cli.commands.base.BaseLinkOutputCommand;
import at.ac.fhcampuswien.craw.lib.advanced.LinkChecker;
import at.ac.fhcampuswien.craw.lib.base.Crawler;
import at.ac.fhcampuswien.craw.lib.exceptions.CrawException;
import at.ac.fhcampuswien.craw.lib.model.BrokenLink;
import at.ac.fhcampuswien.craw.lib.model.Weblink;

import java.util.List;

import static picocli.CommandLine.Command;

@Command(
        name = "check",
        description = "Check a page for broken links."
)
public class CheckCommand extends BaseLinkOutputCommand {

    @Override
    public void run() {
        try {
            if (!url.getProtocol().equalsIgnoreCase("http") && !url.getProtocol().equalsIgnoreCase("https"))
                raiseError(String.format("Unsupported protocol '%s'. Only the protocols 'http' and 'https' are supported.", url.getProtocol()));

            Crawler scraper = new Crawler();
            LinkChecker checker = new LinkChecker();

            out().println(String.format("Scanning '%s' for broken links.", url));
            List<Weblink> linksToCheck = scraper.getLinks(url.getQuery());

            if (linksToCheck.size() == 0)
                out().println(String.format("This page ('%s') contains no links to other pages.", url.getQuery()));

            List<BrokenLink> brokenLinks = checker.checkLinks(linksToCheck);

            if (brokenLinks.size() == 0)
                out().println(String.format("All %d links on the page '%s' are valid.", linksToCheck.size(), url.getQuery()));
            else {
                out().println(String.format(
                        "Analyzed %d links on the page '%s' and found the following %d broken links: ",
                        linksToCheck.size(),
                        url.getQuery(),
                        brokenLinks.size()
                ));
                brokenLinks.stream()
                        .map(x -> String.format(
                                "%s: %s",
                                x.getUrl(),
                                x.getLinkStatus()
                        ))
                        .forEach(x -> out().println(x));
            }
            outputWeblinksToFilesIfRequired(List.copyOf(brokenLinks));
        } catch (CrawException e) {
            raiseError(e.getMessage());
        }
    }
}
