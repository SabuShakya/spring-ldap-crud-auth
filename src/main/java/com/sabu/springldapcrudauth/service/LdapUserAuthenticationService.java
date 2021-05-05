package com.sabu.springldapcrudauth.service;

import com.sabu.springldapcrudauth.entities.LdapAuthUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/05/04
 */
@Service
@AllArgsConstructor
public class LdapUserAuthenticationService implements UserDetailsService {

    private final LdapUserService ldapUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LdapAuthUser ldapAuthUser = ldapUserService.getLdapAuthUser(username);
        if (Objects.isNull(ldapAuthUser))
            throw new UsernameNotFoundException("User with username '" + username + "' not found!");

        UserDetails userDetails = new User(
                ldapAuthUser.getUserName(),
                ldapAuthUser.getPassword(),
                new ArrayList<>());
        return userDetails;
    }
}
