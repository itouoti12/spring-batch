package itouoti.spring.batch.service.sharedService;

/**
 * UtilService サービス層共通部分インタフェース
 * @author itouoti
 */
public interface SharedService {

    /**
     * ログにInfo情報を出力する
     * @param msgParam application-messages.propertiesのパラメーターキー
     * @param insertMsg メッセージ情報 </br>
     *            配列で格納可</br>
     *            str[0]・・・{0}</br>
     *            str[1]・・・{1}</br>
     *            でパラメーターキーで呼び出される文字列にマッピングされる
     */
    public void outputInfoMsgToLog(
                                   String msgParam,
                                   String[] insertMsg);

    /**
     * Long型の数値をカンマ編集して返す
     * @param value 編集前数値
     * @return カンマ編集後文字列
     */
    public String longToConma(long value);

}
