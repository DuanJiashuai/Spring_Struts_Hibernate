package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.AddressDao;
import entity.Address;

public class AddressImpl implements AddressDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Address getAddressById(Integer addressid) {
		return (Address) getSessionFactory().getCurrentSession().get(Address.class, addressid);
	}
	
	@SuppressWarnings("unchecked")
	public List<Address> getAddressesByUserId(Integer userid) {
		Session session = getSessionFactory().getCurrentSession();
		String sqlString = "select * from address where userId=?";
		List<Address> list = session.createSQLQuery(sqlString).addEntity(Address.class).setInteger(0, userid).list();
		if (list.size() != 0) {
			return list;
		} else {
			return null;
		}
	}

	public void updateAddress(Address address) {
		getSessionFactory().getCurrentSession().saveOrUpdate(address);
	}

	public void deleteAddress(Address address) {
		getSessionFactory().getCurrentSession().delete(address);
	}

	public void deleteAddressById(Integer addressid) {
		getSessionFactory().getCurrentSession().createSQLQuery("delete address where userId=?")
				.setParameter(0, addressid).executeUpdate();
	}
}
