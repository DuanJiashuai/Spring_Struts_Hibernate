package dao;

import java.util.List;

import entity.Commidity;

public interface CommidityDao {

	List<Commidity> getAllCommidities();

	Commidity getCommidityById(Integer commidityid);

	void updateCommidity(Commidity commidity);

	void deleteCommidity(Commidity commidity);

	void deleteCommidityById(Integer commidityid);
}
