DROP DATABASE IF EXISTS learning_plan_tool;
CREATE DATABASE IF NOT EXISTS `learning_plan_tool`;
USE learning_plan_tool;
-- 建立课程表
CREATE TABLE course
(
    id          bigint AUTO_INCREMENT COMMENT '课程ID（主键）'
        PRIMARY KEY,
    course_name varchar(255) NOT NULL COMMENT '课程名称',
    credits     int          NOT NULL COMMENT '学分',
    total_hours int          NOT NULL COMMENT '总课时',
    course_type int          NOT NULL COMMENT '课程类型（如选修、必修等）',
    user_id     bigint       NOT NULL COMMENT '所属用户ID（需应用层保证关联性）'
)
    COMMENT '课程信息表' COLLATE = utf8mb4_general_ci;

CREATE INDEX idx_user_id
    ON course (user_id);
-- 建立先修关系表
CREATE TABLE course_prerequisite
(
    course_id       int NOT NULL COMMENT '当前课程ID',
    prerequisite_id int NOT NULL COMMENT '先修课程ID',
    PRIMARY KEY (course_id, prerequisite_id)
);

CREATE INDEX idx_course_id
    ON course_prerequisite (course_id);

CREATE INDEX idx_prerequisite_id
    ON course_prerequisite (prerequisite_id);
-- 建立用户表
CREATE TABLE user
(
    id       bigint AUTO_INCREMENT COMMENT '主键'
        PRIMARY KEY,
    username varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT '用户名',
    password varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT '密码',
    CONSTRAINT username
        UNIQUE (username)
);
-- 插入初始用户
INSERT INTO user (username, password)
VALUES ('admin', 'e10adc3949ba59abbe56e057f20f883e');