package itouoti.spring.batch.service.jobsService.job2;

import java.io.File;
import java.util.Arrays;

import javax.inject.Inject;

import org.apache.commons.dbcp2.BasicDataSource;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import itouoti.spring.batch.tasklet.jobsTasklet.job2.Job2ParamBean;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;

/**
 * Service層実装クラス
 * @author itouoti
 */
@Service
public class Job2ServiceImpl implements Job2Service {

    @Inject
    private BasicDataSource jobDataSource;

    @Value("${job2.setUp.xlsx.dir}")
    private String xlsxDir;

    /**
     * Service層主処理
     * @param input 起動パラメータ
     * @throws Exception 例外
     */
    public void startService(Job2ParamBean input) throws Exception {

        System.out.println("service層起動");

        // コネクション取得
        Log4jdbcProxyDataSource log4jDataSource = new Log4jdbcProxyDataSource(jobDataSource);

        IDatabaseConnection con;
        DatabaseConfig config;

        if (input.getQualified() == 0) {
            // 単一スキーマを引数で指定して用いる場合
            con = new DatabaseConnection(DataSourceUtils.getConnection(log4jDataSource), input.getSchema());
            config = con.getConfig();
            config.setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
        } else {
            // 複数スキーマを用いる場合
            // エクセルのシートに「スキーマ.テーブル名」でデータを指定
            con = new DatabaseConnection(DataSourceUtils.getConnection(log4jDataSource));
            config = con.getConfig();
            config.setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
            config.setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
        }

        // ディレクトリの中のエクセルを一覧取得
        File dir = ResourceUtils.getFile("classpath:" + xlsxDir);
        File[] list = dir.listFiles();
        System.out.println("□ 以下のファイルが見つかりました");
        Arrays.stream(list).forEach(file -> System.out.println(file.toString()));

        try {
            if (list != null) {

                for (int i = 0; i < list.length; i++) {

                    File file = list[i];

                    if (file.isFile() && file.canRead() && file.getPath().endsWith(".xlsx")) {

                        System.out.println("□ 以下のファイルに対して処理をします");
                        System.out.println(file.toString());

                        // xlsxから検証用データを取得
                        IDataSet dataSet = new XlsDataSet(file);
                        // 空文字の変換
                        ReplacementDataSet replaceData = new ReplacementDataSet(dataSet);
                        replaceData.addReplacementObject("[EMPTY_STRING]", "");

                        if (input.getCrean() == 0) {
                            /* if (i == 0) { */
                            // データのクリーンインサート
                            DatabaseOperation.CLEAN_INSERT.execute(con, replaceData);
                            /*
                             * } else { // データのインサート DatabaseOperation.INSERT.execute(con, replaceData); }
                             */
                        } else {
                            // データのインサート
                            DatabaseOperation.INSERT.execute(con, replaceData);
                        }

                    }
                }
            }
        } catch (Exception e) {

            // ロールバック
            con.getConnection().rollback();
            throw new Exception(e);
        }

        // コミット
        con.getConnection().commit();

    }

}
