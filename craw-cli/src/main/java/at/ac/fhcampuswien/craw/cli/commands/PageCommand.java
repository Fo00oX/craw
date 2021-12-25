package at.ac.fhcampuswien.craw.cli.commands;

import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;

@Command(
        name = "page",
        description = "Fetch a list of all Links present on a Webpage"
)
public class PageCommand extends BaseCommand {
    @Override
    public void run() {
        spec.commandLine().getOut().println("PageCommand");
    }
}
