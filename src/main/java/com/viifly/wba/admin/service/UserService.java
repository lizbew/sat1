package com.viifly.wba.admin.service;

import com.viifly.wba.admin.User;

import java.util.List;

public interface UserService {
    User findUser(String loginId);
    void addUser(User user);
    List<User> findAllUsers();
}
