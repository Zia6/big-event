package org.example.service;

import java.util.List;
import org.example.pojo.Category;

public interface CategoryService {
    void add(Category category);
    List<Category> list();

    Category detail(Integer id);

    void update(Category category);

    void delete(Integer id);
}
