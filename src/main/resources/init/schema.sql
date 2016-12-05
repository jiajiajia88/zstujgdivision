/* **** 用户表 **** */
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `number` varchar(13) DEFAULT NULL COMMENT '学工号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `limit` INT NOT NULL COMMENT '用户权限，1为登录权限，2为系统管理员权限，4为教师用户权限，8为学生用户权限，3为1+2的权限',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY number(number)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
INSERT INTO `tb_user` VALUES ('3','2013333502028', '123456', 9 , NOW());
INSERT INTO `tb_user` VALUES ('2','20061657', '123456', 5, NOW());
INSERT INTO `tb_user` VALUES ('1','123456', '123456', 3, NOW());

/* **** 专业大类表 **** */
DROP TABLE IF EXISTS tb_species;
CREATE TABLE tb_species
(
  species_id INT NOT NULL COMMENT '大类id',
  species_name VARCHAR(50) NOT NULL COMMENT '大类名',
  stu_amount INT COMMENT '学生人数',
  status TINYINT DEFAULT 0 COMMENT '状态',
  PRIMARY KEY(species_id),
  UNIQUE KEY species_name(species_name)
)Engine=INNODB DEFAULT CHARSET=utf8;

/* **** 教师信息表 **** */
DROP TABLE IF EXISTS tb_teacher_info;
CREATE TABLE tb_teacher_info
(
  id INT NOT NULL auto_increment COMMENT '主键id',
  number CHAR(8) COMMENT '学工号',
  name VARCHAR(30) NOT NULL COMMENT '名字',
  position_id INT COMMENT '职位id',
  PRIMARY KEY(id),
  UNIQUE KEY number(number)
)Engine=INNODB DEFAULT charset=utf8 auto_increment=1;

/* **** 学生信息表 **** */
DROP TABLE IF EXISTS tb_student_info;
CREATE TABLE tb_student_info
(
  id INT NOT NULL auto_increment COMMENT '主键id',
  number CHAR(13) NOT NULL COMMENT '学号',
  name VARCHAR(50) COMMENT '姓名',
  telephone CHAR(11) COMMENT '手机号（长号）',
  species INTEGER DEFAULT 0 COMMENT '所属大类',
  original_class VARCHAR(30) COMMENT '原班级',
  present_class INT COMMENT '现班级',
  sex INT COMMENT '男1/女2',
  dorm VARCHAR(20) COMMENT '寝室',
  note VARCHAR(30) COMMENT '备注',
  GPA DOUBLE COMMENT '平均学分绩点',
  RealGPA DOUBLE COMMENT '平均学分绩点*70%',
  stuFrom VARCHAR(10) COMMENT '生源地',
  division INT COMMENT '文1/理2',
  entranceScore INT COMMENT '高考成绩',
  admissionScore INT COMMENT '生源省高考录取线',
  gradeOne DOUBLE COMMENT '高考成绩/生源省高考录取线',
  gradeTwo DOUBLE COMMENT '30%*高考成绩/生源省高考录取线',
  totalGrade DOUBLE COMMENT '总成绩=70%*平均学分绩点 + 30%*高考成绩/生源省高考录取线',
  rank INT COMMENT '排名',
  PRIMARY KEY(id),
  UNIQUE KEY number(number)
)Engine=INNODB DEFAULT charset=utf8 auto_increment=1;
INSERT INTO `tb_student_info` VALUES
  ('1', '2013333502028', '施周勇', '15858159214', 2013103, '13管理科学与工程1班', '1030101',1,'B2323',
        '没有','4.22', '2.954', '浙江', '2', '575', '526', '1.093156', '0.327947', '3.281947',4);

/* **** 专业表 **** */
DROP TABLE IF EXISTS tb_major;
CREATE TABLE tb_major
(
  major_id INT NOT NULL COMMENT '专业id',
  major_name VARCHAR(50) NOT NULL COMMENT '专业名',
  status TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY(major_id),
  UNIQUE KEY major_name(major_name)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

/* **** 计划专业表 **** */
DROP TABLE IF EXISTS tb_plan_major;
CREATE TABLE tb_plan_major
(
  id int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  plan_id INT NOT NULL COMMENT '计划id',
  major_id INT NOT NULL COMMENT '专业id',
  stu_number INT NOT NULL COMMENT '学生人数',
  class_number INT NOT NULL COMMENT '班级数',
  PRIMARY KEY(id)
)Engine=INNODB DEFAULT charset=utf8 auto_increment=1;

/* **** 志愿填报表 **** */
DROP TABLE IF EXISTS tb_intent_fill;
CREATE TABLE tb_intent_fill
(
  id INT NOT NULL auto_increment COMMENT '主键id',
  name VARCHAR(50) NOT NULL COMMENT '姓名',
  classes VARCHAR(20) not NULL COMMENT '班级',
  number CHAR(13) NOT NULL COMMENT '学号',
  telephone varchar(11) NOT NULL COMMENT '手机长号',
  first_major INT NOT NULL COMMENT '第一志愿',
  second_major INT NOT NULL COMMENT '第二志愿',
  third_major INT NOT NULL COMMENT '第二志愿',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1保存、2确认',
  PRIMARY KEY(id),
  UNIQUE KEY number(number)
)Engine=INNODB DEFAULT charset=utf8 auto_increment=1;

/* **** 年级表 **** */
DROP TABLE IF EXISTS tb_grade;
CREATE TABLE tb_grade
(
  id INT NOT NULL auto_increment COMMENT '主键id',
  grade INT(4) NOT NULL COMMENT '学期',
  PRIMARY KEY(id),
  UNIQUE KEY grade(grade)
)Engine=INNODB DEFAULT charset=utf8 auto_increment=1;

/* **** 职位表 **** */
DROP TABLE IF EXISTS tb_positions;
CREATE TABLE tb_positions
(
  id INT NOT NULL auto_increment COMMENT '主键id',
  description VARCHAR(20) NOT NULL COMMENT '职位',
  PRIMARY KEY(id),
  UNIQUE KEY description(description)
)ENGINE=INNODB DEFAULT CHARSET=utf8 auto_increment=1;

/* **** 分流计划表 **** */
DROP TABLE IF EXISTS tb_plan;
CREATE TABLE tb_plan
(
  id INT NOT NULL COMMENT '主键id',
  grade INT(4) COMMENT '年级',
  species INT(3) COMMENT '大类（id）',
  amount_student INT COMMENT '人数',
  amount_major INT COMMENT '专业数',
  status TINYINT COMMENt '状态',
  PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
# INSERT INTO tb_plan(id,grade,species,amount_student,amount_major, status) VALUES (2013103,2013,103, 92, 2, 1);

/* **** 系统日志表 **** */
DROP TABLE IF EXISTS tb_system_log;
CREATE TABLE tb_system_log
(
  id INT NOT NULL COMMENT '主键id',
  create_time TIMESTAMP NOT NULL COMMENT '操作时间',
  user INT NOT NULL COMMENT '操作用户',
  description VARCHAR(50) COMMENT '操作描述',
  status TINYINT COMMENT '状态',
  PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;










