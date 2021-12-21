package at.ac.fhcampuswien.craw.lib.base;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ExporterTest {

    Path path;
    File file;

    /* This directory and the files created in it will be deleted after
     * tests are run, even in the event of failures or exceptions.
     */
    @TempDir
    Path tempDir;

    /* executed before every test: create two temporary files */
    @BeforeEach
    public void setUp() {
        try {
            path = tempDir.resolve("testfile1.json");
        } catch (InvalidPathException ipe) {
            System.err.println("error creating temporary test file in " + this.getClass().getSimpleName());
        }

        file = path.toFile();
    }

    /**
     * Write to the two temporary files and run some asserts.
     */
    @Test
    public void writeJsonToFile() {

        //write out data to the test files
        try {
            FileWriter fw1 = new FileWriter(file);
            BufferedWriter bw1 = new BufferedWriter(fw1);

            JSONObject testJSON = new JSONObject();
            testJSON.put("Link1", "www.google.at");

            bw1.write(testJSON.toJSONString());
            bw1.close();
        } catch (IOException ioe) {
            System.err.println("error creating temporary test file in " + this.getClass().getSimpleName());
        }

        assertTrue(file.exists());
        assertTrue(file.isFile());

        assertTrue(file.getAbsolutePath().endsWith("testfile1.json"));
    }
}