package com.sabu.springldapcrudauth.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/05/03
 */
@Getter
@Setter
public class LdapAuthStructure {

    private String ldapUrl;

    private String ldapBase;

    private String ldapManagerPwd;

    private String ldapManagerDn;

    private String userDnPattern;

    private String userSearchBase;
}
