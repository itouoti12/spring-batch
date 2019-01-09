package itouoti.spring.batch.chunk.jobsChunk.job4.writer;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Job4WriterOBJ implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    int customerId;

    @Setter
    @Getter
    String fullName;

    @Setter
    @Getter
    long amount;

}
