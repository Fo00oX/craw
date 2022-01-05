package at.ac.fhcampuswien.craw.lib.base;

import at.ac.fhcampuswien.craw.lib.model.Weblink;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class crawlerTests {
    @Test
    void testGetHtml(){
        Crawler crawler = new Crawler();
        assertTrue(crawler.getHtml("http://www.google.com").contains("</body>"), "No proper HTML fetched");
    }

    @Test
    void testGetList(){
        Crawler crawler = new Crawler();
        Weblink test = new Weblink("https://board.z0r.de/", "Forum");
        for (Weblink link :
                crawler.getLinks("https://z0r.de/36")) {
           if (link.getURL().equals(test.getURL()))
            {
                assertTrue(true);
            }
        }
    }
}
