package com.deep.shiroboot.service;


import com.deep.shiroboot.model.User;

public interface UserService {

    User findByUsername(String username);
}
