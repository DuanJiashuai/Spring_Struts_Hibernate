package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class User {
	@Id
	@Column(name="userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String username;
	private String password;
	private String nickname;
	private String tel;
	private String email;
	private String headicon;
	@OneToMany(targetEntity = Collection.class, mappedBy = "user")
	private Set<Collection> collections = new HashSet<Collection>();
	@OneToMany(targetEntity = Address.class, mappedBy = "user")
	private Set<Address> addresses = new HashSet<Address>();
	@OneToMany(targetEntity = Cart.class, mappedBy = "user")
	private Set<Cart> carts = new HashSet<Cart>();
	@OneToMany(targetEntity = Order.class, mappedBy = "user")
	private Set<Order> orders = new HashSet<Order>();

	public User() {

	}

	public User(String username, String password, String nickname) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}

	public User(String username, String password, String nickname, String tel, String email) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.tel = tel;
		this.email = email;
	}

	public User(String username, String password, String nickname, String tel, String email, String headicon) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.tel = tel;
		this.email = email;
		this.headicon = headicon;
	}

	public void setUserId(Integer id) {
		this.userId = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadicon() {
		return headicon;
	}

	public void setHeadicon(String headicon) {
		this.headicon = headicon;
	}

	public Set<Collection> getCollections() {
		return collections;
	}

	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}
	
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "User[userid=" + userId + ",username=" + username + ",password=" + password + "]";
	}
}
