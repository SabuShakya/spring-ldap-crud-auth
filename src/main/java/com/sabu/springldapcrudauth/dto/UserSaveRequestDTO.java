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
public class UserSaveRequestDTO {

    private String userName;

    private String firstName;

    private String lastName;

    private String password;
}
