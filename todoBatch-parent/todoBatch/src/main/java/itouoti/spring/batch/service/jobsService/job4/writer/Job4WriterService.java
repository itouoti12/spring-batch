package itouoti.spring.batch.service.jobsService.job4.writer;

import java.util.List;

import itouoti.spring.batch.chunk.jobsChunk.job4.writer.Job4WriterOBJ;

/**
 * サービス層インタフェース
 * @author itouoti
 */
public interface Job4WriterService {

    /**
     * サービス層メインメソッド
     */
    public void startService(
                             List<? extends Job4WriterOBJ> obj);

}
