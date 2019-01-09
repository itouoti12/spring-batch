package itouoti.spring.batch.testCommon;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;

/**
 * DBを操作するクラス
 * @author itouoti
 */
public class ExecSQL {

    /**
     * SQLを実行する
     * @param props propertiesファイル
     * @param sql 実行SQL文
     * @param isSELECT 参照系のSQLか？
     * @throws Exception 例外
     */
    public static void execSQLExecSQL(Properties props,
                                      String sql,
                                      boolean isSELECT) throws Exception {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(props.getProperty("jdbc.driver"));
        dataSource.setUrl(props.getProperty("jdbc.url"));
        dataSource.setUsername(props.getProperty("jdbc.username"));
        dataSource.setPassword(props.getProperty("jdbc.password"));
        dataSource.setDefaultAutoCommit(false);
        Log4jdbcProxyDataSource dsLog4j = new Log4jdbcProxyDataSource(dataSource);
        Connection con = dsLog4j.getConnection();

        try (Statement stmt = con.createStatement();) {
            if (isSELECT) {
                stmt.executeQuery(sql);
            } else {
                stmt.executeUpdate(sql);
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
        }

        dataSource.close();

    }
}
