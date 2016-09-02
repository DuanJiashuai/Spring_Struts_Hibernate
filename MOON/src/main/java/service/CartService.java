package service;

import java.util.List;

import entity.Cart;

public interface CartService {

	Cart getCartById(Integer cartid);

	List<Cart> getCartListByUserId(Integer userid);

	void updateCart(Cart cart);

	void deleteCart(Cart cart);

	void deleteCartById(Integer cartid);

}
