package jtk.springboot.controller;

import jtk.springboot.mysql.testdB.entities.User;
import jtk.springboot.mysql.testdB.entities.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jubin on 31/12/16.
 */
@RestController
public class UserAuthorizationController {

    private Logger logger  = LoggerFactory.getLogger(UserAuthorizationController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/authenticate/{username}/{password}", method = RequestMethod.GET)
    public String getUserToken(@PathVariable String username,@PathVariable String password){
        User user  = userRepository.findByUsernameAndPassword(username,password);
        if(user==null)
            throw new UsernameNotFoundException("User not found");
        return createToken(user);
    }

    @RequestMapping(value = "/validaterequest/{token}", method = RequestMethod.GET)
    public HttpStatus hasTokenExpired(@PathVariable String token){
        String[] tokens = token.split("\\|");
        if(tokens.length!=3)
            throw new InsufficientAuthenticationException("Token not correctly formed");
        Instant exp  = Instant.ofEpochMilli(Long.valueOf(tokens[2]));

        logger.info("expiry " + exp.toString());
        if (exp.isBefore(Instant.now()))
            return HttpStatus.REQUEST_TIMEOUT;
        else return HttpStatus.ACCEPTED;
    }


    private String createToken(User user) {
        Instant expiryInstant = Instant.now().plus(Duration.ofMinutes(5).plusMinutes(4));
        logger.info("Token created with expiry " + expiryInstant.toString());

        String userName = user.getUsername();
        String password = user.getPassword();
        return userName + "|" +password+ "|" +expiryInstant.toEpochMilli();
    };

    @ExceptionHandler({UsernameNotFoundException.class})
    public Map<String, Object> UserNotFoundException(HttpServletRequest req, Exception ex){
        Map<String,Object> message = new HashMap();
        message.put("excetion",ex.getMessage());
        message.put("status",HttpStatus.UNAUTHORIZED);
        return message;
    }
}
