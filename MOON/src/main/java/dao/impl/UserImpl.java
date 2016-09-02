package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.UserDao;
import entity.User;

public class UserImpl implements UserDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public User getUserById(Integer userid) {
		return (User) getSessionFactory().getCurrentSession().get(User.class, userid);
	}

	public User getUserByUsername(String username) {
		Session session = getSessionFactory().getCurrentSession();
		String sqlString = "select * from user_info where username=?";
		List list = session.createSQLQuery(sqlString).addEntity(User.class).setString(0, username).list();
		if (list.size() != 0) {
			User user = (User) list.get(0);
			return user;
		} else {
			return null;
		}
	}

	public void updateUser(User user) {
		getSessionFactory().getCurrentSession().saveOrUpdate(user);
	}

	public void deleteUser(User user) {
		getSessionFactory().getCurrentSession().delete(user);
	}

	public void deleteUserById(Integer userid) {
		getSessionFactory().getCurrentSession().createSQLQuery("delete user_info where userId=?")
				.setParameter(0, userid).executeUpdate();
	}

}
