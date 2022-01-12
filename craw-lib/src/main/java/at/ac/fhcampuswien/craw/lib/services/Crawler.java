package at.ac.fhcampuswien.craw.lib.services;

import at.ac.fhcampuswien.craw.lib.exceptions.CrawException;
import at.ac.fhcampuswien.craw.lib.model.Weblink;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides functionality to download and parse Websites
 */
public class Crawler {

    /**
     * Searches a website for links
     * @param url of Website to crawl
     * @return Arraylist of Weblinks
     * @throws CrawException Is thrown when the URL is malformed or the website responds with an HTTP error
     */
    public ArrayList<Weblink> getLinks(String url) throws CrawException{
        ArrayList<Weblink> list = new ArrayList<>();
        try {
            //the regex looks for the pattern <a href="x"> "y"</a>
            //afterwards groups (x, y) are used to access collected information
            //for throughout explanation of the regex look at external sources like https://regex101.com/
            Pattern pattern = Pattern.compile("<a.+href=\"(.*?)\"[^>]*>(.*?)</a>");
            Matcher matcher = pattern.matcher(getHtml(url));
            while (matcher.find()) {
                list.add(new Weblink(matcher.group(1), matcher.group(2)));
            }
        } catch (MalformedURLException | URISyntaxException e) {
            throw new CrawException("Url wasn't properly formed. " + e.getMessage(), e);
        } catch (IOException e){
            throw new CrawException(e.getMessage(), e);
        }
        return list;
    }

    /**
     * Returns HTML of a requested website
     * @param url of Website to get
     * @return html as String
     * @throws URISyntaxException is thrown by errors in Libraries
     * @throws IOException is thrown by errors in Libraries
     */
    private String getHtml(String url) throws URISyntaxException, IOException {
        String html;
        URLConnection connection;
        URL testedUrl = new URL(url);
        //testing the url for invalid characters
        testedUrl.toURI();
        connection = testedUrl.openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        //delimiter ensures that we get the entire HTML
        scanner.useDelimiter("\\Z");
        html = scanner.next();
        scanner.close();
        return html;
    }
}
