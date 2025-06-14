package com.scut.entity;

import lombok.*;

/**
 * 课程实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Course {
    private Long id;// 课程id
    private String courseName; // 课程名称
    private Integer credits; // 学分
    private Integer totalHours; // 总课时
    private Integer courseType; // 课程类型
    private Long userId; // 该课程所属的用户id
}