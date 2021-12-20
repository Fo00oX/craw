package at.ac.fhcampuswien.craw.cli.commands;

import picocli.CommandLine;
import picocli.CommandLine.Model.CommandSpec;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "search",
        description = "Run a Google search using a specified request"
)
public class SearchCommand implements Callable<Integer> {
    @CommandLine.Spec
    CommandSpec spec;

    @Override
    public Integer call() {
        spec.commandLine().getOut().println("SearchCommand!");
        return 0;
    }
}
