package com.bootcamp.shoppingcart.appshoppingcart.service;

import com.bootcamp.shoppingcart.appshoppingcart.exception.NotFoundException;
import com.bootcamp.shoppingcart.appshoppingcart.exception.RepeatedEntityException;
import com.bootcamp.shoppingcart.appshoppingcart.model.Category;
import com.bootcamp.shoppingcart.appshoppingcart.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepo;

  private final String NAME_CATEGORY = "Category";

  @Override
  public List<Category> getAllCategories() {
    return categoryRepo.findAll();
  }

  @Override
  public Category getCategoryById(Long idcategory) {
    return categoryRepo.findById(idcategory)
        .orElseThrow(() -> new NotFoundException(NAME_CATEGORY,idcategory));
  }

  @Override
  public Category createCategory(Category category) {
    if (categoryRepo.exists(Example.of(category)))
      throw new RepeatedEntityException(NAME_CATEGORY);

    return categoryRepo.save(category);
  }

  @Override
  public void deleteCategory(Long idcategory) {
    if (!categoryRepo.existsById(idcategory))
      throw new NotFoundException(NAME_CATEGORY,idcategory);

    categoryRepo.deleteById(idcategory);
  }
}
