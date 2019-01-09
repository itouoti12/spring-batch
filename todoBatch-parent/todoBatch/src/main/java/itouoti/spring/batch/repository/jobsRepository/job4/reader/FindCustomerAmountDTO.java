package itouoti.spring.batch.repository.jobsRepository.job4.reader;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * customer Table DTO
 * @author itouoti
 */
public class FindCustomerAmountDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    int customerId;

    @Setter
    @Getter
    String firstName;

    @Setter
    @Getter
    String lastName;

    @Setter
    @Getter
    long amount;

}
