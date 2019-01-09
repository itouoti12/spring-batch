package itouoti.spring.batch.repository.jobsRepository.job1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.excel.XlsDataSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import itouoti.spring.batch.repository.commonDTO.CustomerDTO;
import itouoti.spring.batch.repository.commonDTO.FilmDTO;
import itouoti.spring.batch.testCommon.DBUnitSetUp;
import itouoti.spring.batch.testCommon.ExecSQL;
import itouoti.spring.batch.testCommon.LoadProperties;
import itouoti.spring.batch.testCommon.TestHelper;
import itouoti.spring.batch.testCommon.TrimTable;
import itouoti.spring.batch.testCommon.XlsDataSetLoader;
import lombok.extern.slf4j.Slf4j;

/**
 * Theoriesテスト
 */
@RunWith(Theories.class)
@ContextConfiguration(locations = { "classpath:META-INF/jobs/job1/job1Test.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@Transactional
@DbUnitConfiguration(dataSetLoader = XlsDataSetLoader.class)
@Slf4j
/**
 * job1Repository sarchfilmData Test
 * @author itouoti
 */
public class Job1RepositorySerchFilmDataTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Inject
    Job1Repository target;

    @Inject
    ResourceLoader resourceLoader;

    @Inject
    JdbcTemplate jdbcTemplate;

    @Inject
    TransactionAwareDataSourceProxy masterDataSource;

    @Value("${xlsx.expectedDataFile}")
    String xlsxExpectedDataFile;

    // エクセルファイルパス
    private static String xlsxPath = "classpath:itouoti/spring/batch/repository/jobsRepository/job1/";

    // テストパターン用エクセルオブジェクト
    private static IDataSet xlsDataSet;

    // assertエラー格納用List
    private static List<AssertionError> assertionErrors = new ArrayList<AssertionError>();

    private static String jdbcProps = "classpath:batch-application.properties";

    private static Properties props;

    /**
     * テストメソッド起動前設定
     * @throws Exception 例外
     */
    @BeforeClass
    public static void setUpBeforeTest() throws Exception {

        log.info("□sarchfilmData test is start");

        // xlsxファイルの読み込み
        File file = ResourceUtils.getFile(xlsxPath + "testList.xlsx");
        xlsDataSet = new XlsDataSet(file);

        // propertiesファイルの読み込み
        props = LoadProperties.load(jdbcProps);

        // 外部キー制約の解除
        String disableAlter = "ALTER TABLE film DISABLE TRIGGER ALL";

        ExecSQL.execSQLExecSQL(props, disableAlter, false);
    }

    /**
     * テスト毎起動前設定
     * @throws Exception 例外
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * テスト毎検証後処理
     */
    @After
    public void cleanUp() {
        // テスト後の後処理があれば記述
    }

    /**
     * テストメソッド実行後処理
     * @throws Exception 例外
     */
    @AfterClass
    public static void creanUpAfterTest() throws Exception {
        // テスト後の後処理があれば記述
        if (assertionErrors.size() > 0) {
            for (AssertionError e : assertionErrors) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
            log.error(assertionErrors.size() + "件のAssertionErrorが発生しています");
            fail(assertionErrors.size() + "件のAssertionErrorが発生しています");
        }

        log.info("□sarchfilmData test is end");

        // 外部キー制約の設定
        String disableAlter = "ALTER TABLE film ENABLE TRIGGER ALL";

        ExecSQL.execSQLExecSQL(props, disableAlter, false);
    }

    /**
     * job1Repository.sarchfilmDataテスト
     * @throws Exception DBUnitのxlsx読み込み例外
     */
    @Theory
    @DatabaseSetup("classpath:itouoti/spring/batch/repository/jobsRepository/job1/setUp_data.xlsx")
    public void sarchfilmDataTest(
                                  Job1RepositorySerchFilmDataParam testParam) throws Exception {

        log.info("■ TestNo." + testParam.no + " sarchfilmData test is start");

        try {

            // STEP1 メソッドのテスト
            Map<String, Object> map = new HashMap<>();
            map.put("filmId", testParam.getFilmId());
            FilmDTO result = target.sarchfilmData(map);

            // STEP2 取得件数の検証
            assertThat(result.getTitle(), is(testParam.getExpectTitle()));
            assertThat(result.getDescription(), is(testParam.getExpectDescription()));

            // STEP3 テーブル状態の検証
            ITable actual1 = DBUnitSetUp.getAtualDataTable(masterDataSource, "public.film");
            ITable expected1 = DBUnitSetUp.getExpectedDataTable(resourceLoader, xlsxPath + xlsxExpectedDataFile, "film");

            org.dbunit.Assertion.assertEquals(new SortedTable(new TrimTable(expected1)), new SortedTable(new TrimTable(actual1), expected1
                    .getTableMetaData()));

        } catch (AssertionError e) {
            // AssertionErrorsに貯める
            log.error(testParam.no + "Memo = " + testParam.getMemo());
            assertionErrors.add(e);
        } catch (Exception e) {
            // AssertionErrorsに貯める
            log.error(e.getMessage());
            assertionErrors.add(new AssertionError(e));
        } finally {
            log.info("■ TestNo." + testParam.no + " sarchfilmData test is end");
        }
    }

    /**
     * job1Repository.sarchfilmDataテスト用パラメータ生成
     * @return sarchfilmData[]
     * @throws Exception 例外
     */
    @DataPoints
    public static Job1RepositorySerchFilmDataParam[] inputTestParams() throws Exception {

        // テスト用シート名
        String testParamSheet = "param";

        ArrayList<Job1RepositorySerchFilmDataParam> paramList = new ArrayList<Job1RepositorySerchFilmDataParam>();

        try {
            ITable paramTable = xlsDataSet.getTable(testParamSheet);

            for (int i = 0; i < paramTable.getRowCount(); i++) {
                Job1RepositorySerchFilmDataParam param = new Job1RepositorySerchFilmDataParam();

                // 試験No
                param.setNo(TestHelper.toStr(paramTable, i, "no"));

                // filmId
                param.setFilmId(TestHelper.toInt(paramTable, i, "filmId"));

                // 期待値(件数)
                param.setExpectCount(TestHelper.toInt(paramTable, i, "expectCount"));

                // 期待値(title)
                param.setExpectTitle(TestHelper.toStr(paramTable, i, "expectTitle"));

                // 期待値(description)
                param.setExpectDescription(TestHelper.toStr(paramTable, i, "expectDescription"));

                // 備考
                param.setMemo(TestHelper.toStr(paramTable, i, "memo"));

                paramList.add(param);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramList.toArray(new Job1RepositorySerchFilmDataParam[paramList.size()]);
    }

}
