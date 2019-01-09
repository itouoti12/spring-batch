package itouoti.spring.batch.repository.sharedRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import itouoti.spring.batch.repository.commonDTO.FilmDTO;
import itouoti.spring.batch.testCommon.ExecSQL;
import itouoti.spring.batch.testCommon.LoadProperties;
import itouoti.spring.batch.testCommon.XlsDataSetLoader;
import lombok.extern.slf4j.Slf4j;

/**
 * Theoriesテスト
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/jobs/sharedReposiotry/sharedRepositoryTest.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@Transactional
@DbUnitConfiguration(dataSetLoader = XlsDataSetLoader.class)
@DatabaseSetup(value = "classpath:itouoti/spring/batch/repository/sharedRepository/tutorialLIMIT/film_setUp_data.xlsx")
@ExpectedDatabase(value = "classpath:itouoti/spring/batch/repository/sharedRepository/tutorialLIMIT/expected_data.xlsx",
                  assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
@Slf4j
/**
 * sharedRepository TutorialLIMIT Test
 * @author itouoti
 */
public class SharedRepositoryTutorialLIMITTest {

    @Inject
    SharedRepository target;

    @Inject
    ResourceLoader resourceLoader;

    @Inject
    JdbcTemplate jdbcTemplate;

    @Inject
    TransactionAwareDataSourceProxy masterDataSource;

    private static String jdbcProps = "classpath:batch-application.properties";

    private static Properties props;

    /**
     * テストメソッド起動前設定
     * @throws Exception 例外
     */
    @BeforeClass
    public static void setUpBeforeTest() throws Exception {

        log.info("□TutorialLIMIT test is start");

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
        log.info("□TutorialLIMIT test is end");

        // 外部キー制約の設定
        String disableAlter = "ALTER TABLE film ENABLE TRIGGER ALL";

        ExecSQL.execSQLExecSQL(props, disableAlter, false);

    }

    /**
     * SharedRepository.TutorialLIMITテスト
     * @throws Exception DBUnitのxlsx読み込み例外
     */
    @Test
    public void TutorialLIMIT外側対象テスト() throws Exception {

        // STEP1 メソッドのテスト
        Map<String, Object> map = new HashMap<>();
        map.put("limit", 2);
        map.put("offset", 7);
        List<FilmDTO> result = target.tutorialLIMIT(map);

        // STEP2 取得件数の検証
        assertThat(result.size(), is(2));

        // STEP3 テーブル状態の検証
        // @ExpectedDatabaseで実施
    }

}
