package com.viifly.wba.admin.dao;

import com.viifly.wba.admin.User;

import java.util.List;

public interface UserDao {
    User getUserByLoginId(String loginId);
    int addUser(User user);
    List<User> findUsers();
}