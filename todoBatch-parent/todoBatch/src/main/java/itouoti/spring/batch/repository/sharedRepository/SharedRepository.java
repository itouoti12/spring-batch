package itouoti.spring.batch.repository.sharedRepository;

import java.util.List;
import java.util.Map;

import itouoti.spring.batch.repository.commonDTO.CustomerDTO;
import itouoti.spring.batch.repository.commonDTO.FilmDTO;
import itouoti.spring.batch.repository.commonDTO.PaymentDTO;
import itouoti.spring.batch.repository.commonDTO.RentalDTO;
import itouoti.spring.batch.repository.commonDTO.T1DTO;

/**
 * repository層共通メソッド部分インタフェース
 * @author itouoti
 */
public interface SharedRepository {

    /**
     * customerテーブルからデータを取得する
     * @return CustomerDTO </br>
     *         firstName</br>
     *         lastName</br>
     *         email
     */
    public List<CustomerDTO> tutorialSELECT();

    /**
     * customerテーブルからデータを取得する
     * @return CustomerDTO </br>
     *         firstName</br>
     *         lastName
     */
    public List<CustomerDTO> tutorialORDERBY();

    /**
     * t1テーブルからデータを取得する
     * @return t1DTO </br>
     *         bcolor </br>
     *         fcolor
     */
    public List<T1DTO> tutorialDISTINCT();

    /**
     * paymentテーブルよりデータを取得する。
     * @param map </br>
     *            upperAmount...BigDecimal </br>
     *            lowerAmount...BigDecimal
     * @return PaymentDTO </br>
     *         customerId </br>
     *         amount </br>
     *         paymentDate
     */
    public List<PaymentDTO> tutorialWHEREType1(
                                               Map<String, Object> map);

    /**
     * paymentテーブルよりデータを取得する。
     * @param map </br>
     *            upperAmount...BigDecimal </br>
     *            lowerAmount...BigDecimal
     * @return PaymentDTO </br>
     *         customerId </br>
     *         amount </br>
     *         paymentDate
     */
    public List<PaymentDTO> tutorialWHEREType2(
                                               Map<String, Object> map);

    /**
     * filmテーブルよりデータを取得する。
     * @param map </br>
     *            limit...int </br>
     *            offset...int
     * @return FilmDTO </br>
     *         filmId </br>
     *         title </br>
     *         releaseYear
     */
    public List<FilmDTO> tutorialLIMIT(
                                       Map<String, Object> map);

    /**
     * filmテーブルよりデータを取得する。
     * @param map </br>
     *            offset...int </br>
     *            fetchSize...int
     * @return FilmDTO </br>
     *         filmId </br>
     *         title
     */
    public List<FilmDTO> tutorialFETCH(
                                       Map<String, Object> map);

    /**
     * rentalテーブルよりデータを取得する。
     * @param map </br>
     *            id...List&ltint&gt
     * @return RentalDTO </br>
     *         customerId </br>
     *         rentalId </br>
     *         returnDate
     */
    public List<RentalDTO> tutorialIN(
                                      Map<String, Object> map);

    /**
     * customerテーブルよりデータを取得する。
     * @param map </br>
     *            returnDate...Date
     * @return CustomerDTO </br>
     *         firstName </br>
     *         lastName
     */
    public List<CustomerDTO> tutorialSUBQUERYIN(
                                                Map<String, Object> map);

    /**
     * paymentテーブルよりデータを取得する。
     * @param map </br>
     *            startBetween...BigDecimal </br>
     *            endBetween...BigDecimal
     * @return PaymentDTO </br>
     *         customerId </br>
     *         paymentId </br>
     *         amount
     */
    public List<PaymentDTO> tutorialBETWEEN(
                                            Map<String, Object> map);

    /**
     * customerテーブルよりデータを取得する。
     * @param map </br>
     *            likeKey...String
     * @return CustomerDTO </br>
     *         firstName </br>
     *         lastName
     */
    public List<CustomerDTO> tutorialLIKE(
                                          Map<String, Object> map);

    /**
     * linkテーブルにデータを挿入する。
     * @param map </br>
     *            url...String </br>
     *            name...String
     * @return int insertCount
     */
    public int tutorialINSERTType1(
                                   Map<String, Object> map);

    /**
     * linkテーブルにデータを挿入する。
     * @param map </br>
     *            url...String </br>
     *            name...String
     * @return int insertId
     */
    public int tutorialINSERTType2(
                                   Map<String, Object> map);

    /**
     * linkテーブルにデータを挿入する。
     * @param map </br>
     *            url...String </br>
     *            name...String
     * @return int insertCount </br>
     *         map</br>
     *         returnId...int
     */
    public int tutorialINSERTType3(
                                   Map<String, Object> map);

    /**
     * linkテーブルにデータを挿入する。
     * @param map </br>
     *            url...String </br>
     *            name...String
     * @return int insertCount </br>
     *         map</br>
     *         returnId...int
     */
    public int tutorialINSERTType4(
                                   Map<String, Object> map);

    /**
     * linkテーブルのデータを更新する。
     * @return int updateCount
     */
    public int tutorialUPDATEType1();

    /**
     * linkテーブルのデータを更新する。
     * @param map </br>
     *            name...String
     * @return int updateCount
     */
    public int tutorialUPDATEType2(
                                   Map<String, Object> map);

    /**
     * linkテーブルのデータを削除する。
     * @param map </br>
     *            id...int
     * @return int deleteCount
     */
    public int tutorialDELETE(
                              Map<String, Object> map);

}
