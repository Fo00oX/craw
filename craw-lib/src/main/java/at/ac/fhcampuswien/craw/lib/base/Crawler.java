package at.ac.fhcampuswien.craw.lib.base;

import at.ac.fhcampuswien.craw.lib.model.Weblink;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides functionality to download and parse Websites
 */
public class Crawler {

    int depth;

    Crawler(int depth){
        this.depth = depth;
    }
    Crawler(){
        this.depth = 1;
    }

    public ArrayList<Weblink> getLinks(String link){
        ArrayList<Weblink> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("<a.+href=\"(.*?)\"[^>]*>(.*?)</a>");
        Matcher matcher = pattern.matcher(getHtml(link));
        while(matcher.find()){
            list.add(new Weblink(matcher.group(1), matcher.group(2)));
        }
        return list;
    }

    public String getHtml(String link){
        String html = null;
        URLConnection connection;
        try {
            connection = new URL(link).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            //delimiter ensures that we get the entire HTML
            scanner.useDelimiter("\\Z");
            html = scanner.next();
            scanner.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }
}
