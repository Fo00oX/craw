package at.ac.fhcampuswien.craw.cli;

import at.ac.fhcampuswien.craw.cli.cliTools.CliOutput;
import at.ac.fhcampuswien.craw.cli.cliTools.SystemOutput;
import at.ac.fhcampuswien.craw.lib.exceptions.BaseCrawException;

/**
 * This class is the entry point for the cli application.
 */
public class App {
    public static CliOutput sysOut = new SystemOutput();
    public static boolean sendExitCode = true;

    private static int exitCode = 0;

    public static void main(String[] args) {
        try {
            CliController cli = new CliController(App.sysOut);
            cli.addCommand(args);
            cli.start();
        } catch (BaseCrawException e) { // any BaseCrawException that gets to this point already blocked the application execution
            sysOut.printlnError(e.getErrorMessage());
            System.exit(-1);
        } /*catch (Exception e) { //TODO uncomment before deployment
            sysOut.printlnError("An unexpected error has occured.");
            System.exit(-1);
        }*/
    }
}
