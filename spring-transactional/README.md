## Status

[![Project Status: WIP – Initial development is in progress](https://www.repostatus.org/badges/latest/wip.svg)](https://www.repostatus.org/#wip)

## Module

This module presents Spring Transactional features such as propagation and isolation level.

## Features

* [x] Spring Boot and Java 14
* [x] H2 in memory

## Execute

### Main program

Run the ```main``` method in the TransactionalApplication class.

## Isolation - Sequence diagram

![iso_sequence_diagram](../img/isolation_sequence_diagram.png)

## Read options

### Dirty read

![phantom-reads](../img/dirty-read.png)

### Non-repeatable read

![phantom-reads](../img/non-repeatable-reads.png)

### Phantom read

![phantom-reads](../img/phantom-reads.png)

### Summary

![phantom-reads](../img/read-table.png)

## Articles

### Transactions

https://dzone.com/articles/spring-transaction-propagation

https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#transaction-declarative

### Isolations

https://www.postgresql.org/docs/9.5/transaction-iso.html
