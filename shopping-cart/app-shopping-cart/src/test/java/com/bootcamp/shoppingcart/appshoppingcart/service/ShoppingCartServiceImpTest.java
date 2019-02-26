package com.bootcamp.shoppingcart.appshoppingcart.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bootcamp.shoppingcart.appshoppingcart.exception.CartCheckedOutException;
import com.bootcamp.shoppingcart.appshoppingcart.exception.NotFoundException;
import com.bootcamp.shoppingcart.appshoppingcart.model.Cart;
import com.bootcamp.shoppingcart.appshoppingcart.model.CartItem;
import com.bootcamp.shoppingcart.appshoppingcart.model.Product;
import com.bootcamp.shoppingcart.appshoppingcart.model.Sale;
import com.bootcamp.shoppingcart.appshoppingcart.model.User;
import com.bootcamp.shoppingcart.appshoppingcart.repository.CartItemRepository;
import com.bootcamp.shoppingcart.appshoppingcart.repository.CartRepository;
import com.bootcamp.shoppingcart.appshoppingcart.repository.SaleRepository;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ShoppingCartServiceImpTest {

  @InjectMocks
  private ShoppingCartServiceImp shoppingCartService;

  @Mock
  private CartRepository cartRepo;

  @Mock
  private CartItemRepository cartItemRepo;

  @Mock
  private SaleRepository saleRepo;

  @Mock
  private UserService userService;

  @Mock
  private ProductService productService;

  private User user;
  private Cart cart;
  private Product product1, product2;
  private CartItem cartItem1, cartItem2;

  @Before
  public void setUp() throws Exception {
    user = new User("username","password","email@email.com");
    cart = new Cart(user);
    user.getCartList().add(cart);
    when(cartRepo.findById(anyLong())).thenReturn(Optional.of(cart));

    product1 = mock(Product.class);
    when(product1.getIdproduct()).thenReturn(1L);
    product2 = mock(Product.class);
    when(product2.getIdproduct()).thenReturn(2L);
    cartItem1 = spy(new CartItem(cart,product1,2));
    cartItem2 = spy(new CartItem(cart,product2,3));
    cart.getCartItemList().add(cartItem1);
    cart.getCartItemList().add(cartItem2);
  }

  @Test
  public void whenCreateCartThenItIsAddedToCartListOfUserAndSaved() {
    when(userService.getUserByUsername("username")).thenReturn(user);
    shoppingCartService.createCart("username");

    assertEquals(2,user.getCartList().size());
    verify(cartRepo, times(1)).save(any(Cart.class));
  }

  @Test
  public void whenGetCartByIdThenReturnTheFoundCart() {
    Cart foundCart = shoppingCartService.getCartById(anyLong());

    assertEquals(cart,foundCart);
  }

  @Test(expected = NotFoundException.class)
  public void whenGetCartByIdAndItDoesntExistThenThrowNotFoundException() {
    when(cartRepo.findById(anyLong())).thenReturn(Optional.empty());
    shoppingCartService.getCartById(anyLong());
  }

  @Test
  public void whenDeleteCartThenCartIsDeletedFromCartRepo() {
    when(cartRepo.existsById(anyLong())).thenReturn(true);
    shoppingCartService.deleteCart(1L);

    verify(cartRepo, times(1)).deleteById(1L);
  }

  @Test(expected = NotFoundException.class)
  public void whenDeleteCartAndItDoesntExistThenThrowNotFoundException() {
    when(cartRepo.existsById(anyLong())).thenReturn(false);
    shoppingCartService.deleteCart(anyLong());

    verify(cartRepo, never()).deleteById(anyLong());
  }

  @Test
  public void whenGetAllCartItemsThenReturnIsListOfCartItems() {
    List<CartItem> cartItemList = shoppingCartService.getAllCartItems(anyLong());

    assertEquals(cart.getCartItemList(),cartItemList);
    assertEquals(2,cartItemList.size());
  }

  @Test
  public void whenAddToCartAndProductExistsThenIncrementQuantity() {
    shoppingCartService.addToCart(anyLong(),1L,2);

    assertEquals(2,cart.getCartItemList().size());
    assertTrue(cart.existsProduct(1L));
    assertTrue(cart.existsProduct(2L));
    assertEquals(2+2,cart.getOneCartItem(1L).getQuantity());
    verify(cartRepo, times(1)).save(cart);
  }

  @Test
  public void whenAddToCartAndProductDoesntExistThenCreateNewCartItem() {
    Product product3 = mock(Product.class);
    when(product3.getIdproduct()).thenReturn(3L);
    when(productService.getProductById(3L)).thenReturn(product3);

    shoppingCartService.addToCart(anyLong(),3L,5);

    assertEquals(3,cart.getCartItemList().size());
    assertTrue(cart.existsProduct(3L));
    assertEquals(5,cart.getOneCartItem(3L).getQuantity());
    verify(cartRepo, times(1)).save(cart);
  }

  @Test
  public void whenRemoveProductThenDecrementQuantity() {
    shoppingCartService.removeProduct(anyLong(),1L,1);

    assertEquals(2,cart.getCartItemList().size());
    assertTrue(cart.existsProduct(1L));
    assertEquals(2-1,cart.getOneCartItem(1L).getQuantity());
    verify(cartRepo, times(1)).save(cart);
  }

  @Test
  public void whenDeleteProductThenItIsGoneFromTheCart() {
    doReturn(5L).when(cartItem2).getIdcartitem();

    shoppingCartService.deleteProduct(anyLong(),2L);

    assertEquals(1,cart.getCartItemList().size());
    assertFalse(cart.existsProduct(2L));
    verify(cartItemRepo, times(1)).deleteById(5L);
  }

  @Test
  public void whenClearCartThenItIsEmpty() {
    shoppingCartService.clearCart(anyLong());

    assertEquals(0,cart.getCartItemList().size());
    assertTrue(cart.getCartItemList().isEmpty());
    verify(cartItemRepo, times(1)).deleteByCart(cart);
  }

  @Test
  public void whenDoCheckOutThenItReturnsSaleWithCorrectTotalPrice() {
    when(product1.getPrice()).thenReturn(100.0);
    when(product2.getPrice()).thenReturn(200.0);

    Sale sale = shoppingCartService.doCheckOut(anyLong());
    double totalPrice = 2 * 100.0 + 3 * 200.0;

    assertEquals(totalPrice,sale.getTotalPrice(),0);
    assertEquals(2,sale.getLineSaleList().size());
    assertTrue(cart.isCheckedOut());
    verify(saleRepo, times(1)).save(sale);
    verify(cartRepo, times(1)).save(cart);
  }

  @Test(expected = CartCheckedOutException.class)
  public void whenDoCheckOutAndCartIsCheckedOutThenThrowCartCheckedOutException() {
    cart.setCheckedOut(true);
    shoppingCartService.doCheckOut(anyLong());

    verify(saleRepo, never()).save(any(Sale.class));
    verify(cartRepo, never()).save(any(Cart.class));
  }
}