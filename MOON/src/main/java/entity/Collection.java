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
@Table(name = "commidity_collection")
public class Collection {
	@Id
	@Column(name = "collectionId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer collectionId;
	@OneToOne(targetEntity = Commidity.class)
	@JoinColumn(name = "commidityId", referencedColumnName = "commidityId", unique = true)
	private Commidity commidity;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
	private User user;

	public Collection() {

	}

	public Collection(Commidity commidity, User user) {
		this.commidity = commidity;
		this.user = user;
	}

	public Integer getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	public Commidity getCommidity() {
		return commidity;
	}

	public void setCommidity(Commidity commidity) {
		this.commidity = commidity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
