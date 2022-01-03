package at.ac.fhcampuswien.craw.lib.advanced;

import at.ac.fhcampuswien.craw.lib.model.Weblink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkCheckerTest {

    private LinkChecker linkChecker;
    private List<Weblink> weblinks = new ArrayList<>();
    private List<Weblink> brokenWeblinks = new ArrayList<>();
    private List<Weblink> brokenWeblinksResults = new ArrayList<>();
    private List<Weblink> weblinksAllValid = new ArrayList<>();
    private List<Weblink> empty = new ArrayList<>();


    @BeforeEach
    void setup() {
        linkChecker = new LinkChecker();
        Weblink link1 = new Weblink("https://google.com", "Google");
        Weblink link2 = new Weblink("https://stackoverflow.com/questions/14414530/how-to-pass-enum-as-an-argument-in-a-method-in-java", "GitHUb");
        Weblink link3 = new Weblink("https://airbrake.io/blog/java/connectexception", "Airbrake");
        Weblink link4 = new Weblink("www.google.com", "Google malformed");
        Weblink link5 = new Weblink("https://whatisit.do", "Valid but typo");

        weblinks.add(link1);
        weblinks.add(link2);
        weblinks.add(link3);
        weblinks.add(link4);
        weblinks.add(link5);
        brokenWeblinksResults.add(link4);
        brokenWeblinksResults.add(link5);
        weblinksAllValid.add(link1);
        weblinksAllValid.add(link2);
        weblinksAllValid.add(link3);
    }


    @Test
    void checkLinks() {
        setup();
        brokenWeblinks = linkChecker.checkLinks(weblinks);
        assertEquals(brokenWeblinks.get(0).getURL(), brokenWeblinksResults.get(0).getURL());
        assertEquals(brokenWeblinks.get(1).getURL(), brokenWeblinksResults.get(1).getURL());
    }

    @Test
    void checkLinksEmpty() {
        brokenWeblinks = linkChecker.checkLinks(empty);
        assertEquals(brokenWeblinks, new ArrayList<>());
    }

    @Test
    void checkLinksAllValid() {
        brokenWeblinks = linkChecker.checkLinks(weblinksAllValid);
        assertEquals(brokenWeblinks, new ArrayList<>());
    }

    @Test
    void checkLinksNull() {
        brokenWeblinks = linkChecker.checkLinks(null);
        assertEquals(brokenWeblinks, new ArrayList<>());
    }

}