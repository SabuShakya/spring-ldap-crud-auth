# LDAP CRUD AND AUTHENTICATION USING SPRING-DATA-LDAP #

## What is LDAP ? ##
 
LDAP-the Lightweight Directory Access Protocol is a set of protocol used for accessing and modifying centrally stored 
information over a network. It is a mature, flexible,and well-supported standards-based mechanism for interacting with 
[directory servers](#directory-serverhttpsldapcomdirectory-servers). It’s often used for authentication and storing information about users, groups, and applications.
This is used predominantly by applications for resource information lookup.

Unlike traditional databases, an LDAP database is especially suited for read, search, and browse operations instead of
write operations. It is with reads that LDAP shines.  
When you have a task that requires “write/update once, read/query many times”, you might consider using LDAP.
LDAP is designed to provide extremely fast read/query performance for a large scale of dataset. Typically, you want
to store only a small piece of information for each entry. The add/delete/update performance is relatively slower
compared with read/query because the assumption is that you don’t do “update” that often.

![Alt text](./images/ldap1.png?raw=true "Title")
[Image Reference](https://youtu.be/lp5z8HQGAH8)

## Why LDAP ?? ##

- **LDAP is lightweight**. LDAP messages are encoded with ASN.1 BER, which is a compact binary format that is very 
  efficient to encode and decode. It’s much more streamlined than something like JSON or XML over HTTP. 
  LDAP uses persistent connections for communication with a directory server(can live for hours or days or longer) 
  while other protocols use relatively short-lived connections. This can make a big difference when it comes to 
  performance and scalability because establishing a new connection is significantly more expensive than using
  one that’s already available.
  
- **LDAP is Secure**. It includes a great deal of password policy functionality, like strong encoding mechanisms 
  and constraints that can prevent users from selecting weak passwords, but it also includes support for a variety 
  of authentication types through SASL (the simple authentication and security layer), including the possibility 
  of two-factor options through mechanisms like one-time passwords. On top of that, directory servers typically 
  provide support for fine-grained access controls that restrict which entries, attributes, and values any 
  individual user can access, and in what ways.

## Uses ##
- LDAP can serve as a complete identity management solution for an organization. It can provide authentication
  and authorization services for users
- LDAP can be used to provide “yellow pages” services for an organization (for instance, users’ or employees’ 
  contact information—phone numbers, addresses, departments, and so on)
- The information stored in DNS records can be stored in LDAP. 
- User/System Groups
- Address book
- Organization Representation
- Asset Tracking
- Telephony Information Store
- User resource management
- E-mail address lookups
- Application Configuration store


## [Directory Server](https://ldap.com/directory-servers/) ##
- A Directory System Agent, or a DSA, is a type of network database that stores information represented as trees of entries.
- A directory is a specialized database specifically designed for searching and browsing, in additional to 
  supporting basic lookup and update functions.
- A server for storing resource specific information, to lookup for information.
- A directory service is a customizable information store that functions as a single point from which users can locate
  resources and services distributed throughout the network. This customizable information store also gives
  administrators a single point for managing its objects and their attributes.
  
## Basic LDAP Concepts ##

### DIT(Data Information Tree) ###
The LDAP server stores the information in a hierarchical (or a tree) form. This tree of data is the Data Information Tree.

![DIT](./images/DIT.png?raw=true "DIT")

