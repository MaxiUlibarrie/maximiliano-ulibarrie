package com.bootcamp.shoppingcart.appshoppingcart.service;

import com.bootcamp.shoppingcart.appshoppingcart.exception.CartCheckedOutException;
import com.bootcamp.shoppingcart.appshoppingcart.exception.NotFoundException;
import com.bootcamp.shoppingcart.appshoppingcart.exception.NotFoundProductInCart;
import com.bootcamp.shoppingcart.appshoppingcart.model.Cart;
import com.bootcamp.shoppingcart.appshoppingcart.model.CartItem;
import com.bootcamp.shoppingcart.appshoppingcart.model.LineSale;
import com.bootcamp.shoppingcart.appshoppingcart.model.Product;
import com.bootcamp.shoppingcart.appshoppingcart.model.Sale;
import com.bootcamp.shoppingcart.appshoppingcart.model.User;
import com.bootcamp.shoppingcart.appshoppingcart.repository.CartItemRepository;
import com.bootcamp.shoppingcart.appshoppingcart.repository.CartRepository;
import com.bootcamp.shoppingcart.appshoppingcart.repository.ProductRepository;
import com.bootcamp.shoppingcart.appshoppingcart.repository.SaleRepository;
import com.bootcamp.shoppingcart.appshoppingcart.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("shoppingCartService")
public class ShoppingCartServiceImp implements ShoppingCartService {

  private final String PRODUCT_NAME = "Product";
  private final String CART_NAME = "Cart";
  private final String USER_NAME = "User";


  @Autowired
  private UserRepository userRepo;

  @Autowired
  private CartRepository cartRepo;

  @Autowired
  private CartItemRepository cartItemRepo;

  @Autowired
  private ProductRepository productRepo;

  @Autowired
  private SaleRepository saleRepo;

  @Override
  public Cart createCart(Long iduser) {
    if (!userRepo.existsById(iduser)) throw new NotFoundException(USER_NAME,iduser);

    User user = userRepo.findById(iduser).get();
    Cart newCart = new Cart(user);
    user.getCartList().add(newCart);
    userRepo.save(user);

    return user.lastCart();
  }

  @Override
  public void deleteCart(Long idcart) {
    if (!cartRepo.existsById(idcart)) throw new NotFoundException(CART_NAME,idcart);

    cartRepo.deleteById(idcart);
  }

  @Override
  public List<CartItem> getAllCartItems(Long idcart) {
    if (!cartRepo.existsById(idcart)) throw new NotFoundException(CART_NAME,idcart);

    return cartRepo.findById(idcart).get().getCartItemList();
  }

  @Override
  public void addToCart(Long idcart, Long idproduct, Integer quantity) {
    if (!cartRepo.existsById(idcart)) throw new NotFoundException(CART_NAME,idcart);
    if (!productRepo.existsById(idproduct)) throw new NotFoundException(PRODUCT_NAME,idproduct);

    Cart cart = cartRepo.findById(idcart).get();
    Product product = productRepo.findById(idproduct).get();

    if (cart.existsProduct(idproduct)) {
      cart.getOneCartItem(idproduct).incrementQuantity(quantity);
    } else {
      CartItem cartItem = new CartItem(product,quantity);
      cart.getCartItemList().add(cartItem);
    }
    cartRepo.save(cart);
  }

  @Override
  public void removeProduct(Long idcart, Long idproduct, Integer quantity) {
    if (!cartRepo.existsById(idcart)) throw new NotFoundException(CART_NAME,idcart);
    if (!productRepo.existsById(idproduct)) throw new NotFoundException(PRODUCT_NAME,idproduct);

    Cart cart = cartRepo.findById(idcart).get();

    if (!cart.existsProduct(idproduct)) throw new NotFoundProductInCart(idproduct);

    CartItem cartItem = cart.getOneCartItem(idproduct);
    cartItem.decrementQuantity(quantity);

    if (cartItem.getQuantity() == 0) {
      deleteProduct(idcart,idproduct);
    }

    cartRepo.save(cart);
  }

  @Override
  public void deleteProduct(Long idcart, Long idproduct) {
    if (!cartRepo.existsById(idcart)) throw new NotFoundException(CART_NAME,idcart);
    if (!productRepo.existsById(idproduct)) throw new NotFoundException(PRODUCT_NAME,idproduct);

    Cart cart = cartRepo.findById(idcart).get();

    if (!cart.existsProduct(idproduct)) throw new NotFoundProductInCart(idproduct);

    Long idCartItem = cart.getOneCartItem(idproduct).getIdcartitem();
    cart.deleteProduct(idproduct);
    cartItemRepo.deleteById(idCartItem);
  }

  @Override
  public void clearCart(Long idcart) {
    if (!cartRepo.existsById(idcart)) throw new NotFoundException(CART_NAME,idcart);

    Cart cart = cartRepo.findById(idcart).get();
    List<Long> idCartItems = new ArrayList<>();
    cart.getCartItemList().forEach(cartItem -> { idCartItems.add(cartItem.getIdcartitem()); });
    cart.getCartItemList().clear();
    idCartItems.forEach(cartItemRepo::deleteById);
  }

  @Override
  public Sale doCheckOut(Long idcart) {
    if (!cartRepo.existsById(idcart)) throw new NotFoundException(CART_NAME,idcart);

    Cart cart = cartRepo.findById(idcart).get();
    if (cart.isCheckedOut()) throw new CartCheckedOutException(idcart);

    Sale sale = new Sale(LocalDateTime.now());
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
