package itouoti.spring.batch.service.jobsService.job1;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import itouoti.spring.batch.repository.commonDTO.FilmDTO;
import itouoti.spring.batch.repository.jobsRepository.job1.Job1Repository;
import itouoti.spring.batch.tasklet.jobsTasklet.job1.Job1ParamBean;

/**
 * Service層実装クラス
 * @author itouoti
 */
@Service
public class Job1ServiceImpl implements Job1Service {

    @Inject
    private Job1Repository repository;

    @Value("${job1.param1}")
    private String porpertiesParam1;

    @Value("${job2.param1}")
    private String porpertiesParam2;

    /**
     * Service層主処理
     * @param bean
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void startService(
                             Job1ParamBean bean) {

        System.out.println("service層起動");
        System.out.println(porpertiesParam1);

        System.out.println("repository層起動");
        Map<String, Object> map = new HashMap<>();
        map.put("filmId", Integer.parseInt(bean.getFilmId()));

        FilmDTO filmDto = repository.sarchfilmData(map);
        System.out.println("filmTitle is " + filmDto.getTitle());

    }

}
