package service;

import java.util.List;

import entity.Commidity;

public interface CommidityService {

	Commidity getCommidityById(Integer commidityid);

	void updateCommidity(Commidity commidity);

	void deleteCommidity(Commidity commidity);

	void deleteCommidityById(Integer commidityid);

	List<Commidity> getAllCommidities();

}
