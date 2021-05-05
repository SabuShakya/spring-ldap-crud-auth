package com.sabu.springldapcrudauth.util;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/05/04
 */
public enum PwdEncodingAlgo {
        BCrypt("bcrypt"), Pbkf2("pbkdf2"), SCrypt("scrypt");

        private String status;

        private PwdEncodingAlgo(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
}
