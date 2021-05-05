package com.sabu.springldapcrudauth.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;
import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/05/03
 */
@Entry(
        base = "ou=users",
        objectClasses = {"top", "person", "organizationalPerson", "inetOrgPerson"}
)
@Getter
@Setter
/*
  Another unique point is that this model class implements the Persistable<Name> interface.
  Especially for the LDAP, at the time of adding a new entity, Spring Data does not have any
  way of knowing whether the entity is new or existing. So, every time Spring Data
  tries to search for an existing entity, it will throw an error if the entity does not exist.
  To avoid this, while adding a new record,we will explicitly set the
  isNew attribute to true, so that Spring Data will get its value with the overridden method isNew().
 */
public class LdapAuthUser implements Persistable<Name>, Serializable {
    @Id
    private Name id;

    /**
     * The "@Attribute" annotation is used to map the instance variable with the attribute of the LDAP entity.
     * The "@DnAttribute" is used to populate the values automatically, from the distinguished names of the entries found.
     */
    @Attribute(name = "uid")
    @DnAttribute(value = "uid")
    private String userName;

    @Attribute(name = "cn")
    private String firstName;

    @Attribute(name = "sn")
    private String lastName;

    @Attribute(name = "userPassword")
    private String password;

    @Transient
    private boolean isNew;

    @Override
    public Name getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }
}
