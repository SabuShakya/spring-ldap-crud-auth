package com.sabu.springldapcrudauth.dto;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/05/03
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    @JMap("userName")
    private String userName;

    @JMap("firstName")
    private String firstName;

    @JMap("lastName")
    private String lastName;
}
