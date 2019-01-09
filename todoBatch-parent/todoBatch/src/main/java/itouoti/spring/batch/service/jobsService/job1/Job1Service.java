package itouoti.spring.batch.service.jobsService.job1;

import itouoti.spring.batch.tasklet.jobsTasklet.job1.Job1ParamBean;

/**
 * サービス層インタフェース
 * @author itouoti
 */
public interface Job1Service {

    /**
     * サービス層メインメソッド
     * @param bean 起動パラメータ </br>
     *            filmId
     */
    public void startService(
                             Job1ParamBean bean);

}
