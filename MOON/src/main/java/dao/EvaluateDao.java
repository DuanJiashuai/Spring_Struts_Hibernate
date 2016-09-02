package dao;

import entity.Evaluate;

public interface EvaluateDao {
	Evaluate getEvaluateById(Integer evaluateid);

	Evaluate getEvaluateByOrderId(Integer orderid);

	void updateEvaluate(Evaluate evaluate);

	void deleteEvaluate(Evaluate evaluate);

	void deleteEvaluateById(Integer evaluateid);

}
