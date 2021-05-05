package com.sabu.springldapcrudauth.config;

import com.sabu.springldapcrudauth.dto.LdapAuthStructure;
import com.sabu.springldapcrudauth.security.LdapAuthenticationFilter;
import com.sabu.springldapcrudauth.service.LdapUserAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/05/03
 */
@Slf4j
@Configuration
@EnableWebSecurity
@ComponentScan("com.sabu.springldapcrudauth.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LdapAuthStructure ldapAuthStructure;

    @Autowired
    private LdapUserAuthenticationService authenticationService;

    @Autowired
    private LdapAuthenticationFilter ldapAuthenticationFilter;

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers("/createUser")
//                .antMatchers("/verifyUser")
//                .antMatchers("/delete/*");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/createUser").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(ldapAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
//        authManagerBuilder.userDetailsService(authenticationService);
//    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().fullyAuthenticated().and()
//                .formLogin()
//                .successHandler((request, response, authentication) -> {
//                    response.getWriter().append("OK");
//                    response.setStatus(200);
//                    log.info("AHVJAdhjSfjavs================" + authentication);
//                });
//        log.info("configure method is called to make the resources secure ...");
// }

    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.ldapAuthentication()
                .userDnPatterns(ldapAuthStructure.getUserDnPattern())
                .userSearchBase(ldapAuthStructure.getUserSearchBase())
                .contextSource()
                .url(ldapAuthStructure.getLdapUrl() + "/" + ldapAuthStructure.getLdapBase())
                .managerDn(ldapAuthStructure.getLdapManagerDn())
                .managerPassword(ldapAuthStructure.getLdapManagerPwd())
                .and()
                .passwordCompare()
                .passwordEncoder(bCryptPasswordEncoder())
                .passwordAttribute("userPassword");

        log.info("configure method is called to build Authentication manager ...");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//
//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        encoders.put(PwdEncodingAlgo.BCrypt.getStatus(), new BCryptPasswordEncoder());
//        encoders.put(PwdEncodingAlgo.Pbkf2.getStatus(), new Pbkdf2PasswordEncoder());
//        encoders.put(PwdEncodingAlgo.SCrypt.getStatus(), new SCryptPasswordEncoder());
//
//        return new DelegatingPasswordEncoder(PwdEncodingAlgo.BCrypt.getStatus(), encoders);
//    }
}
