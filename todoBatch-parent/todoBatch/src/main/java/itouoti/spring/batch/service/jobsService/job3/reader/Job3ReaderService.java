package itouoti.spring.batch.service.jobsService.job3.reader;

import java.util.List;

import itouoti.spring.batch.chunk.jobsChunk.job3.reader.Job3ParamBean;
import itouoti.spring.batch.repository.commonDTO.CustomerDTO;

/**
 * サービス層インタフェース
 * @author itouoti
 */
public interface Job3ReaderService {

    /**
     * サービス層メインメソッド
     * @return job3ReaderDTO
     */
    public List<CustomerDTO> job3Service(Job3ParamBean input);

}
