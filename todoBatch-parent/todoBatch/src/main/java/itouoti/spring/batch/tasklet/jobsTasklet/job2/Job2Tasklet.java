package itouoti.spring.batch.tasklet.jobsTasklet.job2;

import javax.inject.Inject;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import itouoti.spring.batch.service.jobsService.job2.Job2Service;

/**
 * tasklet処理 </br>
 * commandLineParams:META-INF/jobs/job2/job2.xml Job2 qualified=0 schema=spring_batch crean=0
 * @author itouoti
 */
@Component
@Scope("step")
public class Job2Tasklet implements Tasklet {

    @Value("#{jobParameters[qualified]}")
    private int qualified;

    @Value("#{jobParameters[schema]}")
    private String schema;

    @Value("#{jobParameters[crean]}")
    private int crean;

    @Inject
    private Job2Service service;

    @Override
    public RepeatStatus execute(
                                StepContribution stepContribution,
                                ChunkContext chunkContext) throws Exception {

        System.out.println("tasklet層起動");

        Job2ParamBean input = new Job2ParamBean();
        input.setSchema(schema);
        input.setCrean(crean);
        input.setQualified(qualified);

        // サービス層処理実行
        service.startService(input);

        return RepeatStatus.FINISHED;
    }

}
