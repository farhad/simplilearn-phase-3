drop database learners_academy;
create database learners_academy;

use learners_academy;

create table Users(
                      id INT auto_increment,
                      first_name varchar(50),
                      last_name varchar(50),
                      user_name varchar(120),
                      pass_word varchar(30),
                      assigned_role varchar(20),
                      primary key (id)
);

create table Subjects(
                         id INT auto_increment,
                         title varchar(50),
                         description varchar(100),
                         primary key (id)
);

create table Teachers(
                         id INT auto_increment,
                         first_name varchar(50),
                         last_name varchar(50),
                         bio varchar(100),
                         primary key (id)
);

create table Students(
                         id INT auto_increment,
                         first_name varchar(50),
                         last_name varchar(50),
                         primary key (id)
);

create table Classes(
                        id INT auto_increment,
                        subject_id INT ,
                        teacher_id INT,
                        title varchar(50),
                        description varchar(100),
                        primary key (id),

                        constraint fk_subjects foreign key (subject_id) references Subjects(id)
                            on update cascade
                            on delete cascade,

                        constraint fk_teachers foreign key (teacher_id) references Teachers(id)
                            on update cascade
                            on delete cascade
);

create table Enrollments(
                            id INT auto_increment,
                            student_id INT ,
                            class_id INT,
                            primary key (id),

                            constraint fk_students foreign key (student_id) references Students(id)
                                on update cascade
                                on delete cascade,

                            constraint fk_classes foreign key (class_id) references Classes(id)
                                on update cascade
                                on delete cascade
);