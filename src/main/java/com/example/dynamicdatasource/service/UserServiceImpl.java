package com.example.dynamicdatasource.service;

import com.example.dynamicdatasource.dao.UserDOMapper;
import com.example.dynamicdatasource.empty.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDOMapper userDOMapper;

    @Override
    public void add(UserDO user) {
        // 操作数据库
        userDOMapper.insertSelective(user);
        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        System.out.println(userDO.toString());
//       userDOMapper.insertSelective(user);
        userDOMapper.deleteByPrimaryKey(1);
    }

    @Override
    public UserDO query(Integer id) {
        return userDOMapper.selectByPrimaryKey(id);
    }


}
