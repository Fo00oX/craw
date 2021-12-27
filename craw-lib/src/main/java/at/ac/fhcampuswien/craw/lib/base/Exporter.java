package at.ac.fhcampuswien.craw.lib.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.InvalidPathException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import at.ac.fhcampuswien.craw.lib.model.Weblink;

/**
 * @author Thonhauser
 */

public class Exporter {

    private List<Weblink> links;


    public void setLinks(List<Weblink> links) {
        this.links = links;
    }

    public List<Weblink> getLinks() {
        return links;
    }

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

        File file = getFile(filename);
        saveLinksAsYAML(links, file);
    }

    private void saveLinksAsYAML(List<Weblink> links, File file) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Representer representer = new Representer();    //removes default tag
        representer.addClassTag(Exporter.class, Tag.MAP);
        Yaml yaml = new Yaml(representer);
        this.setLinks(links);

        yaml.dump(this, writer);
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

    private JSONArray convertLinksToJsonFormat(List<Weblink> links) {
        JSONArray json = new JSONArray();
        for (Weblink link : links) {
            JSONObject linksAsJSON = new JSONObject();
            linksAsJSON.put("URL", link.getURL());
            linksAsJSON.put("name", link.getName());
            json.add(linksAsJSON);
        }
        return json;
    }

    private File getFile(String filename) {
        try {
            if (!filename.contains("/")) {
                String pathToDownloads = System.getProperty("user.home") + "/Downloads/";
                return new File(pathToDownloads + filename);
            } else {
                new File("../temp/").mkdirs();
                String pathToTempFolder = "../temp/";
                return new File(pathToTempFolder + filename);
            }
        } catch (InvalidPathException ipe) {
            System.err.println("error creating temporary test file in " + this.getClass().getSimpleName());
        }
        return null;
    }

    private void saveLinksAsJSON(String filename, JSONArray linksAsJSON) {
        try {
            File file = getFile(filename);
            FileWriter fw1 = new FileWriter(file);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            bw1.write(linksAsJSON.toJSONString());
            bw1.close();
        } catch (IOException ioe) {
            System.err.println("error creating temporary test file in " + this.getClass().getSimpleName());
        }
    }
}
