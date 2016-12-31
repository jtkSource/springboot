package jtk.springboot.auth;

import jtk.springboot.auth.client.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by jubin on 31/12/16.
 */
@Component
public class DemoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    Logger logger = LoggerFactory.getLogger(DemoAuthenticationProvider.class);

    @Autowired
    private UserClient userClient;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        logger.info("--------authentication---------");
        HttpStatus httpStatus =  userClient.hasTokenExpired(userDetails.getPassword());
        logger.info("Token status " + httpStatus.name());

        if(httpStatus!=HttpStatus.ACCEPTED) {
            authentication.setAuthenticated(false);
            getUserCache().removeUserFromCache(userDetails.getUsername());
            throw new SessionAuthenticationException("Token expired");
        }
    }


    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        logger.info("--------fetching user information---------");
        logger.info("username: " + username);
        String token = userClient.getUserToken(username,authentication.getCredentials().toString());
        logger.info("token created " + token);
        UserDetails userDetails = new User(username,token, Arrays.asList());
        return userDetails;
    }
}
