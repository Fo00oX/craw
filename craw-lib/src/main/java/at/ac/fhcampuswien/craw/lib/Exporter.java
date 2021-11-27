package at.ac.fhcampuswien.craw.lib;

import at.ac.fhcampuswien.craw.lib.model.Weblink;

import java.util.List;

public class Exporter {
    /**
     * Writes a list of Weblinks to a .yaml/yml file
     *
     * @param filename the path and name of the file
     * @param links
     */
    public void writeYAML(String filename, List<Weblink> links) {
        if (!filename.endsWith(".yml") && !filename.endsWith(".yaml")) filename += ".yml";
    }

    /**
     * Writes a list of Weblinks to a .json file
     *
     * @param filename the path and name of the file
     * @param links
     */
    public void writeJSON(String filename, List<Weblink> links) {
        if (!filename.endsWith(".json")) filename += ".json";
    }
}
