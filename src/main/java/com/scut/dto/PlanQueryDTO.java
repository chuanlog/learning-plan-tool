package com.scut.dto;

import lombok.*;

/**
 * 培养计划查询的数据传输对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PlanQueryDTO {
    private Long userId;// 用户id
    private Integer creditsOfProfessionalElectiveCourses;// 专业选修课学分要求
    private Integer creditsOfCommonElectiveCourses; // 公共选修课学分要求
}
