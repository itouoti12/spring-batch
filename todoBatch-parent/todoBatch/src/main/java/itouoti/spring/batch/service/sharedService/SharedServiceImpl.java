package itouoti.spring.batch.service.sharedService;

import java.text.NumberFormat;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import itouoti.spring.batch.repository.sharedRepository.SharedRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * UtilService サービス層共通部分実装クラス
 * @author itouoti
 */
@Service
@Scope("step")
public class SharedServiceImpl implements SharedService {

    @Inject
    private SharedRepository sharedRepository;

    @Inject
    private MessageSource messageSource;

    @Value("#{stepExecutionContext[jobId]}")
    private String jobId;

    /**
     * ログにInfo情報を出力する
     * @param msgParam application-messages.propertiesのパラメーターキー
     * @param insertMsg メッセージ情報 </br>
     *            配列で格納可</br>
     *            str[0]・・・{0}</br>
     *            str[1]・・・{1}</br>
     *            でパラメーターキーで呼び出される文字列にマッピングされる
     */
    @Override
    public void outputInfoMsgToLog(
                                   String msgParam,
                                   String[] insertMsg) {
        final Logger jobLog = org.slf4j.LoggerFactory.getLogger(this.jobId);

        String msg = messageSource.getMessage(msgParam, insertMsg, Locale.getDefault());
        jobLog.info(msg);

    }

    /**
     * Long型の数値をカンマ編集して返す
     * @param value 編集前数値
     * @return カンマ編集後文字列
     */
    @Override
    public String longToConma(long value) {

        NumberFormat conmaFormat = NumberFormat.getNumberInstance();

        return conmaFormat.format(value);
    }

}
