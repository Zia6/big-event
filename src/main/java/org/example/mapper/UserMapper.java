package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.User;


@Mapper
public interface UserMapper {

    //查询用户名是否存在
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);


    @Insert("insert into user(username,password,create_time,update_time)" +
            "values(#{username},#{md5Password},now(),now())")
    void add(String username, String md5Password);

    @Update("update user set nickname=#{nickname},email=#{email},user_pic=#{userPic},update_time=now() where id=#{id}")
    void update(User user);

    @Update("update user set user_pic=#{avatarUrl},update_time=now()  where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);


    @Update("update user set password=#{md5Password},update_time=now() where id=#{id}")
    void updatePwd(String md5Password, Integer id);
}
