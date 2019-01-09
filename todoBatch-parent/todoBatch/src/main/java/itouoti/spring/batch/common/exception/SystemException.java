package itouoti.spring.batch.common.exception;

/**
 * システム例外クラス
 * @author itouoti
 */
public class SystemException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param cause 例外
     */
    public SystemException(Throwable cause) {
        super(cause);
    }
}
