package at.ac.fhcampuswien.craw.lib.advanced;


import at.ac.fhcampuswien.craw.lib.model.BrokenLink;
import at.ac.fhcampuswien.craw.lib.model.Weblink;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

public class LinkChecker {

    /**
     * Returns a list of broken links in form of a list of BrokenLink Object(inherits from Weblink)
     * where the status is saved as an attribute.
     * There are three cases of "brokenness":
     * - malformed URL
     * - could not write/read from or connect to URL
     * - HTTP response is not 200
     *
     *
     * @param urls is a list of Weblink Objects gathered by the Crawler
     * @return a list containing the broken links from the list of URLs
     */
    public List<Weblink> checkLinks(List<Weblink> urls) {
        List<Weblink> brokenLinks = new ArrayList<>();

        // Added because it is the only way to halt execution of function without crashing. Null should never be passed, but just in case.
        if (urls == null) {
            return brokenLinks;
        }

        for (Weblink weblink : urls) {
            try {
                URL url = new URL(weblink.getUrl());
                try {
                    /*
                    https://docs.oracle.com/javase/7/docs/api/java/net/URL.html#openConnection()
                    */
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int responseCode = connection.getResponseCode();

                    // Currently only checking if server doesn't respond with status code 200. Implies that redirects are not supported.
                    if (responseCode != HTTP_OK) {
                        BrokenLink brokenLink = new BrokenLink(weblink.getUrl(), weblink.getName(), BrokenLink.States.NOT_OK);
                        brokenLinks.add(brokenLink);
                    }
                } catch (IOException e) { // URL is well-formed but issue during connecting to host(not reachable) or writing/reading from host.
                    BrokenLink brokenLink = new BrokenLink(weblink.getUrl(), weblink.getName(), BrokenLink.States.CONNECTION_ERROR);
                    brokenLinks.add(brokenLink);

                }

            } catch (MalformedURLException e) { // Weblink could not be parsed or no legal protocol could be found
                BrokenLink brokenLink = new BrokenLink(weblink.getUrl(), weblink.getName(), BrokenLink.States.MALFORMED);
                brokenLinks.add(brokenLink);
            }
        }
        return brokenLinks;
    }
}
