package itouoti.spring.batch.repository.sharedRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import itouoti.spring.batch.repository.commonDTO.PaymentDTO;
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
@DatabaseSetup("classpath:itouoti/spring/batch/repository/sharedRepository/tutorialWHEREType2/payment_setUp_data.xlsx")
@ExpectedDatabase(value = "classpath:itouoti/spring/batch/repository/sharedRepository/tutorialWHEREType2/expected_data.xlsx",
                  assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
@Slf4j
/**
 * sharedRepository TutorialWHEREType2 Test
 * @author itouoti
 */
public class SharedRepositoryTutorialWHEREType2Test {

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

        log.info("□TutorialWHEREType2 test is start");

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
        log.info("□TutorialWHEREType2 test is end");
    }

    /**
     * SharedRepository.TutorialWHEREType2テスト </br>
     * x &lt= 2 , 7 &lt= x
     * @throws Exception DBUnitのxlsx読み込み例外
     */
    @Test
    public void TutorialWHEREType2外側対象テスト() throws Exception {

        // STEP1 メソッドのテスト
        Map<String, Object> map = new HashMap<>();
        map.put("upperAmount", new BigDecimal(2));
        map.put("lowerAmount", new BigDecimal(7));
        List<PaymentDTO> result = target.tutorialWHEREType1(map);

        // STEP2 取得件数の検証
        assertThat(result.size(), is(4));

        // STEP3 テーブル状態の検証
        // @ExpectedDatabaseで実施
    }

    /**
     * SharedRepository.TutorialWHEREType2テスト </br>
     * 2 &lt= x or x &lt= 7 (つまり全部対象)
     * @throws Exception DBUnitのxlsx読み込み例外
     */
    @Test
    public void TutorialWHEREType2内側対象テスト() throws Exception {

        // STEP1 メソッドのテスト
        Map<String, Object> map = new HashMap<>();
        map.put("upperAmount", new BigDecimal(7));
        map.put("lowerAmount", new BigDecimal(2));
        List<PaymentDTO> result = target.tutorialWHEREType1(map);

        // STEP2 取得件数の検証
        assertThat(result.size(), is(9));

        // STEP3 テーブル状態の検証
        // @ExpectedDatabaseで実施
    }

}
