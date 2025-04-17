package com.scut.mapper;

import com.scut.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 添加用户
     * @param user
     */
    void add(User user);
}
