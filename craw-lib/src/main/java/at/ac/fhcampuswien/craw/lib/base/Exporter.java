package at.ac.fhcampuswien.craw.lib.base;

import at.ac.fhcampuswien.craw.lib.model.Weblink;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author Thonhauser
 */

public class Exporter {

    private File file;

    /**
     * Writes a list of Weblinks to a .yaml/yml file
     *
     * @param filename the path and name of the file
     * @param links
     */
    public void writeYAML(String filename, List<Weblink> links) {
        if (!filename.endsWith(".yml") && !filename.endsWith(".yaml")) {
            filename += ".yml";
        }
        //TODO add functionality
    }

    /**
     * Writes a list of Weblinks to a .json file
     *
     * @param filename the path and name of the file
     * @param links
     */
    public void writeJSON(String filename, List<Weblink> links) {

        if (!filename.endsWith(".json")) {
            filename += ".json";
        }
        JSONArray linksAsJSON = convertLinksToJsonFormat(links);
        saveLinksAsJSON(filename, linksAsJSON);
    }

    public JSONArray convertLinksToJsonFormat(List<Weblink> links) {
        JSONArray json = new JSONArray();
        for (Weblink link : links) {
            JSONObject linksAsJSON = new JSONObject();
            linksAsJSON.put("name", link.getName());
            linksAsJSON.put("URL", link.getURL());
            json.add(linksAsJSON);
            System.out.println(json.toJSONString());
            System.out.println();
        }
        return json;
    }

    public File createJSONFile(String filename) {
        try {
            return new File(filename);
        } catch (InvalidPathException ipe) {
            System.err.println("error creating temporary test file in " + this.getClass().getSimpleName());
        }
        return null;
    }

    public void saveLinksAsJSON(String filename, JSONArray linksAsJSON) {
        try {
            file = createJSONFile( filename);
            FileWriter fw1 = new FileWriter(file);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            bw1.write(linksAsJSON.toJSONString());
            bw1.close();
        } catch (IOException ioe) {
            System.err.println("error creating temporary test file in " + this.getClass().getSimpleName());
        }

    }
}
