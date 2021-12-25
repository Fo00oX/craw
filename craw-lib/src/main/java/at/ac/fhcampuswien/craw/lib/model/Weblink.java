package at.ac.fhcampuswien.craw.lib.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Weblink {
    private String URL;
    private String Name;

    public String prettifiedString() {
        return String.format("%s:  %s", getName(), getURL());
    }
}
