package at.ac.fhcampuswien.craw.cli.commands;

import static picocli.CommandLine.Option;

public abstract class BaseCommand implements Runnable {
    @Option(names = {"--help", "-h", "-?"}, usageHelp = true, description = "Display this help and exit")
    boolean help;
}
