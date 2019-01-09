package itouoti.spring.batch.testCommon.servicePostProcessor;

/**
 * batch job系ServiceImplクラスを集めたクラス
 * @author itouoti
 */
public class ServiceImplList {

    /**
     * batch job系ServiceImplのインスタンスチェックを行い、 </br>
     * 該当のクラスをプロキシクラスを通して代替する。
     * @param bean チェック対象
     * @return boolean
     */
    public static Object serviceImplInstanceOf(
                                               Object bean) {

        return bean;

    }
}
