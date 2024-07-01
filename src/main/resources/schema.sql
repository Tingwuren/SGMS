DROP DATABASE IF EXISTS student_db;
CREATE DATABASE student_db;
USE student_db;

-- 专业班级表
DROP TABLE IF EXISTS class_info;
CREATE TABLE class_info (
    class_id INT AUTO_INCREMENT PRIMARY KEY,
    class_no VARCHAR(255) NOT NULL
);

-- 学生表
DROP TABLE IF EXISTS student_info;
CREATE TABLE student_info (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password TEXT NOT NULL,
    class_id INT,
    student_name VARCHAR(255) NOT NULL,
    sex ENUM('male', 'female') NOT NULL,
    birthdate DATE,
    contact_info VARCHAR(100),
    address VARCHAR(255),
    token TEXT,
    FOREIGN KEY (class_id) REFERENCES class_info(class_id)
);

-- 教师表
DROP TABLE IF EXISTS teacher_info;
CREATE TABLE teacher_info (
    teacher_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password TEXT NOT NULL,
    teacher_name VARCHAR(255) NOT NULL,
    sex ENUM('male', 'female') NOT NULL,
    token TEXT
);

-- 课程表
DROP TABLE IF EXISTS course_info;
CREATE TABLE course_info (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_no VARCHAR(255) NOT NULL,
    course_name VARCHAR(255) DEFAULT NULL,
    teacher_id INT NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES teacher_info(teacher_id)
);

-- 学生选课成绩表
DROP TABLE IF EXISTS student_course;
CREATE TABLE student_course (
    score_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    score INT DEFAULT NULL,
    FOREIGN KEY (student_id) REFERENCES student_info(student_id),
    FOREIGN KEY (course_id) REFERENCES course_info(course_id)
);

-- 管理员表
DROP TABLE IF EXISTS admin_info;
CREATE TABLE admin_info (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password TEXT NOT NULL,
    token TEXT
);
