package jtk.springboot.db.util;

import javax.persistence.AttributeConverter;

/**
 * Created by jubin on 31/12/16.
 */
public class IntToBooleanConvertor implements AttributeConverter<Boolean,Integer> {
    public Integer convertToDatabaseColumn(Boolean attribute) {
        return attribute?1:0;
    }

    public Boolean convertToEntityAttribute(Integer dbData) {
        return dbData==1?true:false;
    }
}
