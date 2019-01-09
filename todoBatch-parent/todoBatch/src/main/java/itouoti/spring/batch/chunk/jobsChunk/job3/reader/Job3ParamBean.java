package itouoti.spring.batch.chunk.jobsChunk.job3.reader;

import java.io.Serializable;

import itouoti.spring.batch.common.bean.ParamBean;
import lombok.Getter;
import lombok.Setter;

/**
 * パラメータ格納クラス
 * @author itouoti
 */
public class Job3ParamBean extends ParamBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * storeId
     */
    @Getter
    @Setter
    private int storeId;

}
