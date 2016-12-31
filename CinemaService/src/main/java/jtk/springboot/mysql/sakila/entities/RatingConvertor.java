package jtk.springboot.mysql.sakila.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by jubin on 29/12/16.
 */

@Converter(autoApply = true)
public class RatingConvertor implements AttributeConverter<Films.Rating, String> {

    @Override
    public String convertToDatabaseColumn(Films.Rating attribute) {
        return attribute.toString();
    }

    @Override
    public Films.Rating convertToEntityAttribute(String dbData) {
        Films.Rating rating=Films.Rating.UNRATED;
        if(dbData.equals("G"))
            rating= Films.Rating.G;
        else if(dbData.equals("PG"))
            rating= Films.Rating.PG;
        else if(dbData.equals("PG-13"))
            rating= Films.Rating.PG13;
        else if(dbData.equals("R"))
            rating= Films.Rating.R;
        else if(dbData.equals("NC-17"))
            rating= Films.Rating.NC17;
        return rating;
    }
}
