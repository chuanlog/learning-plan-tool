package com.scut.entity;

import lombok.*;

import java.util.List;

/**
 * 培养计划类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LearningPlan {
    List<Semester> AllSemesters;//全部8个学期
    Integer totalCredits;//总必修课学分
    Integer ElectiveScore;//选修课的总学分
}
