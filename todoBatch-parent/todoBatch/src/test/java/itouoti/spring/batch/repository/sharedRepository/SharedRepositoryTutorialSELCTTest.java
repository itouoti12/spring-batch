package itouoti.spring.batch.repository.sharedRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.List;

import javax.inject.Inject;

import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
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
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import itouoti.spring.batch.repository.commonDTO.CustomerDTO;
import itouoti.spring.batch.testCommon.DBUnitSetUp;
import itouoti.spring.batch.testCommon.TrimTable;
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
 * sharedRepository tutorialSELECT Test
 * @author itouoti
 */
public class SharedRepositoryTutorialSELCTTest {

    @Inject
    SharedRepository target;

    @Inject
    ResourceLoader resourceLoader;

    @Inject
    JdbcTemplate jdbcTemplate;

    @Inject
    TransactionAwareDataSourceProxy masterDataSource;

    @Value("${xlsx.expectedDataFile}")
    String xlsxExpectedDataFile;

    // エクセルファイルパス
    private static String xlsxPath = "classpath:itouoti/spring/batch/repository/sharedRepository/tutorialSELECT/";

    /**
     * テストメソッド起動前設定
     * @throws Exception 例外
     */
    @BeforeClass
    public static void setUpBeforeTest() throws Exception {

        log.info("□tutorialSELECT test is start");

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
        log.info("□tutorialSELECT test is end");
    }

    /**
     * SharedRepository.tutorialSELECTテスト
     * @throws Exception DBUnitのxlsx読み込み例外
     */
    @Test
    @DatabaseSetups({
            @DatabaseSetup("classpath:itouoti/spring/batch/repository/sharedRepository/tutorialSELECT/payment_setUp_data.xlsx"),
            @DatabaseSetup("classpath:itouoti/spring/batch/repository/sharedRepository/tutorialSELECT/rental_setUp_data.xlsx"),
            @DatabaseSetup("classpath:itouoti/spring/batch/repository/sharedRepository/tutorialSELECT/customer_setUp_data.xlsx") })
    public void tutorialSELECTTest() throws Exception {

        // STEP1 メソッドのテスト
        List<CustomerDTO> result = target.tutorialSELECT();

        // STEP2 取得件数の検証
        assertThat(result.size(), is(9));

        // STEP3 テーブル状態の検証
        ITable actual1 = DBUnitSetUp.getAtualDataTable(masterDataSource, "public.customer");
        ITable expected1 = DBUnitSetUp.getExpectedDataTable(resourceLoader, xlsxPath + xlsxExpectedDataFile, "customer");

        org.dbunit.Assertion.assertEquals(new SortedTable(new TrimTable(expected1)), new SortedTable(new TrimTable(actual1), expected1
                .getTableMetaData()));

    }

}
