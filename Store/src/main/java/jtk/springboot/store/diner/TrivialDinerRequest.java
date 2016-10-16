package jtk.springboot.store.diner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jubin on 15/10/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
 public class TrivialDinerRequest {

    /**The Day of Week, 1 = Sunday, 2, 3, ...**/
    private String day;

    public String getDay() {
        return day;
    }
}
