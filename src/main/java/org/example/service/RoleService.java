package org.example.service;

import org.example.pojo.PageBean;
import org.example.pojo.Role;

public interface RoleService {
    void addRole(Role role);

    PageBean<Role> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    void deleteRole(Integer id);

    Role detail(Integer id);

    void updateRole(Role role);
}
