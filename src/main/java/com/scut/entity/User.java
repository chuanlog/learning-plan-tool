package com.scut.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类，对应数据库中用户表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    Long id;
    String username;
    String password;
}
