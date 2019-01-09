package itouoti.spring.batch.repository.commonDTO;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * rental Table DTO
 * @author itouoti
 */
public class RentalDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    int rentalId;

    @Setter
    @Getter
    Timestamp rentalDate;

    @Setter
    @Getter
    int inventoryId;

    @Setter
    @Getter
    short customerId;

    @Setter
    @Getter
    Timestamp returnDate;

    @Setter
    @Getter
    short staffId;

    @Setter
    @Getter
    Timestamp lastUpdate;

}
