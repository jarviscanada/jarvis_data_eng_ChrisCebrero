# Introduction

The project involves creating tables, loading data, and implementing SQL queries to extract specific information from the database. The purpose of the project is to provide a learning opportunity for users interested in SQL and database management, allowing them to practice creating tables, loading data, and executing SQL queries. Bash, Docker, Git and PostgreSQL were the technologies used for this project.

# SQL Queries

###### Table Setup (DDL)

```sql
CREATE TABLE bookings (
    bookid integer NOT NULL,
    facid integer NOT NULL,
    memid integer NOT NULL,
    starttime timestamp without time zone NOT NULL,
    slots integer NOT NULL
);

CREATE TABLE facilities (
    facid integer NOT NULL,
    name character varying(100) NOT NULL,
    membercost numeric NOT NULL,
    guestcost numeric NOT NULL,
    initialoutlay numeric NOT NULL,
    monthlymaintenance numeric NOT NULL
);

CREATE TABLE members (
    memid integer NOT NULL,
    surname character varying(200) NOT NULL,
    firstname character varying(200) NOT NULL,
    address character varying(300) NOT NULL,
    zipcode integer NOT NULL,
    telephone character varying(20) NOT NULL,
    recommendedby integer,
    joindate timestamp without time zone NOT NULL
);
```
###### Question 1: Show all members 

```sql
SELECT *
FROM cd.members
```

