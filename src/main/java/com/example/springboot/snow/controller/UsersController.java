package com.example.springboot.snow.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.snow.annotation.PassToken;
import com.example.springboot.snow.annotation.UserLoginToken;
import com.example.springboot.snow.core.JsonResult;
import com.example.springboot.snow.core.Result;
import com.example.springboot.snow.core.ResultGenerator;
import com.example.springboot.snow.exception.BaseException;
import com.example.springboot.snow.model.User;
import com.example.springboot.snow.model.Users;
import com.example.springboot.snow.service.UserService;
import com.example.springboot.snow.util.JwtUtil;
import com.example.springboot.snow.util.MD5Util;
import com.example.springboot.snow.util.SymmetricEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users")
@RestController
public class UsersController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userjson",method = RequestMethod.GET)
    public JsonResult userList() {
        List<Users> userList = userService.getUserList();
        String a = "123456789a";
        String b = SymmetricEncoder.AESEncode(userList.get(userList.size()-1).getName(),a);
        String c = SymmetricEncoder.AESDncode(userList.get(userList.size()-1).getName(),userList.get(userList.size()-1).getPassword());
        String d = SymmetricEncoder.AESDncode(userList.get(userList.size()-1).getName(),userList.get(userList.size()-1).getPassword());
        return JsonResult.success(userList);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public JsonResult register(HttpServletRequest request) throws Exception {
        Users users = new Users();
        if (request.getParameter("username") != null) {
            if (userService.selectUserCount(request.getParameter("username")) > 0) {
                return JsonResult.successMsg("已存在该用户");
            }
            users.setName(request.getParameter("username"));
        }

        if (request.getParameter("password") != null) {
            String a = request.getParameter("password");
            String pwStr = SymmetricEncoder.AESEncode(users.getName(),a);
            String psssword = SymmetricEncoder.AESDncode(users.getName(),pwStr);
            users.setPassword(pwStr);
        }
        if (request.getParameter("email") != null) {
            users.setEmail(request.getParameter("email"));
        } else {
            throw new BaseException(Result.error,new NullPointerException());
        }
        int a;
        try {
            a = userService.setUsers(users);
        } catch (Exception e) {
            throw new BaseException(e);
        }
        return JsonResult.success(a);
    }
    //登录
    @PostMapping("/login")
    @PassToken
    public Object login(@RequestBody @Valid Users user) throws Exception {

        JSONObject jsonObject = new JSONObject();
        Users userForBase = userService.findByUsername(user);
        if (userForBase == null) {
            jsonObject.put("message", "登录失败,用户不存在");
            return jsonObject;
        } else {
            if (!SymmetricEncoder.AESDncode(userForBase.getName(),userForBase.getPassword()).equals(user.getPassword())) {
                jsonObject.put("message", "登录失败,密码错误");
                return jsonObject;
            } else {
                String token = JwtUtil.createJWT(6000000, userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    //查看个人信息

    @GetMapping("/getMessage")
    @UserLoginToken
    public String getMessage() {
        return "你已通过验证";
    }
}
