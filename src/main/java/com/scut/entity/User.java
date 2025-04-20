package com.scut.entity;

import lombok.*;

/**
 * 用户实体类，对应数据库中用户表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    Long id;
    String username;
    String password;
}
