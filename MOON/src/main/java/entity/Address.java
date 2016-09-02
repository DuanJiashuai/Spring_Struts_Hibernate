package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	@Id
	@Column(name="addressId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	private String addressDetails;
	private String recieverName;
	private String recieverTel;
	private String postcode;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
	private User user;

	public Address(){
		
	}
	
	public Address(String addressDetails,String recieverName,String recieverTel,String postcode){
		this.addressDetails=addressDetails;
		this.recieverName=recieverName;
		this.recieverTel=recieverTel;
		this.postcode=postcode;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString(){
		String str="{\"addressId\":\""+addressId+"\",\"addressDetails\":\""+addressDetails+"\",\"recieverName\":\""+recieverName+"\",\"recieverTel\":\""+recieverTel+"\",\"postcode\":\""+postcode+"\"}";
		return str;
	}

}
