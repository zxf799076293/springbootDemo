package com.example.springboot.snow.service;

import com.example.springboot.snow.dao.UserInfoMapper;
import com.example.springboot.snow.mapper.UserMapper;
import com.example.springboot.snow.mapper.UsersMapper;
import com.example.springboot.snow.model.User;
import com.example.springboot.snow.model.Users;
import com.example.springboot.snow.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UsersMapper usersMapper;
    public void createUser(String tel,String pwd) {
        userInfoMapper.createUser(tel,pwd);
    }

    public UserInfo getUser(Integer id) {
        return userInfoMapper.getUser(id);
    }

    public void updateUser(String user_id, String nickName) {
        userInfoMapper.updateUser(user_id,nickName);
    }

    public void deleteUserByUserId(Integer id) {
        userInfoMapper.deleteUserByUserId(id);
    }
    public User getUserInfo(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
    //用户登录
    public User userLogin(String username,String password){
        User user = userMapper.userlogin(username,password);
        return user;
    }
    //用户登录
    public List<Users> getUserList(){
        List<Users> users = usersMapper.getUserList();
        return users;
    }
    //用户注册
    public int setUsers(Users users) {
        return usersMapper.insertSelective(users);
    }
    //用户id
    public Users getUsersModel(String id)  throws Exception {
        return usersMapper.selectByPrimaryKey(Integer.parseInt(id));
    }
    //用户id
    public Users findByUsername(Users users)  throws Exception {
        return usersMapper.selectUser(users);
    }
    //更新用户
    public void updateUser(Users users) {
        usersMapper.updateByPrimaryKeySelective(users);
    }
    //获取用户count
    public int selectUserCount(String name) {
        return usersMapper.userCount(name);
    }

}
