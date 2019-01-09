package itouoti.spring.batch.chunk.jobsChunk.job3.reader;

import java.util.List;

import javax.inject.Inject;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import itouoti.spring.batch.repository.commonDTO.CustomerDTO;
import itouoti.spring.batch.service.jobsService.job3.reader.Job3ReaderService;

@Scope("step")
public class Job3ReaderChunk implements ItemReader<CustomerDTO> {

    @Value("#{jobParameters[storeId]}")
    public int storeId;

    @Inject
    private Job3ReaderService service;

    private List<CustomerDTO> dto;

    private int listCount = 0;

    @Override
    public CustomerDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        Job3ParamBean input = new Job3ParamBean();
        input.setStoreId(storeId);
        
        dto = service.job3Service(input);

        if (listCount < dto.size()) {
            return dto.get(listCount++);
        } else {
            return null;
        }

    }

}
