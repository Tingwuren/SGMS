-- 使用数据库
USE student_db;

-- 插入 ClassInfo 表数据
INSERT INTO class_info (class_id, class_no) VALUES
    (1, 'Class101'),
    (2, 'Class102'),
    (3, 'Class103'),
    (4, 'Class104'),
    (5, 'Class105'),
    (6, 'Class106'),
    (7, 'Class107'),
    (8, 'Class108'),
    (9, 'Class109'),
    (10, 'Class110'),
    (11, 'Class201'),
    (12, 'Class202'),
    (13, 'Class203'),
    (14, 'Class204'),
    (15, 'Class205'),
    (16, 'Class206'),
    (17, 'Class207'),
    (18, 'Class208'),
    (19, 'Class209'),
    (20, 'Class210');

-- 插入 StudentInfo 表数据
INSERT INTO student_info (username, password, class_id, student_name, sex, birthdate, contact_info, address) VALUES
    ('user1', 'password1', 1, '学生一', 'male', '2000-01-01', '1234567890', '地址一'),
    ('user2', 'password2', 2, '学生二', 'female', '2000-02-02', '1234567891', '地址二'),
    ('user3', 'password3', 3, '学生三', 'male', '2000-03-03', '1234567892', '地址三'),
    ('user4', 'password4', 4, '学生四', 'female', '2000-04-04', '1234567893', '地址四'),
    ('user5', 'password5', 5, '学生五', 'male', '2000-05-05', '1234567894', '地址五'),
    ('user6', 'password6', 6, '学生六', 'female', '2000-06-06', '1234567895', '地址六'),
    ('user7', 'password7', 7, '学生七', 'male', '2000-07-07', '1234567896', '地址七'),
    ('user8', 'password8', 8, '学生八', 'female', '2000-08-08', '1234567897', '地址八'),
    ('user9', 'password9', 9, '学生九', 'male', '2000-09-09', '1234567898', '地址九'),
    ('user10', 'password10', 10, '学生十', 'female', '2000-10-10', '1234567899', '地址十'),
    ('user11', 'password11', 11, '学生十一', 'male', '2001-01-11', '1234567810', '地址十一'),
    ('user12', 'password12', 12, '学生十二', 'female', '2001-02-12', '1234567811', '地址十二'),
    ('user13', 'password13', 13, '学生十三', 'male', '2001-03-13', '1234567812', '地址十三'),
    ('user14', 'password14', 14, '学生十四', 'female', '2001-04-14', '1234567813', '地址十四'),
    ('user15', 'password15', 15, '学生十五', 'male', '2001-05-15', '1234567814', '地址十五'),
    ('user16', 'password16', 16, '学生十六', 'female', '2001-06-16', '1234567815', '地址十六'),
    ('user17', 'password17', 17, '学生十七', 'male', '2001-07-17', '1234567816', '地址十七'),
    ('user18', 'password18', 18, '学生十八', 'female', '2001-08-18', '1234567817', '地址十八'),
    ('user19', 'password19', 19, '学生十九', 'male', '2001-09-19', '1234567818', '地址十九'),
    ('user20', 'password20', 20, '学生二十', 'female', '2001-10-20', '1234567819', '地址二十');

-- 插入 TeacherInfo 表数据
INSERT INTO teacher_info (username, password, teacher_name, sex) VALUES
    ('teacher1', 'password1', '教师一', 'male'),
    ('teacher2', 'password2', '教师二', 'female'),
    ('teacher3', 'password3', '教师三', 'male'),
    ('teacher4', 'password4', '教师四', 'female'),
    ('teacher5', 'password5', '教师五', 'male'),
    ('teacher6', 'password6', '教师六', 'female'),
    ('teacher7', 'password7', '教师七', 'male'),
    ('teacher8', 'password8', '教师八', 'female'),
    ('teacher9', 'password9', '教师九', 'male'),
    ('teacher10', 'password10', '教师十', 'female'),
    ('teacher11', 'password11', '教师十一', 'male'),
    ('teacher12', 'password12', '教师十二', 'female'),
    ('teacher13', 'password13', '教师十三', 'male'),
    ('teacher14', 'password14', '教师十四', 'female'),
    ('teacher15', 'password15', '教师十五', 'male'),
    ('teacher16', 'password16', '教师十六', 'female'),
    ('teacher17', 'password17', '教师十七', 'male'),
    ('teacher18', 'password18', '教师十八', 'female'),
    ('teacher19', 'password19', '教师十九', 'male'),
    ('teacher20', 'password20', '教师二十', 'female');

-- 插入 CourseInfo 表数据
INSERT INTO course_info (course_no, course_name, teacher_id) VALUES
    ('C101', '数学', 1),
    ('C102', '英语', 2),
    ('C103', '物理', 3),
    ('C104', '化学', 4),
    ('C105', '生物', 5),
    ('C106', '历史', 6),
    ('C107', '地理', 7),
    ('C108', '政治', 8),
    ('C109', '体育', 9),
    ('C110', '音乐', 10),
    ('C111', '美术', 11),
    ('C112', '信息技术', 12),
    ('C113', '心理学', 13),
    ('C114', '哲学', 14),
    ('C115', '经济学', 15),
    ('C116', '法学', 16),
    ('C117', '社会学', 17),
    ('C118', '管理学', 18),
    ('C119', '教育学', 19),
    ('C120', '文学', 20);

-- 插入 StudentCourse 表数据
INSERT INTO student_course (student_id, course_id, score) VALUES
    (1, 1, 85),
    (2, 1, 90),
    (1, 2, 88),
    (2, 2, 92),
    (3, 3, 75),
    (4, 3, 80),
    (3, 4, 82),
    (4, 4, 78),
    (5, 5, 95),
    (6, 5, 89),
    (5, 6, 87),
    (6, 6, 93),
    (7, 7, 85),
    (8, 7, 88),
    (7, 8, 90),
    (8, 8, 92),
    (9, 9, 74),
    (10, 9, 79),
    (9, 10, 85),
    (10, 10, 88),
    (11, 11, 92),
    (12, 11, 95),
    (11, 12, 87),
    (12, 12, 89),
    (13, 13, 78),
    (14, 13, 82),
    (13, 14, 84),
    (14, 14, 86),
    (15, 15, 90),
    (16, 15, 93),
    (15, 16, 85),
    (16, 16, 88),
    (17, 17, 92),
    (18, 17, 94),
    (17, 18, 89),
    (18, 18, 91),
    (19, 19, 75),
    (20, 19, 78),
    (19, 20, 82),
    (20, 20, 85);

INSERT INTO admin_info (username, password) VALUES
    ('admin', '123456');