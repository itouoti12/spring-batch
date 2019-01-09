package itouoti.spring.batch.chunk.jobsChunk.job4.writer;

import java.util.List;

import javax.inject.Inject;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import itouoti.spring.batch.service.jobsService.job4.writer.Job4WriterService;

/**
 * chunkモデルの上位レイヤー
 * @author itouoti
 */
@Component("writer")
public class Job4WriterChunk implements ItemWriter<Job4WriterOBJ> {

    @Inject
    Job4WriterService service;

    @Override
    public void write(List<? extends Job4WriterOBJ> obj) throws Exception {
        
        service.startService(obj);
    }

}
