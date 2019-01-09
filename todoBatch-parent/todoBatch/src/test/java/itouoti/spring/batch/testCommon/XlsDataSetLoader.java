package itouoti.spring.batch.testCommon;

import java.io.InputStream;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

/**
 * xlsxローダークラス </br>
 * AbstractDataSetLoaderクラスを継承する
 * @author itouoti
 */
public class XlsDataSetLoader extends AbstractDataSetLoader {

    // 標準のxml形式からxlsl形式のフォーマットを読み込む設定に変更（override）する
    @Override
    public IDataSet createDataSet(
            Resource resource) throws Exception {

        try (InputStream inputStream = resource.getInputStream()) {

            IDataSet xlsxData = new XlsDataSet(inputStream);
            ReplacementDataSet replaceData = new ReplacementDataSet(xlsxData);
            replaceData.addReplacementObject("[EMPTY_STRING]", "");

            return replaceData;
        }

    }

}
