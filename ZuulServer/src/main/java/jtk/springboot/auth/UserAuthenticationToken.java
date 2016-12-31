package jtk.springboot.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by jubin on 31/12/16.
 */
public class UserAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String xAuth;

    public UserAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String xAuth) {
        super(principal, credentials, authorities);
        this.xAuth = xAuth;
    }


}
