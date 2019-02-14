package com.bootcamp.topic6.apptopic6.service;

import com.bootcamp.topic6.apptopic6.model.LineSale;
import com.bootcamp.topic6.apptopic6.model.Product;
import com.bootcamp.topic6.apptopic6.model.Sale;
import com.bootcamp.topic6.apptopic6.repository.SaleRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private List<Item> itemList;
  private boolean checkedOut;

  private final SaleRepository saleRepository;

  public ShoppingCartServiceImpl(SaleRepository saleRepository) {
    this.saleRepository = saleRepository;
    this.itemList = new ArrayList<>();
    this.checkedOut = false;
  }

  @Override
  public List<Item> getAllItems() {
    return Collections.unmodifiableList(itemList);
  }

  @Override
  public boolean addToCart(Product product) {
    if (getItemByProduct(product) != null) {
      getItemByProduct(product).incrementQuantity();
      return true;
    }

    boolean asd = itemList.add(new Item(product));

    System.out.println(itemList.get(0));

    return asd;
  }

  @Override
  public Product getProductById(Long id) {
    return getItemByProductId(id).getProduct();
  }

  @Override
  public Item getItemByProductId(Long id) {
    return itemList.stream()
        .filter(item -> item.getProduct().getId() == id)
        .findAny()
        .get();
  }

  @Override
  public boolean deleteProductById(Long id) {
    return itemList
        .removeIf(item -> item.getProduct().getId() == id);
  }

  @Override
  public void clearCart() {
    itemList.clear();
  }

  @Override
  public double doCheckOut() {
    List<LineSale> lineSaleList = new ArrayList<>();
    double totalPrice = 0;
    for (Item item : itemList) {
      totalPrice += item.getProduct().getPrice() * item.getQuantity();
      LineSale lineSale = new LineSale(item.getProduct(),item.getQuantity());
      lineSaleList.add(lineSale);
    }
    Sale newSale = new Sale(lineSaleList, LocalDateTime.now());
    saleRepository.save(newSale);
    checkedOut = true;

    return totalPrice;
  }

  private Item getItemByProduct(Product product) {
    return itemList.stream()
        .filter(item -> item.getProduct().equals(product))
        .findAny()
        .map(item -> { return item; })
        .orElseGet(() -> { return null; });
  }
}
