package itouoti.spring.batch.repository.jobsRepository.job4.reader;

import java.util.List;
import java.util.Map;

/**
 * @author job4Reader層インタフェース
 */
public interface Job4ReaderRepository {

    /**
     * customerテーブルからAmountの一覧データを抽出する
     * @param map </br>
     *            storeId...int
     * @return String
     */
    public List<FindCustomerAmountDTO> findCustomerAmount(Map<String, Object> map);

}
