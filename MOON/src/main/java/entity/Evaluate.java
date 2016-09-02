package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commidity_evaluate")
public class Evaluate {

	@Id
	@Column(name="evaluateId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer evaluateId;
	@OneToOne(targetEntity = Order.class)
	@JoinColumn(name = "orderId", referencedColumnName = "orderId", unique = true)
	private Order order;
	private Integer logistics;
	private Integer service;
	private Integer quality;
	@OneToMany(targetEntity = EvaluateImages.class, mappedBy = "evaluate")
	private Set<EvaluateImages> eimages = new HashSet<EvaluateImages>();

	public Integer getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(Integer evaluateId) {
		this.evaluateId = evaluateId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getLogistics() {
		return logistics;
	}

	public void setLogistics(Integer logistics) {
		this.logistics = logistics;
	}

	public Integer getService() {
		return service;
	}

	public void setService(Integer service) {
		this.service = service;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Set<EvaluateImages> getEimages() {
		return eimages;
	}

	public void setEimages(Set<EvaluateImages> eimages) {
		this.eimages = eimages;
	}
}
