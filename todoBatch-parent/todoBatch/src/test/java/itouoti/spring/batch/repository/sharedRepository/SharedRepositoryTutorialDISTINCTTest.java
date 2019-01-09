package itouoti.spring.batch.repository.sharedRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.List;

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

import itouoti.spring.batch.repository.commonDTO.T1DTO;
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
@Slf4j
/**
 * sharedRepository tutorialDISTINCT Test
 * @author itouoti
 */
public class SharedRepositoryTutorialDISTINCTTest {

    @Inject
    SharedRepository target;

    @Inject
    ResourceLoader resourceLoader;

    @Inject
    JdbcTemplate jdbcTemplate;

    @Inject
    TransactionAwareDataSourceProxy masterDataSource;

    /**
     * テストメソッド起動前設定
     * @throws Exception 例外
     */
    @BeforeClass
    public static void setUpBeforeTest() throws Exception {

        log.info("□tutorialDISTINCT test is start");

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
        log.info("□tutorialDISTINCT test is end");
    }

    /**
     * SharedRepository.tutorialDISTINCTテスト
     * @throws Exception DBUnitのxlsx読み込み例外
     */
    @Test
    @DatabaseSetup("classpath:itouoti/spring/batch/repository/sharedRepository/tutorialDISTINCT/t1_setUp_data.xlsx")
    @ExpectedDatabase(value = "classpath:itouoti/spring/batch/repository/sharedRepository/tutorialDISTINCT/expected_data.xlsx",
                      assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void tutorialDISTINCTTest() throws Exception {

        // STEP1 メソッドのテスト
        List<T1DTO> result = target.tutorialDISTINCT();

        // STEP2 取得件数の検証
        assertThat(result.size(), is(11));

        // STEP3 テーブル状態の検証
        // @ExpectedDatabaseで実施

    }

}
