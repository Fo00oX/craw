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

    File file;
    List<Weblink> links;
    Exporter exporter;
    JSONArray jsonArray;

    @BeforeEach
    public void setUp() {
        this.links = new ArrayList<>();
        this.links.add(new Weblink("www.google.at", "google"));
        this.links.add(new Weblink("www.orf.at", "orf"));
        this.links.add(new Weblink("www.github.at", "github"));

        exporter = new Exporter();
    }

    @Test
    public void writeJSON() throws IOException {
        setUp();
        try {
            this.jsonArray = exporter.convertLinksToJsonFormat(links);

        } catch (Exception e) {
            System.out.println(e);
        }

        new File("../temp/").mkdirs();
        String pathToFile = "../temp/links.json";
        file = new File(pathToFile);
        exporter.writeJSON(pathToFile, this.links);

        assert (file.exists());
        assert (file.isFile());
        assertEquals(file.getName(), "links.json");

        String fileContent = getFileContent(pathToFile);

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