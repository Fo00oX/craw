package at.ac.fhcampuswien.craw.cli;

import at.ac.fhcampuswien.craw.cli.cliTools.CliOutput;
import at.ac.fhcampuswien.craw.cli.commands.Command;
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
    public void addCommand(String[] args) throws BaseCrawException {
        if (args.length == 0)
            throw CliException.ForNoArgumentsProvided();
        switch (args[0]) {

            default -> throw CliException.ForInvalidCommand(args[0]);
        }
    }

    public void addCommand(Command cmd){

    }

    /**
     * Starts the Application
     */
    public void start() {

    }
}
