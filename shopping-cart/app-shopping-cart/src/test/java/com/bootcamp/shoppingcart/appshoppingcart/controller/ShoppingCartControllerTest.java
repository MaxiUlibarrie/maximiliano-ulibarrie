package com.bootcamp.shoppingcart.appshoppingcart.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bootcamp.shoppingcart.appshoppingcart.model.Cart;
import com.bootcamp.shoppingcart.appshoppingcart.model.CartItem;
import com.bootcamp.shoppingcart.appshoppingcart.model.Category;
import com.bootcamp.shoppingcart.appshoppingcart.model.Product;
import com.bootcamp.shoppingcart.appshoppingcart.model.User;
import com.bootcamp.shoppingcart.appshoppingcart.service.ShoppingCartService;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ShoppingCartControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @InjectMocks
  private ShoppingCartController shoppingCartController;

  @Mock
  private ShoppingCartService shoppingCartService;

  private User user;
  private Cart cart;
  private Product product;
  private CartItem cartItem;

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders
        .standaloneSetup(shoppingCartController)
        .build();

    user = mock(User.class);
//    when(user.getIduser()).thenReturn(1L);
    cart = spy(new Cart(user));
    doReturn(1L).when(cart).getIdcart();
    product = mock(Product.class);
    when(product.getIdproduct()).thenReturn(1L);
    cartItem = new CartItem(cart,product,5);
  }

  @Test
  public void whenDeleteCartThenReturnsHttpStatusOK() throws Exception {
    mockMvc.perform(delete("/api/shoppingcart/1"))
        .andExpect(status().isOk());
  }

  @Test
  public void whenGetCartItemsThenReturnsHttpStatusOK() throws Exception {
    List<CartItem> cartItemList = Arrays.asList(cartItem);
    when(shoppingCartService.getCartItems(anyLong())).thenReturn(cartItemList);

    mockMvc.perform(get("/api/shoppingcart/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].quantity", is(cartItem.getQuantity())));
  }

  @Test
  public void whenAddToCartThenReturnsHttpStatusOK() throws Exception {
    mockMvc.perform(post("/api/shoppingcart/1/product/1?quantity=3"))
        .andExpect(status().isOk());
  }

  @Test
  public void whenAddToCartAndForgotQuantityThenReturnsHttpStatusBadRequest() throws Exception {
    mockMvc.perform(post("/api/shoppingcart/1/product/1"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void whenAddToCartAndNotFoundCartThenReturnsHttpStatusNotFound() throws Exception {
    String messageNotFound = "Could not find entity";
    doThrow(new RuntimeException(messageNotFound))
        .when(shoppingCartService)
        .addToCart(anyLong(),anyLong(),anyInt());

    mockMvc.perform(post("/api/shoppingcart/1/product/1?quantity=3")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void whenRemoveProductThenReturnsHttpStatusOK() throws Exception {
    mockMvc.perform(put("/api/shoppingcart/1/product/1?quantity=3"))
        .andExpect(status().isOk());
  }

  @Test
  public void whenDeleteProductFromCartThenReturnsHttpStatusOK() throws Exception {
    mockMvc.perform(delete("/api/shoppingcart/1/product/1"))
        .andExpect(status().isOk());
  }

  @Test
  public void whenClearCartThenReturnsHttpStatusOK() throws Exception {
    mockMvc.perform(delete("/api/shoppingcart/1/clear"))
        .andExpect(status().isOk());
  }

  @Test
  public void whenDoCheckOutThenReturnsHttpStatusCreated() throws Exception {
    mockMvc.perform(post("/api/shoppingcart/1/checkout"))
        .andExpect(status().isCreated());
  }
}