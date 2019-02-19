package com.bootcamp.topic6.apptopic6.service;

import com.bootcamp.topic6.apptopic6.exception.NotFoundException;
import com.bootcamp.topic6.apptopic6.exception.NotFoundProductInCart;
import com.bootcamp.topic6.apptopic6.model.Cart;
import com.bootcamp.topic6.apptopic6.model.CartItem;
import com.bootcamp.topic6.apptopic6.model.LineSale;
import com.bootcamp.topic6.apptopic6.model.Product;
import com.bootcamp.topic6.apptopic6.model.Sale;
import com.bootcamp.topic6.apptopic6.model.User;
import com.bootcamp.topic6.apptopic6.repository.CartItemRepository;
import com.bootcamp.topic6.apptopic6.repository.CartRepository;
import com.bootcamp.topic6.apptopic6.repository.ProductRepository;
import com.bootcamp.topic6.apptopic6.repository.SaleRepository;
import com.bootcamp.topic6.apptopic6.repository.UserRepository;
import java.time.LocalDateTime;
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

    if (cart.existsProduct(idproduct)) {
      cart.getCartItemByIdProduct(idproduct).incrementQuantity(quantity);
    } else {
      CartItem cartItem = new CartItem(idcart,idproduct,quantity);
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

    CartItem cartItem = cart.getCartItemByIdProduct(idproduct);
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

    Long idCartItem = cart.getCartItemByIdProduct(idproduct).getIdcartitem();
    cart.deleteProduct(idproduct);
    cartItemRepo.deleteById(idCartItem);
  }

  @Override
  public void clearCart(Long idcart) {
    if (!cartRepo.existsById(idcart)) throw new NotFoundException(CART_NAME,idcart);

    Cart cart = cartRepo.findById(idcart).get();
    cart.getCartItemList().clear();
    cartRepo.save(cart);
    cartItemRepo.deleteByIdcart(idcart);
  }

  @Override
  public Sale doCheckOut(Long idcart) {
    if (!cartRepo.existsById(idcart)) throw new NotFoundException(CART_NAME,idcart);

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
