package at.ac.fhcampuswien.craw.cli.commands;

import at.ac.fhcampuswien.craw.cli.commands.base.BaseCommand;
import at.ac.fhcampuswien.craw.cli.commands.base.BaseLinkOutputCommand;

import static picocli.CommandLine.Command;

@Command(
        name = "page",
        description = "Fetch a list of all Links present on a Webpage"
)
public class PageCommand extends BaseLinkOutputCommand {
    @Override
    public void run() {
        spec.commandLine().getOut().println("PageCommand");
    }
}
