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

import at.ac.fhcampuswien.craw.lib.model.Weblink;

public class ExporterTest {

    private final Exporter exporter = new Exporter();
    private String pathToFile;
    private static List<Weblink> links;
    private File tempDir;

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
        this.pathToFile = "../temp/links.yaml";
        tempDir = new File(pathToFile);
        final String testYaml = "links:"
                + "- {name: google, url: www.google.at}"
                + "- {name: orf, url: www.orf.at}"
                + "- {name: github, url: www.github.at}";

        //act
        this.exporter.writeYAML(this.pathToFile, links);

        //assert
        assertTrue(tempDir.exists(), "directory exists");
        assertTrue(tempDir.isFile(), "file lays in directory");
        assertEquals("links.yaml", this.tempDir.getName(), "file is saved as links.yaml");
        assertEquals(testYaml, getFileContent(pathToFile), "file content equals test content");
    }

    @Test
    public void writeJSON() throws IOException {
        //arrange
        this.pathToFile = "../temp/links.json";
        tempDir = new File(pathToFile);
        final String testJson = "[" +
                "{\"name\":\"google\",\"url\":\"www.google.at\"}," +
                "{\"name\":\"orf\"," + "\"url\":\"www.orf.at\"}," +
                "{\"name\":\"github\",\"url\":\"www.github.at\"}" +
                "]";

        //act
        exporter.writeJSON(this.pathToFile, links);

        //assert
        assertTrue(tempDir.exists(), "directory exists");
        assertTrue(tempDir.isFile(), "file lays in directory");
        assertEquals("links.json", tempDir.getName(), "file is saved as links.json");
        assertEquals(testJson, getFileContent(this.pathToFile), "file content equals test content");
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