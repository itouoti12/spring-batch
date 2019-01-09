package itouoti.spring.batch.tasklet.jobsTasklet.job1;

import javax.inject.Inject;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import itouoti.spring.batch.service.jobsService.job1.Job1Service;

/**
 * tasklet処理 </br>
 * commandLineParams:META-INF/jobs/job1/job1.xml Job1 filmId=1
 * @author itouoti
 */
@Component
@Scope("step")
public class Job1Tasklet implements Tasklet {

    public Job1ParamBean bean;

    @Value("#{jobParameters[filmId]}")
    private String filmId;

    @Inject
    private Job1Service service;

    @Override
    public RepeatStatus execute(
                                StepContribution stepContribution,
                                ChunkContext chunkContext) throws Exception {

        System.out.println("tasklet層起動");

        // パラメータをbeanにセット
        bean = new Job1ParamBean();
        bean.setFilmId(filmId);

        // サービス層処理実行
        service.startService(bean);

        return RepeatStatus.FINISHED;
    }

}
