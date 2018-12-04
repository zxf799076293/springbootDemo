package com.example.springboot.snow.dao;

import com.example.springboot.snow.pojo.UserInfo;

public interface UserInfoMapper {

    void createUser(String tel, String pwd);

    UserInfo getUser(Integer id);

    void updateUser(String user_id, String nickName);

    void deleteUserByUserId(Integer id);
}
