package org.example.controller;


import org.example.pojo.Category;
import org.example.pojo.Result;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody @Validated(Category.add.class) Category category){
        categoryService.add(category);
        return Result.success("添加成功");
    }

    @GetMapping
    public Result<List<Category>> list(){
        return Result.success(categoryService.list());
    }

    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        return Result.success(categoryService.detail(id));
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.update.class) Category category){
        categoryService.update(category);
        return Result.success("修改成功");
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        categoryService.delete(id);
        return Result.success("删除成功");
    }
}
