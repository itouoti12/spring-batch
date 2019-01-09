package itouoti.spring.batch.tasklet.jobsTasklet.job2;

import java.io.Serializable;

import itouoti.spring.batch.common.bean.ParamBean;
import lombok.Getter;
import lombok.Setter;

/**
 * パラメータ格納クラス
 * @author itouoti
 */
public class Job2ParamBean extends ParamBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * テーブルスキーマ名
     */
    @Getter
    @Setter
    private String schema;

    /**
     * インサートフラグ </br>
     * 0:CreanInsert </br>
     * 1:Insert
     */
    @Getter
    @Setter
    private int crean;

    /**
     * スキーマ指定フラグ</br>
     * 0:xlsxでスキーマ指定をしない（単一スキーマINSERT）</br>
     * 1:xlsxでスキーマ指定（複数スキーマINSERT）
     */
    @Getter
    @Setter
    private int qualified;

}
