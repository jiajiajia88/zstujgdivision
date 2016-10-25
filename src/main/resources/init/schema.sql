/* **** 专业大类表 **** */
DROP TABLE IF EXISTS tb_species;
CREATE TABLE tb_species
(
  species_id INT NOT NULL COMMENT '大类id',
  species_name VARCHAR(50) NOT NULL COMMENT '大类名',
  stu_amount INT COMMENT '学生人数',
  status TINYINT DEFAULT 0 COMMENT '状态',
  PRIMARY KEY(species_id)
)Engine=INNODB DEFAULT CHARSET=utf8;

/* **** 教师信息表 **** */
DROP TABLE IF EXISTS tb_teacher_info;
CREATE TABLE tb_teacher_info
(
  id INT NOT NULL auto_increment COMMENT '主键id',
  number VARCHAR(13) COMMENT '学工号',
  name VARCHAR(30) NOT NULL COMMENT '名字',
  position_id INT COMMENT '职位id',
  PRIMARY KEY(id)
)Engine=INNODB DEFAULT charset=utf8 auto_increment=1;

/* **** 学生信息表 **** */
DROP TABLE IF EXISTS tb_student_info;
CREATE TABLE tb_student_info
(
  id INT NOT NULL auto_increment COMMENT '主键id',
  number LONG NOT null COMMENT '学号',
  name VARCHAR(50) NOT NULL COMMENT '姓名',
  telephone CHAR(11) DEFAULT NULL COMMENT '手机号（长号）',
  species INTEGER COMMENT '所属大类',
  original_class VARCHAR(30) NOT NULL COMMENT '原班级',
  present_class VARCHAR(30) DEFAULT NULL COMMENT '现班级',
  GPA DOUBLE COMMENT '平均学分绩点',
  RealGPA DOUBLE COMMENT '平均学分绩点*70%',
  stuFrom VARCHAR(10) COMMENT '生源地',
  division INT COMMENT '文1/理2',
  entranceScore INT COMMENT '高考成绩',
  admissionScore INT COMMENT '生源省高考录取线',
  gradeOne DOUBLE COMMENT '高考成绩/生源省高考录取线',
  gradeTwo DOUBLE COMMENT '30%*高考成绩/生源省高考录取线',
  totalGrade DOUBLE COMMENT '总成绩=70%*平均学分绩点 + 30%*高考成绩/生源省高考录取线',
  rank INT COMMENT '等级',
  PRIMARY KEY(id)
)Engine=INNODB DEFAULT charset=utf8 auto_increment=1;
INSERT INTO `tb_student_info` VALUES ('1', '2013333502028', '施周勇', '15858159214', 2013103, '13管理科学与工程1班', '1030101','4.22', '2.954', '浙江', '2', '575', '526', '1.093156', '0.327947', '3.281947',4);

/* **** 专业表 **** */
DROP TABLE IF EXISTS tb_major;
CREATE TABLE tb_major
(
  major_id INT NOT NULL COMMENT '专业id',
  major_name VARCHAR(50) NOT NULL COMMENT '专业名',
  status TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY(major_id)
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
  number LONG NOT NULL COMMENT '学号',
  telephone varchar(11) NOT NULL COMMENT '手机长号',
  first_major INT NOT NULL COMMENT '第一志愿',
  second_major INT NOT NULL COMMENT '第二志愿',
  third_major INT NOT NULL COMMENT '第二志愿',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1保存、2确认',
  PRIMARY KEY(id)
)Engine=INNODB DEFAULT charset=utf8 auto_increment=1;

/* **** 年级表 **** */
DROP TABLE IF EXISTS tb_grade;
CREATE TABLE tb_grade
(
  id INT NOT NULL auto_increment COMMENT '主键id',
  grade INT(4) NOT NULL COMMENT '学期',
  PRIMARY KEY(id)
)Engine=INNODB DEFAULT charset=utf8 auto_increment=1;

/* **** 职位表 **** */
DROP TABLE IF EXISTS tb_positions;
CREATE TABLE tb_positions
(
  id INT NOT NULL auto_increment COMMENT '主键id',
  description VARCHAR(20) NOT NULL COMMENT '职位',
  PRIMARY KEY(id)
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
  PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

/* **** 用户表 **** */
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `number` varchar(13) DEFAULT NULL COMMENT '学工号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO `tb_user` VALUES ('3','20061657', '123456', '2016-06-02 23:35:38');
INSERT INTO `tb_user` VALUES ('2','2013333502028', '123456', '2016-06-02 23:35:38');
INSERT INTO `tb_user` VALUES ('1','123456', '123456', '2016-06-01 23:35:17');

/* **** 角色表 **** */
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO `tb_role` VALUES ('3', 'student', '学生用户', '2016-10-21 17:41:11');
INSERT INTO `tb_role` VALUES ('2', 'teacher', '教师用户', '2016-10-21 17:41:11');
INSERT INTO `tb_role` VALUES ('1', 'admin', '系统管理员', '2016-10-21 17:41:11');

/* **** 角色模块表 **** */
DROP TABLE IF EXISTS `tb_role_module`;
CREATE TABLE `tb_role_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色模块',
  `module_id` int(11) DEFAULT NULL COMMENT '模块id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
INSERT INTO `tb_role_module` VALUES ('1', '1', '1');
INSERT INTO `tb_role_module` VALUES ('2', '1', '2');
INSERT INTO `tb_role_module` VALUES ('3', '1', '3');
INSERT INTO `tb_role_module` VALUES ('4', '1', '4');
INSERT INTO `tb_role_module` VALUES ('5', '2', '1');
INSERT INTO `tb_role_module` VALUES ('6', '2', '3');
INSERT INTO `tb_role_module` VALUES ('7', '3', '1');
INSERT INTO `tb_role_module` VALUES ('8', '3', '4');

/* **** 模块表 **** */
DROP TABLE IF EXISTS `tb_module`;
CREATE TABLE `tb_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `module_name` varchar(32) DEFAULT NULL COMMENT '模块名',
  `module_path` varchar(50) DEFAULT NULL COMMENT '模块路由',
  `module_key` varchar(32) DEFAULT NULL COMMENT '模块key',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
INSERT INTO `tb_module` VALUES ('1', '登陆权限', '/login*', 'login','2016-06-01 23:41:39');
INSERT INTO `tb_module` VALUES ('2', '系统权限', '/system/*', 'system','2016-06-01 23:41:39');
INSERT INTO `tb_module` VALUES ('3', '教师用户权限', '/teacher/*', 'teacher','2016-06-01 23:41:39');
INSERT INTO `tb_module` VALUES ('4', '学生用户权限', '/student/*', 'student','2016-06-01 23:41:39');


/* **** 用户角色表 **** */
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `tb_user_role` VALUES ('1', '1', '1');
INSERT INTO `tb_user_role` VALUES ('2', '2', '2');
INSERT INTO `tb_user_role` VALUES ('3', '3', '3');








