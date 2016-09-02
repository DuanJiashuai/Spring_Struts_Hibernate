package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.CartDao;
import entity.Cart;

public class CartImpl implements CartDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Cart getCartById(Integer cartid) {
		return (Cart) getSessionFactory().getCurrentSession().get(Cart.class, cartid);
	}

	@SuppressWarnings("unchecked")
	public List<Cart> getCartListByUserId(Integer userid) {
		Session session = getSessionFactory().getCurrentSession();
		String sqlString = "select * from cart_info where userId=?";
		List<Cart> list = session.createSQLQuery(sqlString).addEntity(Cart.class).setInteger(0, userid).list();
		if (list.size() != 0) {
			return list;
		} else {
			return null;
		}
	}

	public void updateCart(Cart cart) {
		getSessionFactory().getCurrentSession().saveOrUpdate(cart);
	}

	public void deleteCart(Cart cart) {
		getSessionFactory().getCurrentSession().delete(cart);
	}

	public void deleteCartById(Integer cartid) {
		getSessionFactory().getCurrentSession().createSQLQuery("delete cart_info where cartId=?")
				.setParameter(0, cartid).executeUpdate();
	}
}
