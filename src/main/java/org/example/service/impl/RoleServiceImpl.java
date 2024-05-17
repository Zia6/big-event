package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.RoleMapper;
import org.example.pojo.PageBean;
import org.example.pojo.Role;
import org.example.service.RoleService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public void addRole(Role role) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        role.setCreateUser(userId);
        roleMapper.addRole(role);
    }

    @Override
    public PageBean<Role> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建pageBean对象
        PageBean<Role> pageBean = new PageBean<>();

        //开启分页查询Pagehelper
        PageHelper.startPage(pageNum,pageSize);
        //查询数据
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Role> list = roleMapper.list(userId,categoryId,state);
        Page<Role> p = (Page<Role>)list;

        //设置数据
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }

    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteRole(id);
    }

    @Override
    public Role detail(Integer id) {
        return roleMapper.detail(id);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }
}
