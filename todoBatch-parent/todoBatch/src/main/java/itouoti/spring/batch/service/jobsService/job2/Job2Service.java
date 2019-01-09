package itouoti.spring.batch.service.jobsService.job2;

import itouoti.spring.batch.tasklet.jobsTasklet.job2.Job2ParamBean;

/**
 * サービス層インタフェース
 * @author itouoti
 */
public interface Job2Service {

    /**
     * サービス層メインメソッド
     * @param input 起動パラメータ
     * @throws 例外
     */
    public void startService(Job2ParamBean input) throws Exception;

}
