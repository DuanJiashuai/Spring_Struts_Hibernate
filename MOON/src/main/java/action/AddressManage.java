package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Address;
import entity.User;
import service.AddressService;
import service.UserService;

public class AddressManage extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer addressId;

	private String addressDetails;
	private String recieverName;
	private String recieverTel;
	private String postcode;

	private AddressService addressService;
	private UserService userService;

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	public String getRecieverName() {
		return recieverName;
	}

	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}

	public String getRecieverTel() {
		return recieverTel;
	}

	public void setRecieverTel(String recieverTel) {
		this.recieverTel = recieverTel;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String addAddress() {
		try {
			String username = (String) ActionContext.getContext().getSession().get("username");
			User user = userService.getUserByUsername(username);
			Address address = new Address(getAddressDetails(), getRecieverName(), getRecieverTel(), getPostcode());
			address.setUser(user);
			addressService.updateAddress(address);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String editAddress() {
		try {
			Address address = addressService.getAddressById(getAddressId());
			address.setAddressDetails(getAddressDetails());
			address.setPostcode(getPostcode());
			address.setRecieverName(getRecieverName());
			address.setRecieverTel(getRecieverTel());
			addressService.updateAddress(address);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String getDefaultAddress(){
		try{
			Address address = addressService.getAddressesByUserId(getUserId()).get(0);
			this.setAddressId(address.getAddressId());
			this.setAddressDetails(address.getAddressDetails());
			this.setRecieverName(address.getRecieverName());
			this.setRecieverTel(address.getRecieverTel());
			this.setPostcode(address.getPostcode());
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}

	public Integer getUserId() {
		String username = (String) ActionContext.getContext().getSession().get("username");
		Integer userid = userService.getUserByUsername(username).getUserId();
		return userid;
	}
}
