package at.ac.fhcampuswien.craw.cli;

import at.ac.fhcampuswien.craw.cli.cliTools.SystemWrapper;
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
    private static boolean debug;

    public static void main(String[] args) {
        SystemWrapper system = new SystemWrapper();

        // Load system properties
        try (InputStream appPropertyFile = new FileInputStream(Objects.requireNonNull(App.class.getResource("/properties/app.properties")).getFile())) {
            Properties prop = new Properties(System.getProperties());
            prop.load(appPropertyFile);
            System.setProperties(prop);

            debug = Boolean.getBoolean("DEBUG");
        } catch (IOException ex) {
            debug = false;
        }

        try {
            CliController cli = new CliController(system);
            cli.start(args);
        } catch (BaseCrawException e) { // any BaseCrawException that gets to this point already blocked the application execution
            system.exitWithError(e);
        } catch (Exception e) {
            system.exitWithError(e);
        }
    }

    public static boolean isDebug() {
        return debug;
    }
}
