package service;

import java.util.List;

import entity.Collection;

public interface CollectionService {

	Collection getCollectionById(Integer collectionid);

	List<Collection> getCollectionListByUserId(Integer userid);

	void updateCollection(Collection collection);

	void deleteCollection(Collection collection);

	void deleteCollectionById(Integer collectionid);

}
