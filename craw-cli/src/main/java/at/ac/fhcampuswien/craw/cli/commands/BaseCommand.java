package at.ac.fhcampuswien.craw.cli.commands;

import picocli.CommandLine.Model.CommandSpec;

import static picocli.CommandLine.Option;
import static picocli.CommandLine.Spec;

public abstract class BaseCommand implements Runnable {
    @Spec
    CommandSpec spec;

    @Option(names = {"--help", "-h", "-?"}, usageHelp = true, description = "Display this help and exit")
    boolean help;
}
