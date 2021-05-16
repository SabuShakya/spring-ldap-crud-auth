# LDAP CRUD AND AUTHENTICATION USING SPRING-DATA-LDAP #

## What is LDAP ? ##

LDAP-the Lightweight Directory Access Protocol is a set of protocol used for accessing and modifying centrally stored
information over a network. It is a mature, flexible,and well-supported standards-based mechanism for interacting with
directory servers. It’s often used for authentication and storing information about users, groups, and applications.
This is used predominantly by applications for resource information lookup.

Unlike traditional databases, an LDAP database is especially suited for read, search, and browse operations instead of
write operations. It is with reads that LDAP shines.  
When you have a task that requires “write/update once, read/query many times”, you might consider using LDAP. LDAP is
designed to provide extremely fast read/query performance for a large scale of dataset. Typically, you want to store
only a small piece of information for each entry. The add/delete/update performance is relatively slower compared with
read/query because the assumption is that you don’t do “update” that often.

For detail concepts : [LDAP CONCEPTS](https://github.com/SabuShakya/ldap-concepts)

