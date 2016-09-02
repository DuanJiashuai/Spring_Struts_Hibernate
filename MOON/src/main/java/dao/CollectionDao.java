package dao;

import java.util.List;

import entity.Collection;

public interface CollectionDao {
	Collection getCollectionById(Integer collectionid);

	List<Collection> getCollectionListByUserId(Integer userid);

	void updateCollection(Collection collection);

	void deleteCollection(Collection collection);

	void deleteCollectionById(Integer collectionid);

}
