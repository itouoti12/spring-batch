package itouoti.spring.batch.service.jobsService.job3.processor;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import itouoti.spring.batch.chunk.jobsChunk.job3.writer.Job3WriterOBJ;
import itouoti.spring.batch.repository.commonDTO.CustomerDTO;
import itouoti.spring.batch.repository.jobsRepository.job3.reader.Job3ReaderRepository;

/**
 * Service層実装クラス
 * @author itouoti
 */
@Service
public class Job3ProcessorServiceImpl implements Job3ProcessorService {

    @Inject
    Job3ReaderRepository repository;

    /**
     * Service層主処理
     * @param bean
     */
    @Override
    public Job3WriterOBJ job3Service(CustomerDTO input) {

        Job3WriterOBJ writerObj = new Job3WriterOBJ();

        writerObj.setCustomerId(input.getCustomerId());
        writerObj.setName(input.getFirstName() + "_" + input.getLastName());
        writerObj.setEmail(input.getEmail());
        writerObj.setAddressId(input.getAddressId());
        return writerObj;
    }

}
