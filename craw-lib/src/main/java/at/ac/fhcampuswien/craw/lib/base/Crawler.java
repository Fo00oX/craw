package at.ac.fhcampuswien.craw.lib.base;

import at.ac.fhcampuswien.craw.lib.model.Weblink;

import java.util.List;

/**
 * Provides functionality to download and parse Websites
 */
public class Crawler {
    public List<Weblink> getLinks(String link) {
        //TODO add functionality
        return List.of(
                // Testdata
                new Weblink(
                        "https://track.craw.dataspeak.at/",
                        "Link1"
                ),
                new Weblink(
                        "https://track.craw.dataspeak.at/projects/61edb02c-bc65-4a0a-a71b-9b0639200aba/",
                        "Link2"
                )
        );
    }
}
