package itouoti.spring.batch.repository.commonDTO;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * customer Table DTO
 * @author itouoti
 *
 */
public class CustomerDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    int customerId;

    @Setter
    @Getter
    short storeId;

    @Setter
    @Getter
    String firstName;

    @Setter
    @Getter
    String lastName;

    @Setter
    @Getter
    String email;

    @Setter
    @Getter
    short addressId;

    @Setter
    @Getter
    boolean activebool;

    @Setter
    @Getter
    Date createDate;

    @Setter
    @Getter
    Timestamp lastupdate;

    @Setter
    @Getter
    int active;



}
