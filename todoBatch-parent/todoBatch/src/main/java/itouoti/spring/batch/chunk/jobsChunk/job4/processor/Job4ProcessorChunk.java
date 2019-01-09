package itouoti.spring.batch.chunk.jobsChunk.job4.processor;

import javax.inject.Inject;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import itouoti.spring.batch.chunk.jobsChunk.job4.writer.Job4WriterOBJ;
import itouoti.spring.batch.repository.jobsRepository.job4.reader.FindCustomerAmountDTO;
import itouoti.spring.batch.service.jobsService.job4.processor.Job4ProcessorService;

/**
 * chunkモデルの上位レイヤー
 * @author itouoti
 */
@Component("processor")
public class Job4ProcessorChunk implements ItemProcessor<FindCustomerAmountDTO, Job4WriterOBJ> {

    @Inject
    Job4ProcessorService service;

    @Override
    public Job4WriterOBJ process(FindCustomerAmountDTO dto) throws Exception {
        // TODO 自動生成されたメソッド・スタブ
        return service.startService(dto);
    }

}
