package org.example.mapper;


import org.apache.ibatis.annotations.*;
import org.example.pojo.Role;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface RoleMapper {
    @Insert("insert into role(name,introduction,cover_img,state,category_id,create_user,create_time,update_time) " +
            "values(#{name},#{introduction},#{coverImg},#{state},#{categoryId},#{createUser},now(),now())")
    void addRole(Role role);


    List<Role> list(Integer userId, Integer categoryId, String state);

    @Delete("delete from role where id = #{id}")
    void deleteRole(Integer id);

    @Select("select * from role where id = #{id}")
    Role detail(Integer id);

    @Update("update role set name=#{name},introduction=#{introduction},cover_img=#{coverImg},state=#{state},category_id=#{categoryId},update_time=now() where id=#{id}")
    void updateRole(Role role);
}
