package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_info")
public class Cart {
	@Id
	@Column(name="cartId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;
	@OneToOne(targetEntity = Commidity.class)
	@JoinColumn(name = "commidityId", referencedColumnName = "commidityId", unique = true)
	private Commidity commidity;
	private Integer amount;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
	private User user;

	public Cart(){
		
	}
	
	public Cart(Commidity commidity,Integer amount,User user){
		this.commidity=commidity;
		this.amount=amount;
		this.user=user;
	}
	
	
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Commidity getCommidity() {
		return commidity;
	}

	public void setCommidity(Commidity commidity) {
		this.commidity = commidity;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
