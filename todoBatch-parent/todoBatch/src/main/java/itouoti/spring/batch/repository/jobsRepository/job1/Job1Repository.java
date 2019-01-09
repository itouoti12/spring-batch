package itouoti.spring.batch.repository.jobsRepository.job1;

import java.util.Map;

import itouoti.spring.batch.repository.commonDTO.FilmDTO;

/**
 * @author job1Repository層インタフェース
 */
public interface Job1Repository {

    /**
     * filmテーブルから情報を取得する
     * @param map </br>
     *            filmId...int
     * @return FilmDTO
     */
    public FilmDTO sarchfilmData(
                                 Map<String, Object> map);

}
