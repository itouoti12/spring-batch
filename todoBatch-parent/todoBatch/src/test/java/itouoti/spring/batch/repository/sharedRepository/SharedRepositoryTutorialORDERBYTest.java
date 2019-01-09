package itouoti.spring.batch.repository.sharedRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.bind.DefaultBinderConfiguration;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.operation.Operation;

import itouoti.spring.batch.repository.commonDTO.CustomerDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * Theoriesテスト
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/jobs/sharedReposiotry/sharedRepositoryTest.xml" })
@Transactional
@Slf4j
/**
 * sharedRepository tutorialORDERBY Test
 * @author itouoti
 */
public class SharedRepositoryTutorialORDERBYTest {

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

        log.info("□tutorialORDERBY test is start");

    }

    /**
     * テスト毎起動前設定
     * @throws Exception 例外
     */
    @Before
    public void setUp() throws Exception {

        Date createDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2006-02-14").getTime());

        Timestamp lastUpdate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-05-26 14:49:45").getTime());

        Destination dest = new DataSourceDestination(masterDataSource);
        Operation operation = Operations.sequenceOf(
                // 制約無効化
                Operations.sql("ALTER TABLE customer DISABLE TRIGGER ALL"), Operations.deleteAllFrom("customer"), Operations
                        .insertInto("customer")

                        // 共通データ部分
                        .withDefaultValue("activebool", true)
                        .withDefaultValue("create_date", createDate)
                        .withDefaultValue("last_update", lastUpdate)
                        .withDefaultValue("active", 1)

                        // 列定義
                        .columns("customer_id", "store_id", "first_name", "last_name", "email", "address_id")
                        // レコード情報
                        .values(524, (short) 1, "Jared", "Smith", "jared.ely@sakilacustomer.org", (short) 530)
                        .values(1, (short) 1, "Mary", "Johnson", "mary.smith@sakilacustomer.org", (short) 5)
                        .values(2, (short) 1, "Patricia", "Williams", "patricia.johnson@sakilacustomer.org", (short) 6)
                        .values(3, (short) 1, "Patricia", "Jones", "linda.williams@sakilacustomer.org", (short) 7)
                        .values(4, (short) 2, "Barbara", "Brown", "barbara.jones@sakilacustomer.org", (short) 8)
                        .build());

        Connection connection = dest.getConnection();
        connection.setAutoCommit(false);
        operation.execute(connection, DefaultBinderConfiguration.INSTANCE);

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

        log.info("□tutorialORDERBY test is end");
    }

    /**
     * SharedRepository.tutorialSELECTテスト
     * @throws Exception DBUnitのxlsx読み込み例外
     */
    @Test
    public void tutorialSELECTTest() throws Exception {

        // STEP1 メソッドのテスト
        List<CustomerDTO> result = target.tutorialORDERBY();

        // STEP2 取得件数の検証
        assertThat(result.size()).isEqualTo(5);
        assertThat(result.get(0).getFirstName()).isEqualTo("Barbara");
        assertThat(result.get(0).getLastName()).isEqualTo("Brown");
        assertThat(result.get(1).getFirstName()).isEqualTo("Jared");
        assertThat(result.get(1).getLastName()).isEqualTo("Smith");
        assertThat(result.get(2).getFirstName()).isEqualTo("Mary");
        assertThat(result.get(2).getLastName()).isEqualTo("Johnson");
        assertThat(result.get(3).getFirstName()).isEqualTo("Patricia");
        assertThat(result.get(3).getLastName()).isEqualTo("Williams");
        assertThat(result.get(4).getFirstName()).isEqualTo("Patricia");
        assertThat(result.get(4).getLastName()).isEqualTo("Jones");

        // STEP3 テーブル状態の検証

    }

}
