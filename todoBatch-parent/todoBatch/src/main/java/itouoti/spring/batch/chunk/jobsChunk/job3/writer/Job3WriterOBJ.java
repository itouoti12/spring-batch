package itouoti.spring.batch.chunk.jobsChunk.job3.writer;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Job3WriterOBJ implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    int customerId;

    @Setter
    @Getter
    String name;

    @Setter
    @Getter
    String email;

    @Setter
    @Getter
    short addressId;

}
