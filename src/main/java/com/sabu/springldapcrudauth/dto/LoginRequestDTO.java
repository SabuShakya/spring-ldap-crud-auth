package com.sabu.springldapcrudauth.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/05/04
 */
@Getter
@Setter
public class LoginRequestDTO {

    private String userName;

    private String password;
}
