package com.sabu.springldapcrudauth.service;

import com.sabu.springldapcrudauth.dto.LoginRequestDTO;
import com.sabu.springldapcrudauth.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/05/04
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final LdapUserAuthenticationService ldapUserAuthenticationService;

    public String login(LoginRequestDTO loginRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUserName(),
                            loginRequestDTO.getPassword()
                    ));
            log.info("Authentication", authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password.", e);
        }

        UserDetails userDetails = ldapUserAuthenticationService.loadUserByUsername(loginRequestDTO.getUserName());
        String token = "Bearer " + jwtUtil.generateToken(userDetails);
        return token;
    }
}
