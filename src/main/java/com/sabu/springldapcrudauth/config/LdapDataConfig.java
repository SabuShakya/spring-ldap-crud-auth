package com.sabu.springldapcrudauth.config;

import com.sabu.springldapcrudauth.dto.LdapAuthStructure;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/05/03
 */
@Configuration
@ComponentScan(basePackages = {"com.sabu.springldapcrudauth"})
@EnableLdapRepositories(basePackages = {"com.sabu.ldapcrudwithspring.**"})
public class LdapDataConfig {
    @Value("${spring.ldap.urls}")
    private String ldapUrls;

    @Value("${spring.ldap.base}")
    private String ldapBase;

    @Value("${spring.ldap.password}")
    private String ldapManagerPwd;

    @Value("${spring.ldap.username}")
    private String ldapManagerUserName;


    /*
      userDnPattern: The value is uid={0},ou=users. This is nothing but a DN pattern (relative to the entity ou=users).
      The {0} will be substituted with the actual value (uid, user ID) by Spring at runtime.
      userSearchBase:It represents the user base (ou=users). Basically, it represents an entity under which the users
      can be searched.
      groupSearchBase: It represents the group base (ou=roles). We will use this property to perform authorization in
      upcoming sections.
     */
    @Bean("ldapAuthStructure")
    public LdapAuthStructure getLDAPAuthStructure() {
        LdapAuthStructure authStructure = new LdapAuthStructure();
        authStructure.setLdapUrl(ldapUrls);
        authStructure.setLdapBase(ldapBase);
        authStructure.setLdapManagerDn(ldapManagerUserName);
        authStructure.setLdapManagerPwd(ldapManagerPwd);
        authStructure.setUserDnPattern("uid={0},ou=users");
        authStructure.setUserSearchBase("ou=roles");
        return authStructure;
    }

}
