package jtk.springboot.store.diner;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by jubin on 15/10/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrivialDinerResponse {
    /**open or closed**/
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }
}
