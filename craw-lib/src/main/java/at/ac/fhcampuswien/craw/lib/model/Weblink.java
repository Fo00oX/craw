package at.ac.fhcampuswien.craw.lib.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Weblink {
    private String URL;
    private String Name;
}
