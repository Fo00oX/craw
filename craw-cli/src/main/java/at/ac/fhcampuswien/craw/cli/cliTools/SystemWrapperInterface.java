package at.ac.fhcampuswien.craw.cli.cliTools;

/**
 * Provides methods that can be used to write output. DO NOT use System.out.print*() directly!
 */
public interface SystemWrapperInterface {
    void print(String output);
    void println(String output);
    void printError(String error);
    void printlnError(String error);
}
