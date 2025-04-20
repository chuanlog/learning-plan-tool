package com.scut.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 先修课程实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CoursePrerequisite {
    private Long courseId;          // 当前课程ID（对应表字段 course_id）
    private Long prerequisiteId;    // 先修课程ID（对应表字段 prerequisite_id）
}
