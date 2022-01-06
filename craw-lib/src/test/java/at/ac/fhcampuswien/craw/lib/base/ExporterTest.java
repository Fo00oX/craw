package at.ac.fhcampuswien.craw.lib.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import at.ac.fhcampuswien.craw.lib.exceptions.CrawException;
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

    /**
     * @throws IOException thrown if file cannot be written to directory
     */
    @Test
    void writeYAMLToCustomDirectory() throws IOException {  //todo: use Exception Wrapper and add JavaDoc
        //arrange
        File YAML = new File(tempDir, "links.yml");
        final String expectedYAML = "links:" +
                "- name: google  url: www.google.at" +
                "- name: orf  url: www.orf.at" +
                "- name: github  url: www.github.at";

        //act
        this.exporter.writeYAML(YAML, links);
        File exportedFile = getFileFromDirectory(tempDir, "links.yml");

        //assert
        assertTrue(tempDir.isDirectory(), "Should be a directory");
        assertTrue(exportedFile.exists(), "YAML should exist");
        assertEquals("links.yml", exportedFile.getName(), "Should be named links.yml");
        assertEquals(expectedYAML, getFileContent(exportedFile), "Content should equal mocked content");
    }

    /**
     * @throws IOException thrown if file cannot be written to directory
     */
    @Test
    void writeEmptyYAMLToCustomDirectory() throws IOException {
        //arrange
        File YAML = new File(tempDir, "links.yml");
        final List<Weblink> emptyLinks = new ArrayList<>();

        //act
        exporter.writeYAML(YAML, emptyLinks);
        File exportedFile = getFileFromDirectory(tempDir, "links.yml");

        //assert
        assertTrue(tempDir.isDirectory(), "Should be a directory");
        assertTrue(exportedFile.exists(), "YAML should exist");
        assertEquals("links.yml", exportedFile.getName(), "Should be named links.yml");
        assertEquals("links: []", getFileContent(exportedFile), "Content should be empty");
    }

    /**
     * @throws CrawException thrown if file could not be written to directory, exported file could not be found, or
     *                       if file content could not be read
     */
    @Test
    void writeJSONToCustomDirectory() throws CrawException {
        //arrange
        File exportedFile;
        String fileContent;
        File JSON = new File(tempDir, "links.json");
        final String expectedJSON = "[" +
                "{\"name\":\"google\",\"url\":\"www.google.at\"}," +
                "{\"name\":\"orf\"," + "\"url\":\"www.orf.at\"}," +
                "{\"name\":\"github\",\"url\":\"www.github.at\"}" +
                "]";

        //act
        exporter.writeJSON(JSON, links);
        try {
            exportedFile = getFileFromDirectory(tempDir, "links.json");
            fileContent = getFileContent(exportedFile);
        } catch (FileNotFoundException fnfe) {
            throw new CrawException("Exported file could not be found in directory", fnfe);
        } catch (IOException ioe) {
            throw new CrawException("File content could not be read", ioe);
        }

        //assert
        assertTrue(tempDir.isDirectory(), "Should be a directory");
        assertTrue(exportedFile.exists(), "JSON should exist");
        assertEquals("links.json", exportedFile.getName(), "Should be named links.json");
        assertEquals(expectedJSON, fileContent, "Content should equal mocked content");
    }

    /**
     * @throws CrawException thrown if file could not be written to directory, exported file could not be found, or
     *                       if file content could not be read
     */
    @Test
    void writeEmptyJSONToCustomDirectory() throws CrawException {
        //arrange
        File exportedFile;
        String fileContent;
        File JSON = new File(tempDir, "links.json");
        final List<Weblink> emptyLinks = new ArrayList<>();

        //act
        exporter.writeJSON(JSON, emptyLinks);
        try {
            exportedFile = getFileFromDirectory(tempDir, "links.json");
            fileContent = getFileContent(exportedFile);
        } catch (FileNotFoundException fnfe) {
            throw new CrawException("Exported file could not be found in directory", fnfe);
        } catch (IOException ioe) {
            throw new CrawException("File content could not be read", ioe);
        }

        //assert
        assertTrue(tempDir.isDirectory(), "Should be a directory");
        assertTrue(exportedFile.exists(), "JSON should exist");
        assertEquals("links.json", exportedFile.getName(), "Should be named links.json");
        assertEquals("[]", fileContent, "Content should be empty");
    }

    private File getFileFromDirectory(File directory, String filename) throws FileNotFoundException {
        System.out.println(directory);

        for (File file : directory.listFiles()) {
            if (file.isFile() && file.getName().equals(filename)) {
                return file;
            }
        }
        throw new FileNotFoundException("file " + filename + " does not exist in this directory");
    }

    private String getFileContent(File file) throws IOException {
        String pathToFile = file.getPath();
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
