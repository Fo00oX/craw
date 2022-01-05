package at.ac.fhcampuswien.craw.lib.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import at.ac.fhcampuswien.craw.lib.model.Weblink;

public class ExporterTest {

    private final Exporter exporter = new Exporter();
    private String pathToFile;
    private static List<Weblink> links;

    @TempDir
    File tempDir;

    @BeforeAll
    public static void createLinksMock() {
        links = new ArrayList<>();
        links.add(new Weblink("www.google.at", "google"));
        links.add(new Weblink("www.orf.at", "orf"));
        links.add(new Weblink("www.github.at", "github"));
    }

    @Test
    public void writeYAML() throws IOException {
        //arrange
        File yaml = new File(tempDir, "links.yaml");
        final String testYaml = "links:"
                + "- {name: google, url: www.google.at}"
                + "- {name: orf, url: www.orf.at}"
                + "- {name: github, url: www.github.at}";

        //act
        this.exporter.writeYAML(yaml.toString(), links);

        //assert
        assertTrue(tempDir.isDirectory(), "Should be a directory");
        assertTrue(yaml.exists(), "YAML should exist");
        assertEquals("links.yaml", yaml.getName(), "YAML should be saved as links.yaml");
        assertEquals(testYaml, getFileContent(yaml.getPath()), "YAML content should equal mocked content");
    }

    @Test
    public void writeJSON() throws IOException {
        //arrange
        File json = new File(tempDir, "links.json");
        final String testJson = "[" +
                "{\"name\":\"google\",\"url\":\"www.google.at\"}," +
                "{\"name\":\"orf\"," + "\"url\":\"www.orf.at\"}," +
                "{\"name\":\"github\",\"url\":\"www.github.at\"}" +
                "]";

        //act
        exporter.writeJSON(json.toString(), links);

        //assert
        assertTrue(tempDir.isDirectory(), "Should be a directory");
        assertTrue(json.exists(), "JSON should exist");
        assertEquals("links.json", json.getName(), "JSON should be saved as links.yaml");
        assertEquals(testJson, getFileContent(json.getPath()), "JSON content should equal mocked content");
    }

    private String getFileContent(String pathToFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(pathToFile));
        String fileContent;
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            fileContent = sb.toString().replace("\n", "").replace("\r", "");
        } finally {
            br.close();
        }
        return fileContent;
    }
}