package at.ac.fhcampuswien.craw.lib.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import at.ac.fhcampuswien.craw.lib.model.Weblink;

public class ExporterTest {

    private final Exporter exporter = new Exporter();
    private static List<Weblink> links;

    @TempDir
    File tempDir;

    @BeforeAll
    static void createLinksMock() {
        links = new ArrayList<>();
        links.add(new Weblink("www.google.at", "google"));
        links.add(new Weblink("www.orf.at", "orf"));
        links.add(new Weblink("www.github.at", "github"));
    }

    @Test
    void writeYAMLToCustomDirectory() throws IOException {
        //arrange
        File YAML = new File(tempDir, "links.yaml");
        final String expectedYAML = "links:" +
                "- name: google  url: www.google.at" +
                "- name: orf  url: www.orf.at" +
                "- name: github  url: www.github.at";

        //act
        this.exporter.writeYAML(YAML.toString(), links);

        //assert
        assertTrue(tempDir.isDirectory(), "Should be a directory");
        assertTrue(YAML.exists(), "YAML should exist");
        assertEquals("links.yaml", YAML.getName(), "YAML should be named links.yaml");
        assertEquals(expectedYAML, getFileContent(YAML.getPath()), "YAML content should equal mocked content");
    }

    @Test
    void writeJSONToCustomDirectory() throws IOException {
        //arrange
        File JSON = new File(tempDir, "links.json");
        final String expectedJSON = "[" +
                "{\"name\":\"google\",\"url\":\"www.google.at\"}," +
                "{\"name\":\"orf\"," + "\"url\":\"www.orf.at\"}," +
                "{\"name\":\"github\",\"url\":\"www.github.at\"}" +
                "]";

        //act
        exporter.writeJSON(JSON.toString(), links);

        //assert
        assertTrue(tempDir.isDirectory(), "Should be a directory");
        assertTrue(JSON.exists(), "JSON should exist");
        assertEquals("links.json", JSON.getName(), "JSON should be named links.json");
        assertEquals(expectedJSON, getFileContent(JSON.getPath()), "JSON content should equal mocked content");
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