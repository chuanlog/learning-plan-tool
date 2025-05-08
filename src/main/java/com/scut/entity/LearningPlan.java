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
}
