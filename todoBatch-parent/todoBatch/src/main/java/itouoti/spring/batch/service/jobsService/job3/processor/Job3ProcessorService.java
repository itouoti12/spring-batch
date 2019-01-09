package itouoti.spring.batch.service.jobsService.job3.processor;

import itouoti.spring.batch.chunk.jobsChunk.job3.writer.Job3WriterOBJ;
import itouoti.spring.batch.repository.commonDTO.CustomerDTO;

/**
 * サービス層インタフェース
 * @author itouoti
 */
public interface Job3ProcessorService {

    /**
     * サービス層メインメソッド
     * @return job3ReaderDTO
     */
    public Job3WriterOBJ job3Service(CustomerDTO input);

}
