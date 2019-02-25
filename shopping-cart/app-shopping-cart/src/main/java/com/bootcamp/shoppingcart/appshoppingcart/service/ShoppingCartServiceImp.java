package com.bootcamp.shoppingcart.appshoppingcart.service;

import com.bootcamp.shoppingcart.appshoppingcart.exception.CartCheckedOutException;
import com.bootcamp.shoppingcart.appshoppingcart.exception.NotFoundException;
import com.bootcamp.shoppingcart.appshoppingcart.model.Cart;
import com.bootcamp.shoppingcart.appshoppingcart.model.CartItem;
import com.bootcamp.shoppingcart.appshoppingcart.model.LineSale;
import com.bootcamp.shoppingcart.appshoppingcart.model.Product;
import com.bootcamp.shoppingcart.appshoppingcart.model.Sale;
import com.bootcamp.shoppingcart.appshoppingcart.model.User;
import com.bootcamp.shoppingcart.appshoppingcart.repository.CartItemRepository;
import com.bootcamp.shoppingcart.appshoppingcart.repository.CartRepository;
import com.bootcamp.shoppingcart.appshoppingcart.repository.SaleRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService {

  @Autowired
  private CartRepository cartRepo;

  @Autowired
  private CartItemRepository cartItemRepo;

  @Autowired
  private SaleRepository saleRepo;

  @Autowired
  private ProductService productService;

  @Autowired
  private UserService userService;

  private final String NAME_CART = "Cart";

  @Override
  public Cart createCart(String username) {
    User user = userService.getUserByUsername(username);
    Cart newCart = new Cart(user);
    user.getCartList().add(newCart);

    return cartRepo.save(newCart);
  }

  @Override
  public Cart getCartById(Long idcart) {
    return cartRepo.findById(idcart)
        .orElseThrow(() -> new NotFoundException(NAME_CART,idcart));
  }

  @Override
  public void deleteCart(Long idcart) {
    if (!cartRepo.existsById(idcart)) throw new NotFoundException(NAME_CART,idcart);
    cartRepo.deleteById(idcart);
  }

  @Override
  public List<CartItem> getAllCartItems(Long idcart) {
    return getCartById(idcart).getCartItemList();
  }

  @Override
  public void addToCart(Long idcart, Long idproduct, Integer quantity) {
    Cart cart = getCartById(idcart);
    Product product = productService.getProductById(idproduct);

    if (cart.existsProduct(idproduct)) {
      cart.getOneCartItem(idproduct).incrementQuantity(quantity);
    } else {
      cart.getCartItemList().add(new CartItem(cart,product,quantity));
    }
    cartRepo.save(cart);
  }

  @Override
  public void removeProduct(Long idcart, Long idproduct, Integer quantity) {
    Cart cart = getCartById(idcart);
    cart.getOneCartItem(idproduct).decrementQuantity(quantity);
    cartRepo.save(cart);
  }

  @Override
  public void deleteProduct(Long idcart, Long idproduct) {
    Cart cart = getCartById(idcart);
    Long idCartItem = cart.getOneCartItem(idproduct).getIdcartitem();
    cart.deleteProduct(idproduct);
    cartItemRepo.deleteById(idCartItem);
  }

  @Override
  public void clearCart(Long idcart) {
    Cart cart = getCartById(idcart);
    cart.getCartItemList().clear();
    cartItemRepo.deleteByCart(cart);
  }

  @Override
  public Sale doCheckOut(Long idcart) {
    Cart cart = getCartById(idcart);
    if (cart.isCheckedOut()) throw new CartCheckedOutException(idcart);
    Sale sale = new Sale(cart.getUser(), LocalDateTime.now());

    for (CartItem cartItem : cart.getCartItemList()) {
      LineSale lineSale = new LineSale(sale,cartItem.getProduct(),cartItem.getQuantity());
      sale.getLineSaleList().add(lineSale);
    }
    sale.calculateTotalPrice();
    saleRepo.save(sale);
    cart.setCheckedOut(true);
    cartRepo.save(cart);

    return sale;
  }
}