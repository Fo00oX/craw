package at.ac.fhcampuswien.craw.lib.base;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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

    }

    @Test
    public void writeJSON() throws IOException {
        setUp();
        exporter = new Exporter();


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