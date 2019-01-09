package itouoti.spring.batch.service.jobsService.job3.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import itouoti.spring.batch.chunk.jobsChunk.job3.reader.Job3ParamBean;
import itouoti.spring.batch.repository.commonDTO.CustomerDTO;
import itouoti.spring.batch.repository.jobsRepository.job3.reader.Job3ReaderRepository;

/**
 * Service層実装クラス
 * @author itouoti
 */
@Service
public class Job3ReaderServiceImpl implements Job3ReaderService {

    @Inject
    Job3ReaderRepository repository;

    /**
     * Service層主処理
     * @param bean
     */
    @Override
    public List<CustomerDTO> job3Service(Job3ParamBean input) {

        Map<String, Object> map = new HashMap<>();
        map.put("storeId", input.getStoreId());

        List<CustomerDTO> dtoList = repository.findDeadCustomor(map);

        return dtoList;
    }

}
