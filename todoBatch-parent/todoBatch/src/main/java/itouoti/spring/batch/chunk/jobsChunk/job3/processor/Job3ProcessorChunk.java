package itouoti.spring.batch.chunk.jobsChunk.job3.processor;

import javax.inject.Inject;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import itouoti.spring.batch.chunk.jobsChunk.job3.writer.Job3WriterOBJ;
import itouoti.spring.batch.repository.commonDTO.CustomerDTO;
import itouoti.spring.batch.service.jobsService.job3.processor.Job3ProcessorService;

/**
 * Job3 processor
 * @author itouoti
 */
@Component("processor")
public class Job3ProcessorChunk implements ItemProcessor<CustomerDTO, Job3WriterOBJ> {

    @Inject
    private Job3ProcessorService service;

    @Override
    public Job3WriterOBJ process(CustomerDTO item) throws Exception {

        Job3WriterOBJ writerObj = service.job3Service(item);
        return writerObj;
    }

}
