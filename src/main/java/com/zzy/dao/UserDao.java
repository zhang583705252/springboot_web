package com.zzy.dao;

import com.zzy.entity.User;

public interface UserDao {
    User findById(Integer id);
}
