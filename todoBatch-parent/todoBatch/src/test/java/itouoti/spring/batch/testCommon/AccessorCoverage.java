package itouoti.spring.batch.testCommon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * getter/setterのカバレッジ取得用クラス
 * @author itouoti from ryu22ee(github)
 */
public class AccessorCoverage {
    private static Map<String, Object> type2PrimitiveValue;
    {
        type2PrimitiveValue = new HashMap<String, Object>();
        type2PrimitiveValue.put("boolean", true);
        type2PrimitiveValue.put("byte", (byte) 123);
        type2PrimitiveValue.put("short", (short) 123);
        type2PrimitiveValue.put("int", 123);
        type2PrimitiveValue.put("long", 123L);
        type2PrimitiveValue.put("float", 123f);
        type2PrimitiveValue.put("double", 123d);
        Date coveradgeDate = new Date(System.currentTimeMillis());
        type2PrimitiveValue.put("Date", coveradgeDate);
        Timestamp coveradgeTimestamp = new Timestamp(System.currentTimeMillis());
        type2PrimitiveValue.put("Timestamp", coveradgeTimestamp);
        BigDecimal coveradgeBigDecimal = new BigDecimal("123");
        type2PrimitiveValue.put("BigDecimal", coveradgeBigDecimal);
    }

    /**
     * 単純なgetterメソッドとsetterメソッドを呼ぶ。<br />
     * カバレッジを100%にするのが目的なので、呼び出した結果については検証しない。
     * @param object テスト対象のオブジェクト。
     * @param ignoreMethodNames 呼び出したくないメソッドの名前。
     * @throws IllegalArgumentException objectがnullの場合。
     * @throws InvocationTargetException テスト対象のオブジェクトがメソッド呼び出しに失敗した場合。
     * @throws IllegalAccessException テスト対象のオブジェクトがメソッド呼び出しに失敗した場合。
     * @throws IllegalArgumentException テスト対象のオブジェクトがメソッド呼び出しに失敗した場合。
     */
    public void invokeGetterSetter(
                                   Object object,
                                   String... ignoreMethodNames) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (object == null) {
            throw new IllegalArgumentException();
        }

        Set<String> ignoreMethodNamesSet = new HashSet<String>();
        for (String ignoreMethod : ignoreMethodNames) {
            ignoreMethodNamesSet.add(ignoreMethod);
        }

        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            // ignoreMethodNamesに一致するメソッドは実行しない。
            if (ignoreMethodNamesSet.contains(methodName)) {
                continue;
            }
            if (Pattern.matches("^(get|is).+", methodName)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 0) {
                    // getterの呼び出し。
                    method.invoke(object);
                }
            } else if (Pattern.matches("^set.+", methodName)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    // パラメータの型がプリミティブならtype2PrimitiveValueから取得した値をsetterに渡す。非プリミティブならnullをsetterに渡す。
                    String type = parameterTypes[0].getName();
                    Object param = type2PrimitiveValue.get(type);
                    // setterの呼び出し。
                    method.invoke(object, new Object[] { param });
                }
            }
        }
    }

}
