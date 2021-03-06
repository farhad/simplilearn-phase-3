drop
database learners_academy;
create
database learners_academy;

use
learners_academy;

create table Users
(
    id            INT auto_increment,
    first_name    varchar(50),
    last_name     varchar(50),
    username      varchar(120),
    password      varchar(30),
    assigned_role varchar(20),
    primary key (id)
);

create table Subjects
(
    id          INT auto_increment,
    title       varchar(50),
    description varchar(100),
    primary key (id)
);

create table Teachers
(
    id         INT auto_increment,
    first_name varchar(50),
    last_name  varchar(50),
    bio        varchar(100),
    primary key (id)
);

create table Students
(
    id         INT auto_increment,
    first_name varchar(50),
    last_name  varchar(50),
    primary key (id)
);

create table Courses
(
    id          INT auto_increment,
    subject_id  INT,
    teacher_id  INT,
    title       varchar(50),
    description varchar(100),
    primary key (id),

    constraint fk_subjects foreign key (subject_id) references Subjects (id)
        on update cascade
        on delete cascade,

    constraint fk_teachers foreign key (teacher_id) references Teachers (id)
        on update cascade
        on delete cascade
);

create table Enrollments
(
    id         INT auto_increment,
    student_id INT,
    course_id  INT,
    primary key (id),

    constraint fk_students foreign key (student_id) references Students (id)
        on update cascade
        on delete cascade,

    constraint fk_courses foreign key (course_id) references Courses (id)
        on update cascade
        on delete cascade
);

insert into Users (first_name, last_name, username, password, assigned_role)
values ('SimpliLearn', 'Admin', 'admin', 'admin', 'admin');