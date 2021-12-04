package at.ac.fhcampuswien.craw.cli;

import at.ac.fhcampuswien.craw.cli.cliTools.CliOutput;
import at.ac.fhcampuswien.craw.cli.cliTools.SystemOutput;
import at.ac.fhcampuswien.craw.lib.exceptions.BaseCrawException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * This class is the entry point for the cli application.
 */
public class App {
    public static CliOutput sysOut = new SystemOutput();
    private static boolean debug;
    private static int errorExitCode = -1;

    public static void main(String[] args) throws Exception {
        // Load system properties
        try (InputStream appPropertyFile = new FileInputStream(Objects.requireNonNull(App.class.getResource("/properties/app.properties")).getFile())) {
            Properties prop = new Properties(System.getProperties());
            prop.load(appPropertyFile);
            System.setProperties(prop);

            debug = Boolean.getBoolean("DEBUG");
            Integer overrideExitCode = Integer.getInteger("OVERRIDE_EXIT_CODE");
            if (overrideExitCode != null) errorExitCode = overrideExitCode;
        } catch (IOException ex) {
            debug = false;
        }

        try {
            CliController cli = new CliController(App.sysOut);
            cli.start(args);
        } catch (BaseCrawException e) { // any BaseCrawException that gets to this point already blocked the application execution
            exitWithError(e);
        } catch (Exception e) {
            exitWithError(e);
        }
    }

    public static boolean isDebug() {
        return debug;
    }

    /**
     * Exits the application with the error code -1 (can be overridden by the system property "OVERRIDE_EXIT_CODE") and prints a generic error message if debug mode is disabled.
     * If debug mode is enabled, the stacktrace of the exception will be printed to console and the exception thrown again.
     * <p>
     * WARNING! BaseCrawExceptions should be caught separately to call the overload exitWithError(BaseCrawException e), which prints a more specific error message.
     *
     * @param e the caught Exception
     * @throws Exception if debug mode is enabled.
     */
    public static void exitWithError(Exception e) throws Exception {
        if (!debug) {
            sysOut.printlnError("An unexpected error has occured.");
            System.exit(errorExitCode);
        } else
            throw e;
    }

    /**
     * Exits the application with the error code -1 (can be overridden by the system property "OVERRIDE_EXIT_CODE") and prints the error message of the {@link BaseCrawException} if debug mode is disabled.
     * If debug mode is enabled, the exception will be thrown again.
     *
     * @param e the caught Exception
     * @throws BaseCrawException if debug mode is enabled.
     */
    public static void exitWithError(BaseCrawException e) throws BaseCrawException {
        if (!debug) {
            sysOut.printlnError(e.getErrorMessage());
            System.exit(errorExitCode);
        } else
            throw e;
    }
}
