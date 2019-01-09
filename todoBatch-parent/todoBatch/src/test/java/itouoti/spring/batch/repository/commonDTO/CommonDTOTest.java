package itouoti.spring.batch.repository.commonDTO;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import itouoti.spring.batch.testCommon.AccessorCoverage;

/**
 * カバレッジ取得用テストコード DTO系
 * @author btitouknzf
 */
public class CommonDTOTest {

    /**
     * DTOのgetter/setterを呼ぶだけのテスト
     * @throws IllegalArgumentException 例外
     * @throws IllegalAccessException 例外
     * @throws InvocationTargetException 例外
     */
    @Test
    public void test() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        AccessorCoverage accessorCoverage = new AccessorCoverage();

        CustomerDTO customerDto = new CustomerDTO();
        accessorCoverage.invokeGetterSetter(customerDto, "");

        FilmDTO filmDto = new FilmDTO();
        accessorCoverage.invokeGetterSetter(filmDto, "");

        LinkDTO linkDto = new LinkDTO();
        accessorCoverage.invokeGetterSetter(linkDto, "");

        PaymentDTO paymentDto = new PaymentDTO();
        accessorCoverage.invokeGetterSetter(paymentDto, "");

        RentalDTO rentalDto = new RentalDTO();
        accessorCoverage.invokeGetterSetter(rentalDto, "");

        T1DTO t1Dto = new T1DTO();
        accessorCoverage.invokeGetterSetter(t1Dto, "");



    }

}
