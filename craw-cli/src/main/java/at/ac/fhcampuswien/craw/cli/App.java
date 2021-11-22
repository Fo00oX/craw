package at.ac.fhcampuswien.craw.cli;

import at.ac.fhcampuswien.SampleLib;

/**
 * This class is the entry point for the cli interface.
 *
 * @author Schindler
 */
public class App {
    public static void main(String[] args) {
        SampleLib lib = new SampleLib();
        System.out.println(lib.getSampleMessage());
    }
}
