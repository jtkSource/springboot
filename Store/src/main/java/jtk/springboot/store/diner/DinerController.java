package jtk.springboot.store.diner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller to manage the Diner's REST<i>ful</i> service.
 *
 */
@RestController
public class DinerController {

    private static final Logger logger = LoggerFactory.getLogger(DinerController.class);


    /**
     * This method finds if the diner is open on a given day.
     *
     * @param dinerRequest
     * @return a trivial diner response
     */
    @RequestMapping(path = "/diner/isopen", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String isOpen(@RequestBody(required = false) TrivialDinerRequest dinerRequest) {
        final String day = dinerRequest.getDay();
        logger.info("Diner day = " + day);
        String status = "open";
        // The diner is open on all days except Sunday
        if ("1".equalsIgnoreCase(day)) {
            status = "close";
        }
        final TrivialDinerResponse response = new TrivialDinerResponse();
        response.setStatus(status);
        return status;
    }
}