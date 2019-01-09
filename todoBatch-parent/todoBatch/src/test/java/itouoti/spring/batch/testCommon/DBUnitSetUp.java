package itouoti.spring.batch.testCommon;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.util.ResourceUtils;

/**
 * データベースセットアップ用クラス
 * @author itouoti
 */
public class DBUnitSetUp {

    /**
     * テスト後テーブル状態の取得処理
     * @param masterDataSource データソース
     * @param tableName 状態取得対象テーブル名
     * @return ITable
     * @throws Exception 例外
     */
    public static ITable getAtualDataTable(
                                           TransactionAwareDataSourceProxy masterDataSource,
                                           String tableName) throws Exception {

        // コネクション取得
        IDatabaseConnection con = getConnection(masterDataSource);

        // ＤＢからテーブルの状態を取得
        IDataSet databaseDataSet = con.createDataSet();
        ITable actualTable = databaseDataSet.getTable(tableName);

        return actualTable;

    }

    /**
     * xlsxよりテーブル状態検証用のデータを取得する処理
     * @param resourceLoader ローダー
     * @param xlsxExpectedDataFile 検証用データxlsx
     * @param tableName 検証対象テーブル名
     * @return ITable
     * @throws Exception 例外
     */
    public static ITable getExpectedDataTable(
                                              ResourceLoader resourceLoader,
                                              String xlsxExpectedDataFile,
                                              String tableName) throws Exception {

        // xlsxから検証用データを取得
        Resource resource = resourceLoader.getResource(xlsxExpectedDataFile);
        XlsDataSetLoader loader = new XlsDataSetLoader();
        IDataSet dataSet = loader.createDataSet(resource);
        ITable expectedTable = dataSet.getTable(tableName);

        return expectedTable;
    }

    /**
     * xlsxよりmock用のデータを取得する処理</br>
     * [EMPTY_STRING]を空文字に変換処理付き
     * @param xlsxExpectedDataFile 検証用データxlsx
     * @param tableName 検証対象テーブル名
     * @return ITable
     * @throws Exception 例外
     */
    public static ITable getMockDataTable(
                                          String xlsxExpectedDataFile,
                                          String tableName) throws Exception {

        // xlsxから検証用データを取得
        File file = ResourceUtils.getFile(xlsxExpectedDataFile);
        IDataSet dataSet = new XlsDataSet(file);
        ReplacementDataSet replaceData = new ReplacementDataSet(dataSet);
        replaceData.addReplacementObject("[EMPTY_STRING]", "");
        ITable expectedTable = replaceData.getTable(tableName);

        return expectedTable;
    }

    /**
     * xlsxよりデータを仕込んだテーブル名をリストで取得する
     * @param xlsxExpectedDataFile テーブル名
     * @return List
     * @throws Exception 例外
     */
    public static List<String> getSetupTableName(
                                                 String xlsxExpectedDataFile) throws Exception {

        // xlsxから用データを取得
        File file = ResourceUtils.getFile(xlsxExpectedDataFile);
        IDataSet dataSet = new XlsDataSet(file);

        return Arrays.asList(dataSet.getTableNames());
    }

    /**
     * @param masterDataSource データソース
     * @return IDatabaseConnection
     * @throws Exception 例外
     */
    private static IDatabaseConnection getConnection(
                                                     TransactionAwareDataSourceProxy masterDataSource) throws Exception {

        // コネクション取得
        IDatabaseConnection con = new DatabaseConnection(DataSourceUtils.getConnection(masterDataSource));
        DatabaseConfig config = con.getConfig();
        config.setFeature(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);

        return con;
    }
}
