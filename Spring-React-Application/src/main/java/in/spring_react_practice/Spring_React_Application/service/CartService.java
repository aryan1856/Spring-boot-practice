package in.spring_react_practice.Spring_React_Application.service;

import in.spring_react_practice.Spring_React_Application.model.Cart;
import in.spring_react_practice.Spring_React_Application.model.CartItem;
import in.spring_react_practice.Spring_React_Application.request.AddCartItemRequest;

public interface CartService {

    CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;

    CartItem updateQuantity(Long cartItemId, int quantity) throws Exception;

    Cart removeItemFromcart(Long cartItemId, String jwt) throws Exception;

    Long calculateCartTotal(Cart cart) throws Exception;

    Cart findCartById(Long id) throws Exception;

    Cart findCartByUserId(Long userId) throws Exception;

    Cart clearCart(Long userId) throws Exception;

}