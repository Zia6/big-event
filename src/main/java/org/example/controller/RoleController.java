package org.example.controller;


import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.pojo.Role;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping
    public Result addRole(@RequestBody @Validated(Role.add.class) Role role){
        roleService.addRole(role);
        return Result.success();
    }
    @GetMapping
    public Result<PageBean<Role>> list(Integer pageNum,Integer pageSize,@RequestParam(required = false)Integer categoryId,@RequestParam(required = false) String state){
        PageBean<Role> pageBean = roleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pageBean);
    }

    @GetMapping("/detail")
    public Result<Role> detail(@RequestParam Integer id){
        return Result.success(roleService.detail(id));
    }
    @DeleteMapping
    public Result deleteRole(@RequestParam Integer id){
        roleService.deleteRole(id);
        return Result.success();
    }

    @PutMapping
    public Result updateRole(@RequestBody @Validated(Role.update.class) Role role){
        roleService.updateRole(role);
        return Result.success();
    }
}
