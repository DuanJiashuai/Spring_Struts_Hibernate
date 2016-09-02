package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.CommidityImagesDao;
import entity.CommidityImages;

public class CommidityImagesImpl implements CommidityImagesDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<CommidityImages> getCimagesByCommidityId(Integer commidityId) {
		Session session = getSessionFactory().getCurrentSession();
		String sqlString = "select * from commidity_images where commidityId=?";
		List<CommidityImages> list = session.createSQLQuery(sqlString).addEntity(CommidityImages.class)
				.setInteger(0, commidityId).list();
		if (list.size() != 0) {
			return list;
		}
		return null;
	}

	public void updateCimages(CommidityImages cimages) {
		getSessionFactory().getCurrentSession().saveOrUpdate(cimages);
	}

	public void deleteCimages(CommidityImages cimages) {
		getSessionFactory().getCurrentSession().delete(cimages);
	}

	public void deleteCimagesById(Integer cimagesid) {
		getSessionFactory().getCurrentSession().createSQLQuery("delete commidity_images where cimageId=?")
				.setParameter(0, cimagesid).executeUpdate();
	}
}
