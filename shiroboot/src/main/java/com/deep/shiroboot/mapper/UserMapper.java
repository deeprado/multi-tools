package com.deep.shiroboot.mapper;

import com.deep.shiroboot.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User findByUsername(@Param("username") String username);
}
