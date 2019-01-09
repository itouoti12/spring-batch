package itouoti.spring.batch.testCommon;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;

import itouoti.spring.batch.testCommon.dataType.ArrayDataType;
import itouoti.spring.batch.testCommon.dataType.Mpaa_ratingDataType;
import itouoti.spring.batch.testCommon.dataType.TsVectorDataType;

public class PsqlArrayDataTypeFactory extends PostgresqlDataTypeFactory {

    public DataType createDataType(int sqlType,
                                   String sqlTypeName,
                                   String tableName,
                                   String columnName) throws DataTypeException {

        if (sqlType == 2003) {
            if (sqlTypeName.equals("_text")) {
                return new ArrayDataType(sqlTypeName, sqlType, false);
            }
        }

        if (sqlType == 1111) {
            if (sqlTypeName.equals("tsvector")) {
                return new TsVectorDataType(sqlTypeName, sqlType);
            }
        }
        
        if (sqlType == 12) {
            if (sqlTypeName.equals("mpaa_rating")) {
                return new Mpaa_ratingDataType(sqlTypeName, sqlType);
            }
        }

        return super.createDataType(sqlType, sqlTypeName);
    }

}
