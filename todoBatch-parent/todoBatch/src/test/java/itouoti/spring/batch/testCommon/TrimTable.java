package itouoti.spring.batch.testCommon;

import org.apache.commons.lang.StringUtils;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableMetaData;

/**
 * 比較対象テーブルのString項目のTrim加工を行う
 * @author itouoti
 */
public class TrimTable implements ITable {

    protected ITable table;

    protected ITableMetaData metaData;

    /**
     * Constructor
     * @param table トリム対象テーブル
     */
    public TrimTable(ITable table) {
        this.table = table;
        this.metaData = table.getTableMetaData();
    }

    /**
     * Getter
     * @return int 行
     */
    public int getRowCount() {
        return table.getRowCount();
    }

    /**
     * Getter
     * @return ITableMetaData
     */
    public ITableMetaData getTableMetaData() {
        return metaData;
    }

    /**
     * 値加工
     * @param row 行目
     * @param columnName カラム名
     * @exception DataSetException 例外
     * @return Object
     */
    public Object getValue(
            int row,
            String columnName) throws DataSetException {
        Object value = table.getValue(row, columnName);

        // トリム処理(両端空白削除)
        if (value instanceof String) {
            value = StringUtils.strip((String) value);
        }

        return value;
    }
}
