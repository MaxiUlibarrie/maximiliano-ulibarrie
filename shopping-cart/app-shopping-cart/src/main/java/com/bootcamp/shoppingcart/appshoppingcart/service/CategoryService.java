package com.bootcamp.shoppingcart.appshoppingcart.service;

import com.bootcamp.shoppingcart.appshoppingcart.model.Category;
import java.util.List;

public interface CategoryService {

  List<Category> getAllCategories();
  Category getCategoryById(Long idcategory);
  Category createCategory(Category category);
  void deleteCategory(Long idcategory);
}
