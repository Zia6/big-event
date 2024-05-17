package org.example.service.impl;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.utils.Md5Util;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        User u = userMapper.findByUsername(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //加密处理密码
        String md5Password = Md5Util.getMD5String(password);
        userMapper.add(username, md5Password);

        //添加
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        String md5Password = Md5Util.getMD5String(newPwd);
        userMapper.updatePwd(md5Password, id);
    }
}
