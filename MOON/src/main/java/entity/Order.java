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
@Table(name = "order_info")
public class Order {
	@Id
	@Column(name="orderId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private String orderCode;
	@OneToOne(targetEntity = Commidity.class)
	@JoinColumn(name = "commidityId", referencedColumnName = "commidityId", unique = true)
	private Commidity commidity;
	private Integer amount;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
	private User user;
	private Integer orderStatus;
	@OneToOne(targetEntity=Evaluate.class,mappedBy="order")
	private Evaluate evaluate;
	
	public Order(){
		
	}
	
	public Order(String orderCode,Commidity commidity,Integer amount,User user,Integer orderStatus){
		this.orderCode=orderCode;
		this.commidity=commidity;
		this.amount=amount;
		this.user=user;
		this.orderStatus=orderStatus;
	}
	
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}
}
