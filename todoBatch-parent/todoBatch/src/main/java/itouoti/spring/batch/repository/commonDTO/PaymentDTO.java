package itouoti.spring.batch.repository.commonDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * payment Table DTO
 * @author itouoti
 */
public class PaymentDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    int paymentId;

    @Setter
    @Getter
    short customerId;

    @Setter
    @Getter
    short staffId;

    @Setter
    @Getter
    int rentalId;

    @Setter
    @Getter
    BigDecimal amount;

    @Setter
    @Getter
    Timestamp paymentDate;

}
