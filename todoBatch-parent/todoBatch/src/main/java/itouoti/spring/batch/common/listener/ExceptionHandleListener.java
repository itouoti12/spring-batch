package itouoti.spring.batch.common.listener;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import itouoti.spring.batch.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

/**
 * ジョブの例外をハンドリングするためのリスナ
 * @author itouoti
 */
@Slf4j
@Component
public class ExceptionHandleListener implements StepExecutionListener {

    /**
     * messageSource
     */
    @Inject
    private MessageSource messageSource;

    /**
     * 処理の開始時に実行されるメソッド。例外ハンドリングのため何もしない。
     */
    @Override
    public void beforeStep(StepExecution stepExecution) {

        // stepExecutionにJobIDを設定
        String jobId = stepExecution.getJobExecution().getJobInstance().getJobName();
        stepExecution.getExecutionContext().put("jobId", jobId);

        // ジョブ開始ログ出力
        String param = stepExecution.getJobParameters().toString();
        Object[] os = { param };
        String msg = messageSource.getMessage("message.001", os, Locale.getDefault());
        log.info(msg);
    }

    /**
     * 例外の種別毎にログの出力を行う。
     */
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        List<Throwable> exceptions = stepExecution.getFailureExceptions();
        // 正常終了
        if (exceptions.isEmpty()) {

            // ジョブ終了ログ出力
            String param = stepExecution.getJobParameters().toString();
            Object[] os = { param };
            String msg = messageSource.getMessage("message.002", os, Locale.getDefault());
            log.info(msg);

            return ExitStatus.COMPLETED;
        }
        for (Throwable th : exceptions) {

            if (th instanceof SystemException) {
                // システムエラー発生時
                SystemException e = (SystemException) th;
                log.error(paramCreateMessage(stepExecution));
                log.error(systemCreateMessage(e), e);

            } else {
                // 想定外の例外発生
                Object[] os = { stepExecution.getJobParameters().toString() };
                String msg = messageSource.getMessage("message.003", os, Locale.getDefault());
                log.error(msg, th);

            }
        }

        return ExitStatus.FAILED;
    }

    /**
     * SystemException</br>
     * 例外時のメッセージを作成する。</br>
     * 共通的な情報を付与するためStepExecutionからパラメータ等を取得する。
     * @param e SystemException
     * @return 例外メッセージに共通情報を付与したメッセージ
     */
    private String systemCreateMessage(SystemException e) {
        Object[] os = {};
        String msg = messageSource.getMessage("message.003", os, Locale.getDefault());
        return msg;
    }

    /**
     * paramCreateMessage</br>
     * 例外時のジョブパラメータ出力用メッセージを作成する。</br>
     * 共通的な情報を付与するためStepExecutionからパラメータ等を取得する。
     * @param stepExecution StepExecution
     * @return 例外メッセージに共通情報を付与したメッセージ
     */
    private String paramCreateMessage(StepExecution stepExecution) {
        Object[] os = { stepExecution.getJobParameters().toString() };
        String msg = messageSource.getMessage("message.003", os, Locale.getDefault());
        return msg;
    }

}
