package at.ac.fhcampuswien.craw.lib.advanced;



import at.ac.fhcampuswien.craw.lib.model.BrokenLink;
import at.ac.fhcampuswien.craw.lib.model.Weblink;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

public class LinkChecker {

    //TODO: JavaDoc and Unit Tests

    public List<Weblink> checkLinks (List<Weblink> urls){
        List<Weblink> brokenLinks = new ArrayList<>();
        for (Weblink weblink: urls){
            try {
                URL url = new URL(weblink.getURL());
                try {
                    /*
                    Doesn't support HTTP links.
                    https://docs.oracle.com/javase/7/docs/api/java/net/URL.html#openConnection()
                    */
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    int responseCode = connection.getResponseCode();

                    // Currently only checking if server doesn't respond with status code 200. Implies that redirects are not supported.
                    if (responseCode != HTTP_OK){
                        BrokenLink brokenLink = new BrokenLink(weblink.getURL(), weblink.getName(), BrokenLink.States.NOT_OK);
                        brokenLinks.add(brokenLink);
                    }
                } catch (IOException e){ // URL is well-formed but issue during connecting to host(not reachable) or writing/reading from host.
                    BrokenLink brokenLink = new BrokenLink(weblink.getURL(), weblink.getName(), BrokenLink.States.CONNECTION_ERROR);
                    brokenLinks.add(brokenLink);

                }

            } catch (MalformedURLException e) { // Weblink could not be parsed or no legal protocol could be found
                BrokenLink brokenLink = new BrokenLink(weblink.getURL(), weblink.getName(), BrokenLink.States.MALFORMED);
                brokenLinks.add(brokenLink);
            }
        }
        return brokenLinks;
    }
}
