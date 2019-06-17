package com.example.dynamicdatasource.service;

import com.example.dynamicdatasource.empty.UserDO;

public interface UserService {

    void add(UserDO userModel);

    UserDO query(Integer id);
}
