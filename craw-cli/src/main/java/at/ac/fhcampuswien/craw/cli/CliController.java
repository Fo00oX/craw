package at.ac.fhcampuswien.craw.cli;

import at.ac.fhcampuswien.craw.cli.cliTools.CliOutput;
import at.ac.fhcampuswien.craw.cli.commands.Command;
import at.ac.fhcampuswien.craw.cli.commands.ScrapeCommand;
import at.ac.fhcampuswien.craw.cli.commands.SearchCommand;
import at.ac.fhcampuswien.craw.cli.exceptions.CliException;
import at.ac.fhcampuswien.craw.lib.exceptions.BaseCrawException;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * This class presents the control functions of the Cli Application
 */
public class CliController {

    private CliOutput out;
    private Queue<Command> commands = new PriorityBlockingQueue<>();

    CliController(CliOutput out) {
        this.out = out;
    }

    /**
     * Initializes the Application with the command provided
     *
     * @param args the array of arguments provided to the Application
     */
    private void setCommand(String[] args) throws BaseCrawException {
        if (args.length == 0)
            throw CliException.ForNoArgumentsProvided();
        switch (args[0].toLowerCase()) {
            case "search":
                commands.add(new SearchCommand());
                break;
            case "scrape":
                commands.add(new ScrapeCommand());
                break;
            case "help":
            case "--help":
                printHelpText();
            default:
                throw CliException.ForInvalidCommand(args[0]);
        }
    }

    /**
     * Starts the Application
     */
    public void start(String[] args) throws BaseCrawException {
        try {
            setCommand(args);
        } catch (BaseCrawException e) {
            printHelpText();
            throw e;
        }
    }

    public void printHelpText() {
        out.println("Welcome to craw!");
        out.println("");
        out.println("Craw commands are structured like this:");
    }
}
