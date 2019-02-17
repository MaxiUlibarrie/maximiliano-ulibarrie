package com.bootcamp.topic6.apptopic6.service;

import com.bootcamp.topic6.apptopic6.model.Cart;
import com.bootcamp.topic6.apptopic6.model.CartItem;
import com.bootcamp.topic6.apptopic6.model.LineSale;
import com.bootcamp.topic6.apptopic6.model.Product;
import com.bootcamp.topic6.apptopic6.model.Sale;
import com.bootcamp.topic6.apptopic6.model.User;
import com.bootcamp.topic6.apptopic6.repository.CartItemRepository;
import com.bootcamp.topic6.apptopic6.repository.CartRepository;
import com.bootcamp.topic6.apptopic6.repository.LineSaleRepository;
import com.bootcamp.topic6.apptopic6.repository.ProductRepository;
import com.bootcamp.topic6.apptopic6.repository.SaleRepository;
import com.bootcamp.topic6.apptopic6.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("shoppingCartService")
public class ShoppingCartServiceImp implements ShoppingCartService {

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

  @Autowired
  private LineSaleRepository lineSaleRepo;

  @Override
  public boolean createCart(Long iduser) {
    if (!userRepo.findById(iduser).isPresent()) return false;

    User user = userRepo.findById(iduser).get();
    Cart newCart = new Cart(user);
    user.getCartList().add(newCart);
    userRepo.save(user);

    return true;
  }

  @Override
  public List<CartItem> getAllCartItems(Long idcart) {
    return cartRepo.findById(idcart).get().getCartItemList();
  }

  @Override
  public boolean addToCart(Long idcart, Long idproduct, Integer quantity) {
    if (!cartRepo.findById(idcart).isPresent()) return false;
    if (!productRepo.findById(idproduct).isPresent()) return false;

    Cart cart = cartRepo.findById(idcart).get();
    CartItem cartItem;

    if (cart.existsProduct(idproduct)) {
      cartItem = cart.getCartItemByIdProduct(idproduct);
      cartItem.incrementQuantity(quantity);
    } else {
      cartItem = new CartItem(idcart,idproduct,quantity);
      cart.getCartItemList().add(cartItem);
    }
    cartRepo.save(cart);

    return true;
  }

  @Override
  public boolean removeProduct(Long idcart, Long idproduct, Integer quantity) {
    if (!cartRepo.findById(idcart).isPresent()) return false;
    if (!productRepo.findById(idproduct).isPresent()) return false;

    Cart cart = cartRepo.findById(idcart).get();

    if (!cart.existsProduct(idproduct)) return false;

    CartItem cartItem = cart.getCartItemByIdProduct(idproduct);
    cartItem.decrementQuantity(quantity);

    cartRepo.save(cart);

    return true;
  }

  @Override
  public boolean deleteProduct(Long idcart, Long idproduct) {
    if (!cartRepo.findById(idcart).isPresent()) return false;
    if (!productRepo.findById(idproduct).isPresent()) return false;

    Cart cart = cartRepo.findById(idcart).get();

    if (!cart.existsProduct(idproduct)) return false;

    Long idCartItem = cart.getCartItemByIdProduct(idproduct).getIdcartitem();
    cart.deleteProduct(idproduct);
    cartItemRepo.deleteById(idCartItem);

    return true;
  }

  @Override
  public boolean clearCart(Long idcart) {
    if (!cartRepo.findById(idcart).isPresent()) return false;

    Cart cart = cartRepo.findById(idcart).get();
    cart.getCartItemList().clear();
    cartRepo.save(cart);
    cartItemRepo.deleteByIdcart(idcart);

    return true;
  }

  @Override
  public Sale doCheckOut(Long idcart) {
    if (!cartRepo.findById(idcart).isPresent()) return null;

    Sale sale = new Sale(LocalDateTime.now());
    saleRepo.save(sale);
    Cart cart = cartRepo.findById(idcart).get();

    for (CartItem cartItem : cart.getCartItemList()) {
      Product product = productRepo.findById(cartItem.getIdproduct()).get();
      LineSale lineSale = new LineSale(sale.getIdSale(),product,cartItem.getQuantity());
      sale.getLineSaleList().add(lineSale);
    }
    sale.calculateTotalPrice();
    saleRepo.save(sale);
    cart.setCheckedOut(true);
    cartRepo.save(cart);

    return sale;
  }
}
