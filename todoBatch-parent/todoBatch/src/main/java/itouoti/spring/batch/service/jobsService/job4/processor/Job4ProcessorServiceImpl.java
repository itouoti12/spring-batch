package itouoti.spring.batch.service.jobsService.job4.processor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import itouoti.spring.batch.chunk.jobsChunk.job4.writer.Job4WriterOBJ;
import itouoti.spring.batch.repository.jobsRepository.job4.reader.FindCustomerAmountDTO;

/**
 * Service層実装クラス
 * @author itouoti
 */
@Service
@Scope("step")
public class Job4ProcessorServiceImpl implements Job4ProcessorService {

    @Value("#{jobParameters[firstInitial]}")
    private String firstInitial;

    @Value("#{jobParameters[amount]}")
    private long amount;

    @Value("${job4.param1}")
    private String porpertiesParam1;

    @Value("${job4.param1}")
    private String porpertiesParam2;

    /**
     * Service層主処理
     * @param bean
     */
    @Override
    public Job4WriterOBJ startService(FindCustomerAmountDTO dto) {

        if (goToWriter(dto)) {

            Job4WriterOBJ writerObj = setWriterObj(dto);

            return writerObj;
        } else {
            // nullを返却した場合はwriter層に処理を委譲せずに終了する
            return null;
        }

    }

    /**
     * 出力対象かどうか判定する処理 </br>
     * true...出力対象 </br>
     * false...対象外
     * @param dto Reader層から受け取ったDTO
     * @return boolean
     */
    private boolean goToWriter(FindCustomerAmountDTO dto) {

        // 頭文字が一致
        if (InitialIsNotMatch(dto.getFirstName())) {
            return false;
        }

        // 金額が指定以上
        if (AmountIsLower(dto.getAmount())) {
            return false;
        }

        return true;

    }

    /**
     * Stringの頭文字が指定された文字と一致するか判定する処理</br>
     * true...一致</br>
     * false...不一致
     * @param sentens 判定対象の文字列
     * @return boolean
     */
    private boolean InitialIsNotMatch(String sentens) {

        if (sentens == null) {
            return false;
        }

        String initial = sentens.substring(0, 1);
        if (initial.toUpperCase().equals(firstInitial.toUpperCase())) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 指定された金額以上か判定する処理 </br>
     * true...一致</br>
     * false...不一致
     * @param amount 判定対象の金額
     * @return boolean
     */
    private boolean AmountIsLower(long amount) {

        if (this.amount <= amount) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Writer層に渡すオブジェクトの作成処理
     * @param dto Reader層から受け取ったDTO
     * @return Job4WriterOBJ
     */
    private Job4WriterOBJ setWriterObj(FindCustomerAmountDTO dto) {

        Job4WriterOBJ writeObj = new Job4WriterOBJ();

        writeObj.setCustomerId(dto.getCustomerId());
        writeObj.setFullName(dto.getFirstName() + "-" + dto.getLastName());
        writeObj.setAmount(dto.getAmount());

        return writeObj;

    }

}
