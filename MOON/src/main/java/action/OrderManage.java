package action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Cart;
import entity.Commidity;
import entity.Order;
import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.CartService;
import service.CommidityService;
import service.OrderService;
import service.UserService;

public class OrderManage extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer orderId;
	private Integer commidityId;
	private String orderCode;
	private Integer amount;
	private Integer orderStatus;
	private HttpServletRequest request;

	private OrderService orderService;
	private UserService userService;
	private CommidityService commidityService;
	private CartService cartService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setCommidityService(CommidityService commidityService) {
		this.commidityService = commidityService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCommidityId() {
		return commidityId;
	}

	public void setCommidityId(Integer commidityId) {
		this.commidityId = commidityId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String createOrders() {
		request = ServletActionContext.getRequest();
		String[] cartId = request.getParameterValues("cartId");
		String username = (String) ActionContext.getContext().getSession().get("username");
		User user = userService.getUserByUsername(username);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String code = df.format(new Date());
		try {
			for (int i = 0; i < cartId.length; i++) {
				Integer amount = Integer.parseInt(request.getParameter("amount" + cartId[i]));
				Integer commidityId = Integer.parseInt(request.getParameter("commidityId" + cartId[i]));
				Commidity commidity = commidityService.getCommidityById(commidityId);
				Random random = new Random();
				int str = random.nextInt(9999) % (9999 - 1000 + 1) + 1000;
				String orderCode = code + str;
				Order order = new Order(orderCode, commidity, amount, user, 0);
				orderService.updateOrder(order);
				Cart cart = cartService.getCartById(Integer.parseInt(cartId[i]));
				cartService.deleteCart(cart);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String getOrderListByUserId() throws IOException {
		String username = (String) ActionContext.getContext().getSession().get("username");
		Integer userid = userService.getUserByUsername(username).getUserId();
		List<Order> list = null;
		if (getOrderStatus() == 5) {
			list = orderService.getOrderListByUserId(userid);
		} else {
			list = orderService.getOrderListByUserIdAndStatus(userid, getOrderStatus());
		}
		JSONArray ja = new JSONArray();
		for (Order o : list) {
			JSONObject jo = new JSONObject();
			jo.put("orderId", o.getOrderId());
			jo.put("orderCode", o.getOrderCode());
			jo.put("sumPrice", o.getAmount() * o.getCommidity().getPrice());
			jo.put("commidityId", o.getCommidity().getCommidityId());
			jo.put("commidityName", o.getCommidity().getCommidityName());
			Integer os = o.getOrderStatus();
			String orderStatus = "";
			if (os == 0) {
				orderStatus = "待付款";
			} else if (os == 1) {
				orderStatus = "待收货";
			} else if (os == 2) {
				orderStatus = "待评价";
			} else {
				orderStatus = "已完成";
			}
			jo.put("orderStatus", orderStatus);
			ja.add(jo);
		}
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(ja);
		return null;
	}

	public String getOrderDetailsById() throws IOException {
		Order order = orderService.getOrderById(getOrderId());
		JSONObject jo = new JSONObject();
		jo.put("orderCode", order.getOrderCode());
		jo.put("commidityId", order.getCommidity().getCommidityId());
		jo.put("commidityName", order.getCommidity().getCommidityName());
		jo.put("amount", order.getAmount());
		jo.put("sumPrice", order.getAmount() * order.getCommidity().getPrice());
		Integer os = order.getOrderStatus();
		String orderStatus = "";
		if (os == 0) {
			orderStatus = "待付款";
		} else if (os == 1) {
			orderStatus = "待收货";
		} else if (os == 2) {
			orderStatus = "待评价";
		} else {
			orderStatus = "已完成";
		}
		jo.put("orderStatus", orderStatus);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jo);
		return null;
	}

	public String updateOrderStatus() {
		try {
			Order order = orderService.getOrderById(getOrderId());
			order.setOrderStatus((order.getOrderStatus() + 1));
			if (order.getOrderStatus() == 1) {
				Commidity commidity = order.getCommidity();
				commidity.setStock((commidity.getStock() - 1));
				commidityService.updateCommidity(commidity);
			}
			if (order.getOrderStatus() == 2) {
				Commidity commidity = order.getCommidity();
				commidity.setSalesVol((commidity.getSalesVol() + 1));
				commidityService.updateCommidity(commidity);
			}
			orderService.updateOrder(order);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

}
