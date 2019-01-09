package itouoti.spring.batch.repository.jobsRepository.job3.reader;

import java.util.List;
import java.util.Map;

import itouoti.spring.batch.repository.commonDTO.CustomerDTO;

/**
 * @author job3Reader層インタフェース
 */
public interface Job3ReaderRepository {

    /**
     * customerテーブルからactiveでないデータを抽出する
     * @param map </br>
     *            storeId...int
     * @return String
     */
    public List<CustomerDTO> findDeadCustomor(Map<String, Object> map);

}
