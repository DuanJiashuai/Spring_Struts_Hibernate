package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.EvaluateDao;
import entity.Evaluate;
import entity.User;

public class EvaluateImpl implements EvaluateDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Evaluate getEvaluateById(Integer evaluateid) {
		return (Evaluate) getSessionFactory().getCurrentSession().get(Evaluate.class, evaluateid);
	}

	@SuppressWarnings("unchecked")
	public Evaluate getEvaluateByOrderId(Integer orderid) {
		Session session = getSessionFactory().getCurrentSession();
		String sqlString = "select * from commidity_evaluate where orderId=?";
		List<Evaluate> list = session.createSQLQuery(sqlString).addEntity(User.class).setInteger(0, orderid).list();
		if (list.size() != 0) {
			Evaluate evaluate = (Evaluate) list.get(0);
			return evaluate;
		} else {
			return null;
		}
	}

	public void updateEvaluate(Evaluate evaluate) {
		getSessionFactory().getCurrentSession().saveOrUpdate(evaluate);
	}

	public void deleteEvaluate(Evaluate evaluate) {
		getSessionFactory().getCurrentSession().delete(evaluate);
	}

	public void deleteEvaluateById(Integer evaluateid) {
		getSessionFactory().getCurrentSession().createSQLQuery("delete commidity_evaluate where evaluateId=?")
				.setParameter(0, evaluateid).executeUpdate();
	}

}
