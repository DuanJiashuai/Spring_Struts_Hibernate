package dao;

import java.util.List;

import entity.Address;

public interface AddressDao {
	Address getAddressById(Integer addressid);

	List<Address> getAddressesByUserId(Integer userid);

	void updateAddress(Address address);

	void deleteAddress(Address address);

	void deleteAddressById(Integer addressid);
}
