package itouoti.spring.batch.testCommon;

import org.dbunit.dataset.ITable;

/**
 * テストクラスの汎用的なメソッド
 * @author itouoti
 */
public class TestHelper {

    /**
     * xlsxファイルから取り出した項目をint型に変換する
     * @param it xlsxファイル
     * @param i 行番号
     * @param name 列名
     * @return Integer
     * @throws Exception 例外
     */
    public static Integer toInt(
            ITable it,
            int i,
            String name) throws Exception {

        Object o = it.getValue(i, name);
        if (o != null) {
            return Integer.parseInt((o.toString()));
        }
        return null;
    }

    /**
     * xlsxファイルから取り出した項目をLong型に変換する
     * @param it xlsxファイル
     * @param i 行番号
     * @param name 列名
     * @return Integer
     * @throws Exception 例外
     */
    public static Long toLong(
            ITable it,
            int i,
            String name) throws Exception {

        Object o = it.getValue(i, name);
        if (o != null) {
            return Long.parseLong((o.toString()));
        }
        return null;
    }

    /**
     * xlsxファイルから取り出した項目をboolean型に変換する
     * @param it xlsxファイル
     * @param i 行番号
     * @param name 列名
     * @return Boolean
     * @throws Exception 例外
     */
    public static Boolean toBool(
            ITable it,
            int i,
            String name) throws Exception {

        Object o = it.getValue(i, name);
        if (o != null) {
            return Boolean.parseBoolean((o.toString()));
        }
        return null;
    }

    /**
     * xlsxファイルから取り出した項目をString型に変換する
     * @param it xlsxファイル
     * @param i 行番号
     * @param name 列名
     * @return String
     * @throws Exception 例外
     */
    public static String toStr(
            ITable it,
            int i,
            String name) throws Exception {

        Object o = it.getValue(i, name);
        if (o != null) {
            return o.toString();
        }
        return null;
    }

    /**
     * xlsxファイルから取り出した項目をString型に変換する
     * @param it xlsxファイル
     * @param i 行番号
     * @param name 列名
     * @return String
     * @throws Exception 例外
     */
    public static Short toShort(
            ITable it,
            int i,
            String name) throws Exception {

        Object o = it.getValue(i, name);
        if (o != null) {
            return Short.parseShort((o.toString()));
        }
        return null;
    }
}
