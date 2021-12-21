package at.ac.fhcampuswien.craw.lib.base;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ExporterTest {

    Path path1, path2;
    File file1, file2;

    /* This directory and the files created in it will be deleted after
     * tests are run, even in the event of failures or exceptions.
     */
    @TempDir
    Path tempDir;

    /* executed before every test: create two temporary files */
    @BeforeEach
    public void setUp() {
        try {
            path1 = tempDir.resolve( "testfile1.json" );
            path2 = tempDir.resolve( "testfile2.jml" );
        }
        catch( InvalidPathException ipe ) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName() );
        }

        file1 = path1.toFile();
        file2 = path2.toFile();
    }

    /**
     *  Write to the two temporary files and run some asserts.
     */
    @Test
    public void test2TempFiles() {

        //write out data to the test files
        try {
            FileWriter fw1 = new FileWriter( file1 );
            BufferedWriter bw1 = new BufferedWriter( fw1 );
            bw1.write( "content for file1");
            bw1.close();

            FileWriter fw2 = new FileWriter( file2 );
            BufferedWriter bw2 = new BufferedWriter( fw2 );
            bw2.write( "content for file2" );
            bw2.close();
        }
        catch( IOException ioe ) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName() );
        }

        assertTrue( file1.exists() );
        assertTrue( file2.isFile() );

        assertEquals( file1.length(), 17L );
        assertEquals( file1.length(), file2.length() );

        assertTrue( file1.getAbsolutePath().endsWith(
                "testfile1.json" ));
    }
}