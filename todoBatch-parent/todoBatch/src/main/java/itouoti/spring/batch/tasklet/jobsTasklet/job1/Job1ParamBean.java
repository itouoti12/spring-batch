package itouoti.spring.batch.tasklet.jobsTasklet.job1;

import java.io.Serializable;

import itouoti.spring.batch.common.bean.ParamBean;
import lombok.Getter;
import lombok.Setter;

/**
 * パラメータ格納クラス
 * @author itouoti
 */
public class Job1ParamBean extends ParamBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * filmId
     */
    @Getter
    @Setter
    private String filmId;

}
