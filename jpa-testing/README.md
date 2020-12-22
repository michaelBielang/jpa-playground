## Topics of this module

- **Logging**\
  https://docs.jboss.org/hibernate/stable/core.old/reference/en/html/configuration-logging.html \
  and\
  https://docs.spring.io/spring-boot/docs/2.1.4.RELEASE/reference/html/boot-features-logging.html
- **h2 console**\
  https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-sql-h2-console
- **Init. DB**\
  https://docs.spring.io/spring-boot/docs/2.1.0.M1/reference/html/howto-database-initialization.html#howto-initialize-a-database-using-spring-jdbc

### What you should know

- **DDL/DML**\
  https://www.tutorialspoint.com/difference-between-ddl-and-dml-in-dbms

**DDL** | This should be the content of the schema.sql for example:

```h2
CREATE TABLE PERSON
(
  EMAIl      VARCHAR(255) PRIMARY KEY NOT NULL,
  FIRST_NAME VARCHAR(255),
  LAST_NAME  VARCHAR(255)             NOT NULL
);
```

**DML** | This should be the content of the insert_test_person.sql for example:

```h2
INSERT INTO PERSON (EMAIL, LAST_NAME)
VALUES ('test@gmail.com', 'Paul');

INSERT INTO PERSON
VALUES ('Hans.Mueller@gmail.com', 'Hans', 'Mueller');
```
