package org.example.mapper;


import org.apache.ibatis.annotations.*;
import org.example.pojo.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {


    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)" +
            "values(#{categoryName},#{categoryAlias},#{createUser},now(),now())")
    void add(Category category);


    @Select("select * from category where create_user = #{userid}")
    List<Category> list(Integer userid);


    @Select("select * from category where id = #{id}")
    Category detail(Integer id);

    @Update("update category set category_name = #{categoryName},category_alias = #{categoryAlias},update_time = now() where id = #{id}")
    void update(Category category);
    @Delete("delete from category where id = #{id}")
    void delete(Integer id);
}
