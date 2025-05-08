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
    List<Course> Courses;//该学期的推荐修读课程
    Integer totalCredits;// 该学期的总学分
    Integer totalHours;// 该学期的总学时
    Integer publicBaseCourseCredits;// 公共基础课的总学分
    Integer majorBaseCourseCredits;// 公共基础课的总学分
    Integer majorElectiveCourseCredits;// 该学期专业选修课程的总学分
    Integer commonElectiveCourseCredits;// 该学期公共选修课程的总学分
    Integer otherCourseCredits;// 该学期其他课程的总学分(体育+毕设+军训)
}
