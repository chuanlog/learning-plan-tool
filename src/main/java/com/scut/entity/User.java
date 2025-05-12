package com.scut.entity;

import lombok.*;

/**
 * 用户实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    Long id;// 用户id
    String username;// 用户名
    String password;// 密码
}
