use test;
create table Books(
id int(10) not null auto_increment,
title varchar(100) not null,
description varchar(255),
author varchar(100) not null,
isbn varchar(20) not null,
printyear int(10) not null,
readAlready boolean not null,
primary key (id)
)
default character set = utf8;

insert into Books(title, description, author, isbn, printyear, readAlready)
values("Евгений Онегин", "Мировая классика", "Пушкин", "214132", "1831", false),
("Война и мир", "Повествование о войне 1812 года", "Лев Толстой", "1221312312", "1865", true),
("Мартин Иден", "Философский классический роман", "Джек Лондон", "213121332", "1890", true),
("Преступление и наказание", "Социально-психологический роман", "Федор Достоевский", "213121332", "1866", true),
("Анна Каренина", "Величайшая история любви", "Лев Толстой", "2132231", "1893", false),
("book1", "description1", "author1", "111111", "1111", false),
("book2", "description2", "author2", "111112", "1112", false),
("book3", "description3", "author3", "111113", "1113", false),
("book4", "description4", "author1", "111111", "1111", false),
("book5", "description5", "author1", "111111", "1111", false),
("book6", "description6", "author1", "111111", "1111", false),
("book7", "description7", "author1", "111111", "1111", false),
("book8", "description8", "author1", "111111", "1111", false),
("book9", "description9", "author1", "111111", "1111", false),
("book10", "description10", "author1", "111111", "1111", false),
("book11", "description11", "author1", "111111", "1111", false),
("book12", "description12", "author1", "111111", "1111", false),
("book13", "description13", "author1", "111111", "1111", false),
("book14", "description14", "author1", "111111", "1111", false),
("book15", "description15", "author1", "111111", "1111", false),
("book16", "description16", "author1", "111111", "1111", false),
("book17", "description17", "author1", "111111", "1111", false),
("book18", "description18", "author1", "111111", "1111", false),
("book19", "description19", "author1", "111111", "1111", false),
("book20", "description20", "author1", "111111", "1111", false),
("book21", "description21", "author1", "111111", "1111", false),
("book22", "description22", "author1", "111111", "1111", false),
("book23", "description23", "author1", "111111", "1111", false),
("book24", "description24", "author1", "111111", "1111", false),
("book25", "description25", "author1", "111111", "1111", false),
("book26", "description26", "author1", "111111", "1111", false),
("book27", "description27", "author1", "111111", "1111", false),
("book28", "description28", "author1", "111111", "1111", false),
("book29", "description29", "author1", "111111", "1111", false),
("book30", "description30", "author1", "111111", "1111", false);