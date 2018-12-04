package com.example.springboot.snow.api;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.snow.annotation.PassToken;
import com.example.springboot.snow.annotation.UserLoginToken;
import com.example.springboot.snow.core.JsonResult;
import com.example.springboot.snow.core.ResultCode;
import com.example.springboot.snow.model.Users;
import com.example.springboot.snow.service.TokenService;
import com.example.springboot.snow.service.UserService;
import com.example.springboot.snow.util.SymmetricEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("api")
public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    //登录
    @PostMapping("/login")
    @PassToken
    public Object login(@Valid Users user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new JsonResult(ResultCode.INTERNAL_SERVER_ERROR.code(),bindingResult.getFieldError().getDefaultMessage(),null);
        }
        JSONObject jsonObject=new JSONObject();
        Users userForBase= null;
        try {
            userForBase = userService.findByUsername(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(userForBase==null){
            new JsonResult(ResultCode.ACCOUNT_PW_NOT_FOUND.code(),
                    ResultCode.ACCOUNT_PW_NOT_FOUND.message(),null);
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!SymmetricEncoder.AESDncode(userForBase.getName(),userForBase.getPassword()).equals(user.getPassword())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = null;
                try {
                    token = tokenService.getToken(20000,userForBase);
                    userForBase.setRememberToken(token);
                    userService.updateUser(userForBase);
                    return JsonResult.success(userForBase);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return jsonObject;
            }
        }
    }
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
