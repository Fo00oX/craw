package at.ac.fhcampuswien.craw.lib.advanced;

import at.ac.fhcampuswien.craw.lib.model.Weblink;

import java.util.List;

public class GoogleSearch {
    public List<Weblink> searchQuery(String query, int anz) {
        //TODO add functionality
        return List.of(
                Weblink.builder()
                        .URL("https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiO7e6og_P0AhWBh_0HHcY5DmcQFnoECA0QAQ&url=https%3A%2F%2Fwww.fh-campuswien.ac.at%2F&usg=AOvVaw3xp-GzQKJbEHJ3wxY7gCZ5")
                        .Name("FH Campus Wien")
                        .build(),
                Weblink.builder()
                        .URL("https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiO7e6og_P0AhWBh_0HHcY5DmcQFnoECAUQAQ&url=https%3A%2F%2Fde.wikipedia.org%2Fwiki%2FFH_Campus_Wien&usg=AOvVaw2pwpFR0XhPSm99QMWmWTaF")
                        .Name("FH Campus Wien - Wikipedia")
                        .build()
        );
    }
}
