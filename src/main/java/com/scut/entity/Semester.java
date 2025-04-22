package com.scut.entity;

import lombok.*;

import java.util.List;

/**
 * 学期类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Semester {
    List<Course>MustCourses;//该学期的必修课程
    List<Course>ElectiveCourses;//该学期的选修课程
    List<Course>RecommendCourses;// 该学期的推荐课程(通选课等)
    Integer TotalMustScore;//该学期必修课的总学分
    Integer ElectiveScore;//选修课的总学分
    Integer RecommendScore;//推荐在这个学期修的选修课的总学分
}
