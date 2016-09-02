package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.CollectionDao;
import entity.Collection;

public class CollectionImpl implements CollectionDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Collection getCollectionById(Integer collectionid) {
		return (Collection) getSessionFactory().getCurrentSession().get(Collection.class, collectionid);
	}

	@SuppressWarnings("unchecked")
	public List<Collection> getCollectionListByUserId(Integer userid) {
		Session session = getSessionFactory().getCurrentSession();
		String sqlString = "select * from commidity_collection where userId=?";
		List<Collection> list = session.createSQLQuery(sqlString).addEntity(Collection.class).setInteger(0, userid)
				.list();
		if (list.size() != 0) {
			return list;
		} else {
			return null;
		}
	}

	public void updateCollection(Collection collection) {
		getSessionFactory().getCurrentSession().saveOrUpdate(collection);
	}

	public void deleteCollection(Collection collection) {
		getSessionFactory().getCurrentSession().delete(collection);
	}

	public void deleteCollectionById(Integer collectionid) {
		getSessionFactory().getCurrentSession().createSQLQuery("delete commidity_collection where collectionId=?")
				.setParameter(0, collectionid).executeUpdate();
	}
}
