create database high_school;

use high_school;

create table Student (
    id_student integer not null primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    registration_number integer unsigned,
    date_of_birth date
);

create table Teacher (
    id_teacher integer not null primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    date_of_birth date
);

create table Course (
    id_course integer not null primary key,
    id_teacher integer,
    name varchar(30), 
    # hours by week is calculated column
    foreign key (id_teacher) references Teacher(id_teacher)
);

create table Schedule(
    id_schedule integer not null primary key,
    id_course integer not null,
    day_of_week tinyint,
    time_from time,
    time_to time,
    foreign key (id_course) references Course(id_course)
);

create table Note (
    id_course integer not null,
    id_student integer not null,
    first_partial_note integer,
    second_partial_note integer,
    third_partial_note integer,
    final_note integer;
    primary key (id_course,id_student),
    foreign key (id_course) references Course(id_course),
    foreign key (id_student) references Student(id_student)
);

create table Assist(
    id_course integer not null,
    id_student integer not null,
    year integer,
    primary key (id_course,id_student),
    foreign key (id_course) references Course(id_course),
    foreign key (id_student) references Student(id_student)
);