package itouoti.spring.batch.service.jobsService.job4.writer;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import itouoti.spring.batch.chunk.jobsChunk.job4.writer.Job4WriterOBJ;
import itouoti.spring.batch.service.sharedService.SharedService;

/**
 * Service層実装クラス
 * @author itouoti
 */
@Service
@Scope("step")
public class Job4WriterServiceImpl implements Job4WriterService {

    @Inject
    private SharedService sharedService;

    @Value("${job4.param1}")
    private String porpertiesParam1;

    @Value("${job4.param1}")
    private String porpertiesParam2;

    private int count = 1;

    /**
     * Service層主処理
     * @param bean
     */
    @Override
    public void startService(List<? extends Job4WriterOBJ> list) {

        String[] count = { String.valueOf(this.count) };
        sharedService.outputInfoMsgToLog("job4.message2", count);

        for (Job4WriterOBJ obj : list) {
            String[] strlist = new String[3];
            // customerId
            strlist[0] = String.valueOf(obj.getCustomerId());
            // name
            strlist[1] = obj.getFullName();
            // amount
            strlist[2] = sharedService.longToConma(obj.getAmount());

            sharedService.outputInfoMsgToLog("job4.message1", strlist);
        }

        this.count++;

    }

}
