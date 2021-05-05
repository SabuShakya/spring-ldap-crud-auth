package com.sabu.springldapcrudauth.service;

import com.sabu.springldapcrudauth.dto.UserDTO;
import com.sabu.springldapcrudauth.dto.UserSaveRequestDTO;
import com.sabu.springldapcrudauth.entities.LdapAuthUser;
import com.sabu.springldapcrudauth.repository.LdapAuthRepository;
import com.sabu.springldapcrudauth.util.UserMapperUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.ldap.LdapName;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/05/03
 */
@Slf4j
@Service
@AllArgsConstructor
public class LdapUserService {
    private final LdapAuthRepository ldapAuthRepository;

    // create

    /**
     * @param userSaveRequestDTO The ID is of the type javax.naming.Name.
     *                           The LdapNameBuilder class is used to build the ID.
     */
    public void addUser(UserSaveRequestDTO userSaveRequestDTO) {
        /*
        To create a new user, the object of the model class (LdapAuthUser) needs to be created first,
        along with its attribute.
         */
        LdapAuthUser ldapUser = new LdapAuthUser();
        ldapUser.setUserName(userSaveRequestDTO.getUserName());
        ldapUser.setFirstName(userSaveRequestDTO.getFirstName());
        ldapUser.setLastName(userSaveRequestDTO.getLastName());
        ldapUser.setPassword(new BCryptPasswordEncoder().encode(userSaveRequestDTO.getPassword()));
        //  to make sure that the Spring Data module considers it a new record.
        //  Without doing this, the system will throw an error.
        ldapUser.setIsNew(true);

        LdapName dn = LdapNameBuilder.newInstance()
                .add("ou=users")
                .add("uid", ldapUser.getUserName())
                .build();
        ldapUser.setId(dn);

        boolean isExist = ldapAuthRepository.existsById(dn);

        if (!isExist) {
            ldapAuthRepository.save(ldapUser);
        } else {
            log.info("User with username " + ldapUser.getUserName() + " already exists.");
        }
    }

    //Read
    public UserDTO getUser(String userName) {
        LdapAuthUser ldapAuthUser = getLdapAuthUser(userName);
        UserDTO userDTO = UserMapperUtil.getUserDTOFromEntity(ldapAuthUser);
        return userDTO;
    }

    public UserDTO verify(UserSaveRequestDTO userSaveRequestDTO) {
        LdapAuthUser ldapAuthUser = getLdapAuthUser(userSaveRequestDTO.getUserName());
        System.out.println(new BCryptPasswordEncoder().encode("admin123"));

        String byteArrayInString = ldapAuthUser.getPassword();
        String[] stringArray = byteArrayInString.split(",");
        byte[] asd = new byte[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            asd[i] = Byte.parseByte(stringArray[i]);
        }
        String orgiPass = new String(asd);
        System.out.println(orgiPass);
        System.out.println(new BCryptPasswordEncoder().matches(userSaveRequestDTO.getPassword(), orgiPass));
        UserDTO userDTO = UserMapperUtil.getUserDTOFromEntity(ldapAuthUser);
        return userDTO;
    }

    public LdapAuthUser getLdapAuthUser(String userName) {
        Optional<LdapAuthUser> ldapAuthUserOptional = ldapAuthRepository.
                findOne(LdapQueryBuilder.query().where("uid").is(userName));
        LdapAuthUser ldapAuthUser = ldapAuthUserOptional.orElse(null);
        return ldapAuthUser;
    }

    public List<UserDTO> getAllUsers() {
        Iterable<LdapAuthUser> users = ldapAuthRepository.findAll();

        List<LdapAuthUser> ldapAuthUsers = (List<LdapAuthUser>) users;
        List<UserDTO> userList = ldapAuthUsers
                .stream()
                .map(UserMapperUtil::getUserDTOFromEntity)
                .collect(Collectors.toList());
        return userList;
    }

    //Update
    public void updateLdapUser(UserSaveRequestDTO userSaveRequestDTO) {
        LdapAuthUser ldapUser = getLdapAuthUser(userSaveRequestDTO.getUserName());
        if (ldapUser != null) {
            ldapUser.setFirstName(userSaveRequestDTO.getFirstName());
            ldapUser.setLastName(userSaveRequestDTO.getLastName());
            ldapAuthRepository.save(ldapUser);
        }
    }

    //Delete
    public void deleteUser(String userName) {
        Optional<LdapAuthUser> ldapAuthUserOptional = ldapAuthRepository.
                findOne(LdapQueryBuilder.query().where("uid").is(userName));
        if (ldapAuthUserOptional.isPresent()) {
            ldapAuthRepository.delete(ldapAuthUserOptional.get());
        } else {
            log.info("User with username " + userName + " does not exist ");
        }
    }
}
