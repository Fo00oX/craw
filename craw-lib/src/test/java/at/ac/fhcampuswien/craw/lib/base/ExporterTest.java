package at.ac.fhcampuswien.craw.lib.base;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
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

    /* executed before every test: create two temporary files */
    @BeforeEach
    public void setUp() {
        this.links = new ArrayList<>();
        this.links.add(new Weblink("www.google.at", "google"));
        this.links.add(new Weblink("www.orf.at", "orf"));
        this.links.add(new Weblink("www.github.at", "github"));

        try {
        this.jsonArray = exporter.convertLinksToJsonFormat(links);

        } catch (Exception e){
            System.out.println(e);
        }

        exporter = new Exporter();
    }

    /**
     * Write to the two temporary files and run some asserts.
     */
    @Test
    public void writeJSONToFile() {
        //write out data to the test files
        setUp();
        file = exporter.createJSONFile();
        exporter.saveLinksAsJSON(jsonArray);

        assertTrue(file.exists());
        assertTrue(file.isFile());

        assertTrue(file.getAbsolutePath().endsWith(".json"));
    }
}