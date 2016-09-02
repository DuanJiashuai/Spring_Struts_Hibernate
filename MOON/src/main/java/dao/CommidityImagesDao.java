package dao;

import java.util.List;

import entity.CommidityImages;

public interface CommidityImagesDao {
	List<CommidityImages> getCimagesByCommidityId(Integer commidityId);

	void updateCimages(CommidityImages cimages);

	void deleteCimages(CommidityImages cimages);

	void deleteCimagesById(Integer cimagesid);
}
