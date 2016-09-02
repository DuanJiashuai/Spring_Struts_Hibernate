package dao;

import java.util.List;

import entity.Order;

public interface OrderDao {
	Order getOrderById(Integer orderid);

	List<Order> getOrderListByUserId(Integer userid);

	List<Order> getOrderListByCommidityId(Integer commidityid);

	List<Order> getOrderListByUserIdAndStatus(Integer userid, Integer status);

	void updateOrder(Order order);

	void deleteOrder(Order order);

	void deleteOrderById(Integer orderid);
}
