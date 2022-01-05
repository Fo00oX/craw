package at.ac.fhcampuswien.craw.lib.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import at.ac.fhcampuswien.craw.lib.model.Weblink;

public class Exporter {

    private List<Weblink> links;    //field + getter and setter are needed by the snakeyaml library to generate YAMLs

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
     * @param links    Weblinks created by Crawler
     */
    public void writeYAML(String filename, List<Weblink> links) throws IOException {
        if (!filename.endsWith(".yml") && !filename.endsWith(".yaml")) {
            filename += ".yml";
        }

        File YAML = createFileAtPath(filename);
        writeLinksToYAML(YAML, links);
    }

    private void writeLinksToYAML(File YAML, List<Weblink> links) throws IOException {
        FileWriter writer = new FileWriter(YAML);
        Representer representer = new Representer();
        representer.addClassTag(Exporter.class, Tag.MAP);   //removes default tag
        Yaml YAMLWithoutDefaultTag = new Yaml(representer);
        this.setLinks(links);

        YAMLWithoutDefaultTag.dump(this, writer);
    }

    /**
     * Writes a list of Weblinks to a .json file
     *
     * @param filename the path and name of the file
     * @param links    Weblinks created by Crawler
     */
    public void writeJSON(String filename, List<Weblink> links) throws IOException {

        if (!filename.endsWith(".json")) {
            filename += ".json";
        }
        JSONArray linksAsJSON = convertLinksToJSONFormat(links);
        writeLinksToJSON(filename, linksAsJSON);
    }

    private JSONArray convertLinksToJSONFormat(List<Weblink> links) {
        JSONArray JSONArray = new JSONArray();
        for (Weblink link : links) {
            JSONObject linksAsJSON = new JSONObject();
            linksAsJSON.put("url", link.getUrl());
            linksAsJSON.put("name", link.getName());
            JSONArray.add(linksAsJSON);
        }
        return JSONArray;
    }

    private void writeLinksToJSON(String filename, JSONArray linksAsJSON) throws IOException {
        File JSON = createFileAtPath(filename);
        FileWriter fileWriter = new FileWriter(JSON);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(linksAsJSON.toJSONString());
        bufferedWriter.close();
    }

    private File createFileAtPath(String filename) throws InvalidPathException {
        String defaultFolder = System.getProperty("user.home") + "/Downloads/";
        String customFolder = filename.substring(0, getFilenameIndex(filename));
        filename = filename.substring(getFilenameIndex(filename));

        return customFolder.length() > 0
                ? new File(customFolder + filename)
                : new File(defaultFolder + filename);
    }

    private int getFilenameIndex(String filename) {
        StringBuilder sb = new StringBuilder(filename);
        sb.reverse();
        return sb.toString().contains("/")
                ? sb.length() - sb.indexOf("/")
                : 0;
    }
}
