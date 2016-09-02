package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Cart;
import entity.Commidity;
import entity.CommidityImages;
import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.CartService;
import service.CommidityImagesService;
import service.CommidityService;
import service.UserService;

public class CartManage extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer cartId;
	private Integer commidityId;
	private Integer amount;
	
	private CartService cartService;
	private UserService userService;
	private CommidityService commidityService;
	private CommidityImagesService cimageService;

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setCommidityService(CommidityService commidityService) {
		this.commidityService = commidityService;
	}

	public void setCimageService(CommidityImagesService cimageService) {
		this.cimageService = cimageService;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getCommidityId() {
		return commidityId;
	}

	public void setCommidityId(Integer commidityId) {
		this.commidityId = commidityId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String addToCart() {
		try {
			String username = (String) ActionContext.getContext().getSession().get("username");
			User user = userService.getUserByUsername(username);
			Commidity commidity = commidityService.getCommidityById(getCommidityId());
			Cart cart = new Cart(commidity, getAmount(), user);
			cartService.updateCart(cart);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String getCartListByUserId() throws IOException {
		String username = (String) ActionContext.getContext().getSession().get("username");
		Integer userid = userService.getUserByUsername(username).getUserId();
		List<Cart> list = cartService.getCartListByUserId(userid);
		JSONArray ja = new JSONArray();
		for (Cart c : list) {
			JSONObject jo = new JSONObject();
			jo.put("cartId", c.getCartId());
			jo.put("commidityId", c.getCommidity().getCommidityId());
			jo.put("commidityName", c.getCommidity().getCommidityName());
			List<CommidityImages> cimages = cimageService.getCimagesByCommidityId(c.getCommidity().getCommidityId());
			jo.put("pic", cimages.get(0).getCimagePath());
			jo.put("price", c.getCommidity().getPrice());
			jo.put("amount", c.getAmount());
			jo.put("salesVol", c.getCommidity().getSalesVol());
			jo.put("sum", c.getAmount()*c.getCommidity().getPrice());
			ja.add(jo);
		}
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(ja);
		return null;
	}
	
	public String updateCart(){
		Cart cart=cartService.getCartById(getCartId());
		cart.setAmount(getAmount());
		cartService.updateCart(cart);
		return SUCCESS;
	}

	public String deleteCart() {
		Cart cart=cartService.getCartById(getCartId());
		cartService.deleteCart(cart);
		return SUCCESS;
	}

}
