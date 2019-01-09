package itouoti.spring.batch.repository.commonDTO;

import java.io.Serializable;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * link Table DTO
 * @author itouoti
 */
public class LinkDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    int id;

    @Setter
    @Getter
    String url;

    @Setter
    @Getter
    String name;

    @Setter
    @Getter
    String description;

    @Setter
    @Getter
    String rel;

    @Setter
    @Getter
    Date lastUpdate;

}
