package itouoti.spring.batch.repository.commonDTO;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * t1 Table DTO
 * @author itouoti
 */
public class T1DTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    int id;

    @Setter
    @Getter
    String bcolor;

    @Setter
    @Getter
    String fcolor;

}
