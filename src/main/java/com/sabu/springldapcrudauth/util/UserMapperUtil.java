package com.sabu.springldapcrudauth.util;

import com.googlecode.jmapper.JMapper;
import com.sabu.springldapcrudauth.dto.UserDTO;
import com.sabu.springldapcrudauth.entities.LdapAuthUser;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/05/03
 */
public class UserMapperUtil {
    private static final JMapper<UserDTO, LdapAuthUser> userMapper;

    static {
        userMapper = new JMapper<>(UserDTO.class, LdapAuthUser.class);
    }

    public static UserDTO getUserDTOFromEntity(LdapAuthUser authUser) {
        return userMapper.getDestination(authUser);
    }
}
