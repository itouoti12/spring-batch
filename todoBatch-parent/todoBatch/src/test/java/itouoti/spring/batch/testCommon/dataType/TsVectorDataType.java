package itouoti.spring.batch.testCommon.dataType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.dbunit.dataset.datatype.AbstractDataType;
import org.dbunit.dataset.datatype.TypeCastException;
import org.postgresql.util.PGobject;

public class TsVectorDataType extends AbstractDataType {

    public TsVectorDataType(String name,
                            int sqlType) {
        super("tsvector", Types.OTHER, String.class, false);
    }

    public Object typeCast(Object value) throws TypeCastException  {
        if (value == null) {
            return null;
        }

        PGobject obj = new PGobject();
        obj.setType("tsvector");
        try {
            obj.setValue(value.toString());
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return obj;
    }

    public Object getSqlValue(int column,
                              ResultSet resultSet) throws SQLException, TypeCastException {
        return resultSet.getString(column);
    }

}
