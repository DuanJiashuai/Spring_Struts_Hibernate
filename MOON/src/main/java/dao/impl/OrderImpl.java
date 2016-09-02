package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.OrderDao;
import entity.Order;

public class OrderImpl implements OrderDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Order getOrderById(Integer orderid) {
		return (Order) getSessionFactory().getCurrentSession().get(Order.class, orderid);
	}

	@SuppressWarnings("unchecked")
	public List<Order> getOrderListByUserId(Integer userid) {
		Session session = getSessionFactory().getCurrentSession();
		String sqlString = "select * from order_info where userId=?";
		List<Order> list = session.createSQLQuery(sqlString).addEntity(Order.class).setInteger(0, userid).list();
		if (list.size() != 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Order> getOrderListByCommidityId(Integer commidityid) {
		Session session = getSessionFactory().getCurrentSession();
		String sqlString = "select * from order_info where commidityId=?";
		List<Order> list = session.createSQLQuery(sqlString).addEntity(Order.class).setInteger(0, commidityid).list();
		if (list.size() != 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Order> getOrderListByUserIdAndStatus(Integer userid, Integer status) {
		Session session = getSessionFactory().getCurrentSession();
		String sqlString = "select * from order_info where userId=? and orderStatus=?";
		List<Order> list = session.createSQLQuery(sqlString).addEntity(Order.class).setInteger(0, userid)
				.setInteger(1, status).list();
		if (list.size() != 0) {
			return list;
		} else {
			return null;
		}
	}

	public void updateOrder(Order order) {
		getSessionFactory().getCurrentSession().saveOrUpdate(order);
	}

	public void deleteOrder(Order order) {
		getSessionFactory().getCurrentSession().delete(order);
	}

	public void deleteOrderById(Integer orderid) {
		getSessionFactory().getCurrentSession().createSQLQuery("delete order_info where orderId=?")
				.setParameter(0, orderid).executeUpdate();
	}
}
