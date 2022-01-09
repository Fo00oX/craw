package at.ac.fhcampuswien.craw.lib.base;

import at.ac.fhcampuswien.craw.lib.exceptions.CrawException;
import at.ac.fhcampuswien.craw.lib.model.Weblink;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExporterTest {

    private final Exporter exporter = new Exporter();
    private static List<Weblink> links;
    private File exportedFile;
    private String fileContent;

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
     * @throws CrawException thrown if file could not be written to directory, exported file could not be found, or
     *                       if file content could not be read
     */
    @Test
    void writeYAMLToCustomDirectory() throws CrawException {
        //arrange
        File YAML = new File(tempDir, "links.yml");
        final String expectedYAML = "links:" +
                "- name: google  url: www.google.at" +
                "- name: orf  url: www.orf.at" +
                "- name: github  url: www.github.at";

        //act
        this.exporter.writeYAML(YAML, links);
        try {
            exportedFile = getFileFromDirectory(tempDir, "links.yml");
            fileContent = getFileContent(exportedFile);
        } catch (FileNotFoundException fnfe) {
            throw new CrawException("Exported YAML could not be found in directory", fnfe);
        } catch (IOException ioe) {
            throw new CrawException("YAML content could not be read", ioe);
        }

        //assert
        assertTrue(tempDir.isDirectory(), "Should be a directory");
        assertTrue(exportedFile.exists(), "YAML should exist");
        assertEquals("links.yml", exportedFile.getName(), "Should be named links.yml");
        assertEquals(expectedYAML, fileContent, "Content should equal mocked content");
    }

    /**
     * @throws CrawException thrown if file could not be written to directory, exported file could not be found, or
     *                       if file content could not be read
     */
    @Test
    void writeEmptyYAMLToCustomDirectory() throws CrawException {
        //arrange
        File YAML = new File(tempDir, "links.yml");
        final List<Weblink> emptyLinks = new ArrayList<>();

        //act
        exporter.writeYAML(YAML, emptyLinks);
        try {
            exportedFile = getFileFromDirectory(tempDir, "links.yml");
            fileContent = getFileContent(exportedFile);
        } catch (FileNotFoundException fnfe) {
            throw new CrawException("Exported YAML could not be found in directory", fnfe);
        } catch (IOException ioe) {
            throw new CrawException("YAML content could not be read", ioe);
        }

        //assert
        assertTrue(tempDir.isDirectory(), "Should be a directory");
        assertTrue(exportedFile.exists(), "YAML should exist");
        assertEquals("links.yml", exportedFile.getName(), "Should be named links.yml");
        assertEquals("links: []", fileContent, "Content should be empty");
    }

    /**
     * @throws CrawException thrown if file could not be written to directory, exported file could not be found, or
     *                       if file content could not be read
     */
    @Test
    void writeJSONToCustomDirectory() throws CrawException {
        //arrange
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
            throw new CrawException("Exported JSON could not be found in directory", fnfe);
        } catch (IOException ioe) {
            throw new CrawException("JSON content could not be read", ioe);
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
        File JSON = new File(tempDir, "links.json");
        final List<Weblink> emptyLinks = new ArrayList<>();

        //act
        exporter.writeJSON(JSON, emptyLinks);
        try {
            exportedFile = getFileFromDirectory(tempDir, "links.json");
            fileContent = getFileContent(exportedFile);
        } catch (FileNotFoundException fnfe) {
            throw new CrawException("Exported JSON could not be found in directory", fnfe);
        } catch (IOException ioe) {
            throw new CrawException("JSON content could not be read", ioe);
        }

        //assert
        assertTrue(tempDir.isDirectory(), "Should be a directory");
        assertTrue(exportedFile.exists(), "JSON should exist");
        assertEquals("links.json", exportedFile.getName(), "Should be named links.json");
        assertEquals("[]", fileContent, "Content should be empty");
    }

    private File getFileFromDirectory(File directory, String filename) throws FileNotFoundException {
        System.out.println(directory);

        File[] files = Objects.requireNonNull(directory.listFiles());
        for (File file : files) {
            if (file.isFile() && file.getName().equals(filename)) {
                return file;
            }
        }
        throw new FileNotFoundException("file " + filename + " does not exist in this directory");
    }

    private String getFileContent(File file) throws IOException {
        String pathToFile = file.getPath();
        String fileContent;
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            fileContent = sb.toString().replace("\n", "").replace("\r", "");
        }
        return fileContent;
    }
}
