package com.viifly.wba.admin.service;

import com.viifly.wba.admin.User;
import com.viifly.wba.admin.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUser(String loginId) {
        return userDao.getUserByLoginId(loginId);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findUsers();
    }
}
