package at.ac.fhcampuswien.craw.lib.base;

import at.ac.fhcampuswien.craw.lib.exceptions.CrawException;
import at.ac.fhcampuswien.craw.lib.model.Weblink;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CrawlerTests {

    @Parameterized.Parameters
    public static Stream<Arguments> input() {
        ArrayList<Weblink> z0r36 = new ArrayList<>();
        z0r36.add(new Weblink("https://board.z0r.de/", "Forum"));
        z0r36.add(new Weblink("vote.php?id=36&vote=3", "broken"));
        z0r36.add(new Weblink("https://z0r.de/datenschutz.php", "Privacy policy"));
        ArrayList<Weblink> suckless = new ArrayList<>();
        suckless.add(new Weblink("//dwm.suckless.org/", "dwm"));
        suckless.add(new Weblink("//libs.suckless.org/deprecated/", "deprecated"));
        ArrayList<Weblink> reddit = new ArrayList<>();
        reddit.add(new Weblink("https://old.reddit.com/r/popular/", "popular"));
        return Stream.of(
                Arguments.of("https://z0r.de/36", z0r36, false),

                Arguments.of("https://libs.suckless.org/", suckless, false),
                //check if an incorrect protocol fails
                Arguments.of("htps://z0r.de/36", z0r36, true),
                //check if invalid characters fail
                Arguments.of("https://z>0r.de/36", z0r36, true),
                //check if http error are handled
                Arguments.of("https://old.reddit.com/r/all/", reddit, true)
        );
    }

    @ParameterizedTest
    @MethodSource("input")
    void testGetLinks(String url, ArrayList<Weblink> weblinksTest, boolean expectException) {
        Crawler crawler = new Crawler();
        ArrayList<Weblink> weblinks = new ArrayList<>();
        int found = 0;
        boolean caught = false;
        try {
            weblinks = crawler.getLinks(url);
        } catch (CrawException e){
            assertTrue(expectException);
            caught = true;
        }
        catch (Exception e){
            fail();
            caught = true;
        }
        for (Weblink link :
                weblinks) {
            for (Weblink linkTest:
                 weblinksTest) {
                if (link.getUrl().equals(linkTest.getUrl())) {
                    found++;
                }
            }

        }
        assertTrue(found == weblinksTest.size() || caught);
    }
}
