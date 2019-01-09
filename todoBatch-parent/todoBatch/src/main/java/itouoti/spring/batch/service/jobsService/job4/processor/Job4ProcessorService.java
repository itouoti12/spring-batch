package itouoti.spring.batch.service.jobsService.job4.processor;

import itouoti.spring.batch.chunk.jobsChunk.job4.writer.Job4WriterOBJ;
import itouoti.spring.batch.repository.jobsRepository.job4.reader.FindCustomerAmountDTO;

/**
 * サービス層インタフェース
 * @author itouoti
 */
public interface Job4ProcessorService {

    /**
     * サービス層メインメソッド
     */
    public Job4WriterOBJ startService(
                                      FindCustomerAmountDTO dto);

}
