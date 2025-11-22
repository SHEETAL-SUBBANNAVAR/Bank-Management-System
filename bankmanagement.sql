create database bankmanagementsystem;

show databases;

use bankmanagementsystem;

create table signup(formno varchar(20),name varchar(20),father_name varchar(20),dob varchar(20),gender varchar(20),email varchar(30),marital_status varchar(20),address varchar(40),city varchar(25),pincode varchar(20),state varchar(25));

show tables;

select * from signup;

create table signuptwo(formno varchar(20),religion varchar(20),category varchar(20),income varchar(20),education varchar(20),occupation varchar(30),pan varchar(20),aadhar varchar(40),seniorciizen varchar(25),existingaccount varchar(20));
select * from signuptwo;

CREATE TABLE signupthree ( formno VARCHAR(20),accountType VARCHAR(40),cardnumber VARCHAR(25),pin VARCHAR(10),facility VARCHAR(100));
create table login(formno varchar(20) ,cardnumber varchar(25) ,pin varchar(10));

select * from signupthree;

select * from login;

drop table bank;

CREATE TABLE bank(
    pin VARCHAR(10),
    date VARCHAR(30),
    type VARCHAR(20),
    amount VARCHAR(20)
);
select * from bank;

drop database bankmanagementsystem;
DESC bank;
