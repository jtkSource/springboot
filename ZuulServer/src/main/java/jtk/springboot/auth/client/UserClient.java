package jtk.springboot.auth.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jubin on 31/12/16.
 */
@Configuration
@EnableFeignClients
@EnableDiscoveryClient
public class UserClient {

    @Autowired
    private ValidateClient validateClient;


    public String getUserToken(String username,String password){
        return validateClient.getUserToken(username,password);
    }

    public HttpStatus hasTokenExpired(String token){
        return validateClient.hasTokenExpired(token);
    }



    @FeignClient(name = "auth")
    public interface ValidateClient {
        @RequestMapping(value = "/authenticate/{username}/{password}", method = RequestMethod.GET,
                consumes =MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        String getUserToken(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password);


        @RequestMapping(value = "/validaterequest/{token}", method = RequestMethod.GET,
                consumes =MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public HttpStatus hasTokenExpired(@RequestParam(value = "token") String token);

    }


}
