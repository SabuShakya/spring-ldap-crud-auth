package com.sabu.springldapcrudauth.repository;

import com.sabu.springldapcrudauth.entities.LdapAuthUser;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/05/03
 */
@Repository
public interface LdapAuthRepository extends LdapRepository<LdapAuthUser> {

}
