package at.ac.fhcampuswien.craw.lib.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import at.ac.fhcampuswien.craw.lib.exceptions.CrawException;
import at.ac.fhcampuswien.craw.lib.model.Weblink;

/**
 * Exports Weblinks as YAML or JSON
 */
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
     * @param file  The file where the links are written
     * @param links Weblinks created by Crawler
     * @throws CrawException Is thrown when file could not be written to directory
     */
    public void writeYAML(File file, List<Weblink> links) throws CrawException {
        String filePath = file.toString();
        if (!filePath.endsWith(".yml") && !filePath.endsWith(".yaml")) {
            file = new File(file.getParent(), file.getName() + ".yml");
        }

        FileWriter writer;
        try {
            writer = new FileWriter(file);
            Yaml formattedYAML = getFormattedYAML();
            setLinks(links);
            formattedYAML.dump(this, writer);
        } catch (IOException ioe) {
            throw new CrawException("File could not be exported", ioe);
        }

    }

    private Yaml getFormattedYAML() {
        Representer representer = new Representer();
        representer.addClassTag(Exporter.class, Tag.MAP);
        representer.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        return new Yaml(representer);
    }

    /**
     * Writes a list of Weblinks to a .json file
     *
     * @param file  The file where the links are written
     * @param links Weblinks created by Crawler
     * @throws CrawException Is thrown when file could not be written to directory
     */
    public void writeJSON(File file, List<Weblink> links) throws CrawException {
        String filePath = file.toString();
        if (!filePath.endsWith(".json")) {
            file = new File(filePath + ".yml");
        }

        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            JSONArray linksAsJSON = convertLinksToJSONFormat(links);
            bufferedWriter.write(linksAsJSON.toJSONString());
            bufferedWriter.close();
        } catch (IOException ioe) {
            throw new CrawException("File could not be exported", ioe);
        }
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
}
