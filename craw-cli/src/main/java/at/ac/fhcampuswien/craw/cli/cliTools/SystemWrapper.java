package at.ac.fhcampuswien.craw.cli.cliTools;

import at.ac.fhcampuswien.craw.cli.App;
import at.ac.fhcampuswien.craw.lib.exceptions.BaseCrawException;

/**
 * Wraps {@link System} methods to enable dependency injection.
 * DO NOT use {@link System} methods like System.out.print*() directly!
 */
public class SystemWrapper {
    private final int errorExitCode = -1;

    public void print(String output) {
        System.out.print(output);
    }

    public void println(String output) {
        System.out.println(output);
    }

    public void printError(String error) {
        System.err.print(error);
    }

    public void printlnError(String error) {
        System.err.println(error);
    }


    /**
     * Exits the application with the error code -1 and prints a generic error message if debug mode is disabled.
     * If debug mode is enabled, the stacktrace of the exception will be printed to console.
     * <p>
     * WARNING! BaseCrawExceptions should be caught separately to call the overload exitWithError(BaseCrawException e), which prints a more specific error message.
     *
     * @param e the caught Exception
     */
    public void exitWithError(Exception e) {
        if (!App.isDebug()) {
            printlnError("An unexpected error has occured.");
            System.exit(errorExitCode);
        } else {
            e.printStackTrace();
            System.exit(errorExitCode);
        }
    }

    /**
     * Exits the application with the error code -1 and prints the error message of the {@link BaseCrawException} if debug mode is disabled.
     * If debug mode is enabled, the stacktrace will be printed.
     *
     * @param e the caught Exception
     */
    public void exitWithError(BaseCrawException e) {
        if (!App.isDebug()) {
            printlnError(e.getErrorMessage());
            System.exit(errorExitCode);
        } else {
            e.printStackTrace();
            System.exit(errorExitCode);
        }
    }
}
