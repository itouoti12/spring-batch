package itouoti.spring.batch.repository.jobsRepository.job1;

import lombok.Getter;
import lombok.Setter;

/**
 * SELECT_STORENAME_001テストパターン格納用オブジェクト
 * @author btitouknzf
 */
public class Job1RepositorySerchFilmDataParam {

    // 試験No
    @Getter
    @Setter
    String no;

    // filmId
    @Getter
    @Setter
    int filmId;

    // 期待値(件数)
    @Getter
    @Setter
    int expectCount;

    // 期待値(title)
    @Getter
    @Setter
    String expectTitle;

    // 期待値(description)
    @Getter
    @Setter
    String expectDescription;

    // 備考
    @Getter
    @Setter
    String memo;
}
