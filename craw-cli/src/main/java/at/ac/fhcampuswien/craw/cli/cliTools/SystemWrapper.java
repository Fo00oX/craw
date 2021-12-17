package at.ac.fhcampuswien.craw.cli.cliTools;

/**
 * Implements {@link SystemWrapperInterface} to write to console
 */
public class SystemWrapper implements SystemWrapperInterface {
    @Override
    public void print(String output) {
        System.out.print(output);
    }

    @Override
    public void println(String output) {
        System.out.println(output);
    }

    @Override
    public void printError(String error) {
        System.err.print(error);
    }

    @Override
    public void printlnError(String error) {
        System.err.println(error);
    }
}
