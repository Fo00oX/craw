package at.ac.fhcampuswien.craw.lib.base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import at.ac.fhcampuswien.craw.lib.model.Weblink;

public class ExporterTest {

    private File file;
    private List<Weblink> links;
    private Exporter exporter;
    private JSONArray jsonArray;
    private String pathToFile;
    private String testYaml;

    @BeforeEach
    public void setUp() {
        this.links = new ArrayList<>();
        this.links.add(new Weblink("www.google.at", "google"));
        this.links.add(new Weblink("www.orf.at", "orf"));
        this.links.add(new Weblink("www.github.at", "github"));

        this.testYaml = "links:"
                + "- {URL: www.google.at, name: google}"
                + "- {URL: www.orf.at, name: orf}"
                + "- {URL: www.github.at, name: github}";

        exporter = new Exporter();

    }

    @Test
    public void writeYAML() throws IOException {
        setUp();
        this.pathToFile = "../temp/links.yaml";
        this.exporter.writeYAML(this.pathToFile, links);

        file = new File(pathToFile);
        assert (file.exists());
        assert (file.isFile());
        assertEquals("links.yaml", this.file.getName());

        assertEquals(this.testYaml, getFileContent(pathToFile));
    }

    @Test
    public void writeJSON() throws IOException {
        setUp();
        this.pathToFile = "../temp/links.json";
        file = new File(pathToFile);
        try {
            this.jsonArray = exporter.convertLinksToJsonFormat(links);

        } catch (Exception e) {
            System.out.println(e);
        }

        exporter.writeJSON(this.pathToFile, this.links);

        assert (file.exists());
        assert (file.isFile());
        assertEquals("links.json", file.getName());

        String fileContent = getFileContent(this.pathToFile);

        assertEquals(this.jsonArray.toString(), fileContent);
    }

    private String getFileContent(String pathToFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(pathToFile));
        String fileContent = "";
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            fileContent = sb.toString().replace("\n", "").replace("\r", "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        return fileContent;
    }
}