package com.scut.dto;

import lombok.*;

/**
 * 用户的数据传输对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDTO {
    private String username;
    private String password;
}
