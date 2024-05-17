package org.example.controller;

import jakarta.validation.constraints.Pattern;
import org.example.service.UserService;
import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.utils.JwtUtil;
import org.example.utils.Md5Util;
import org.example.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {

        //查询用户名是否存在
        User u = userService.findByUsername(username);
        if(u == null){
            //注册用户
            userService.register(username, password);
            return Result.success();
        }
        //注册用户失败
        return Result.fail("用户名已存在");
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User LoginUser = userService.findByUsername(username);
        if(LoginUser == null){
            return Result.fail("用户名不存在");
        }

        //判断密码是否正确
        if(LoginUser.getPassword().equals(Md5Util.getMD5String(password))){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",LoginUser.getId());
            claims.put("username",LoginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.fail("密码错误");
    }

    @GetMapping("userInfo")
    public Result<User> userinfo(){
//        Map<String, Object> claims = JwtUtil.parseToken(token);
//        String username =  (String)claims.get("username");
//        User user = userService.findByUsername(username);
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user ){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){

        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //校验参数
        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(rePwd)){
            return Result.fail("参数不完整");
        }
        //原密码是否正确
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        if(!user.getPassword().equals(Md5Util.getMD5String(old_pwd))){
            return Result.fail("原密码不正确");
        }

        if(!new_pwd.equals(rePwd)){
            return Result.fail("两次密码不一致");
        }

        userService.updatePwd(new_pwd);
        return Result.success();
    }
}
