package itouoti.spring.batch.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ログファイル出力用ロガークラス
 * @author itouoti
 */
public class LoggerWrapper {

    private static Logger logger = LoggerFactory.getLogger(LoggerWrapper.class);

    /**
     * 情報ログをログに出力する
     * @param msg 出力メッセージ
     */
    public static void info(
            String msg) {
        logger.info(msg);
    }

    /**
     * 警告ログをログに出力する
     * @param msg 出力メッセージ
     */
    public static void warn(
            String msg) {
        logger.warn(msg);
    }

    /**
     * エラーログをログに出力する
     * @param msg 出力メッセージ
     */
    public static void error(
            String msg) {
        logger.error(msg);
    }

}
