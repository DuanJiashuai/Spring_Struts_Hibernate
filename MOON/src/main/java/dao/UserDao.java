package dao;

import entity.User;

public interface UserDao {

	User getUserById(Integer userid);

	User getUserByUsername(String username);

	void updateUser(User user);

	void deleteUser(User user);

	void deleteUserById(Integer userid);
}
