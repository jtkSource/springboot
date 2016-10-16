package jtk.springboot.controller.rest;

import jtk.springboot.components.CustomerRegistrar;
import jtk.springboot.entities.Customer;
import jtk.springboot.entities.diner.DinerClient;
import jtk.springboot.entities.diner.TrivialClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jubin on 25/9/16.
 */

@RestController
class CustomerController{

    @Autowired
    private CustomerRegistrar customerRegistrar;

    @Autowired
    private DinerClient dinerClient;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping( path="/open", method = RequestMethod.GET)
    public String isOpen(){

        TrivialClientRequest trivialClientRequest = new TrivialClientRequest();
        trivialClientRequest.setDay("1");
        String status = dinerClient.testDay(trivialClientRequest);
        logger.info("Diner response: {}",status);
        return status;

    }

    @RequestMapping( path="/register", method = RequestMethod.POST)
    public Customer register(@RequestBody  Customer customer){

        return customerRegistrar.register(customer);

    }
}