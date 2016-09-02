package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.CommidityDao;
import entity.Address;
import entity.Commidity;

public class CommidityImpl implements CommidityDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Commidity> getAllCommidities(){
		Session session = getSessionFactory().getCurrentSession();
		String sqlString = "select * from commidity_info";
		List<Commidity> list = session.createSQLQuery(sqlString).addEntity(Commidity.class).list();
		if (list.size() != 0) {
			return list;
		} else {
			return null;
		}
	}
	
	public Commidity getCommidityById(Integer commidityid) {
		return (Commidity) getSessionFactory().getCurrentSession().get(Commidity.class, commidityid);
	}

	public void updateCommidity(Commidity commidity) {
		getSessionFactory().getCurrentSession().saveOrUpdate(commidity);
	}

	public void deleteCommidity(Commidity commidity) {
		getSessionFactory().getCurrentSession().delete(commidity);
	}

	public void deleteCommidityById(Integer commidityid) {
		getSessionFactory().getCurrentSession().createSQLQuery("delete commidity_info where commidityId=?")
				.setParameter(0, commidityid).executeUpdate();
	}
}
