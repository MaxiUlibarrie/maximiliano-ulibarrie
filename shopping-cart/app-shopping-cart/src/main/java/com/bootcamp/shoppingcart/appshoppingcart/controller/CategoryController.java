package com.bootcamp.shoppingcart.appshoppingcart.controller;

import com.bootcamp.shoppingcart.appshoppingcart.model.Category;
import com.bootcamp.shoppingcart.appshoppingcart.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Category> getAllCategories() {
    return categoryService.getAllCategories();
  }

  @GetMapping("/{idcategory}")
  @ResponseStatus(HttpStatus.OK)
  public Category getCategoryById(@PathVariable Long idcategory) {
    return categoryService.getCategoryById(idcategory);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createCategory(@RequestBody Category category) {
    categoryService.createCategory(category);
  }

  @DeleteMapping("/{idcategory}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteCategory(@PathVariable Long idcategory) {
    categoryService.deleteCategory(idcategory);
  }
}