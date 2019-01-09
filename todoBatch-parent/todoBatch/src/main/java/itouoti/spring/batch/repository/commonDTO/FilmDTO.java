package itouoti.spring.batch.repository.commonDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * filmテーブルから取得した情報を格納するDTO
 * @author itouoti
 */
public class FilmDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * film_id
     */
    @Getter
    @Setter
    private int filmId;

    /**
     * title
     */
    @Getter
    @Setter
    private String title;

    /**
     * description
     */
    @Getter
    @Setter
    private String description;

    /**
     * release_year
     */
    @Getter
    @Setter
    private int releaseYear;

    /**
     * language_id
     */
    @Getter
    @Setter
    private short languageId;

    /**
     * rental_duration
     */
    @Getter
    @Setter
    private short rentalDuration;

    /**
     * rental_rate
     */
    @Getter
    @Setter
    private BigDecimal rentalRate;

    /**
     * length
     */
    @Getter
    @Setter
    private short langth;

    /**
     * replacement_cost
     */
    @Getter
    @Setter
    private BigDecimal replacementCost;

    /**
     * rating
     */
    @Getter
    @Setter
    private String rating;

    /**
     * last_update
     */
    @Getter
    @Setter
    private Timestamp lastUpdate;

    /**
     * special_features
     */
    @Getter
    @Setter
    private String specialFeatures;

    /**
     * fulltext
     */
    @Getter
    @Setter
    private String fullText;

}
