package itouoti.spring.batch.testCommon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.util.ResourceUtils;

/**
 * propetiesファイルを読み込むクラス
 * @author itouoti
 */
public class LoadProperties {

    /**
     * propertiesファイルをロードする
     * @param filePath ファイルパス（classpath:XXXXX）
     * @return Properties
     * @throws IOException 例外
     */
    public static Properties load(
            String filePath) throws IOException {

        File file = ResourceUtils.getFile(filePath);
        InputStream input = new FileInputStream(file);

        Properties props = new Properties();
        props.load(input);

        return props;

    }
}
