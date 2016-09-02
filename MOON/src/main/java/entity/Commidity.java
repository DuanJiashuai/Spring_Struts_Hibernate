package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "commidity_info")
public class Commidity {

	@Id
	@Column(name="commidityId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commidityId;
	private String commidityCode;
	private String commidityName;
	private String commidityDescription;
	private double price;
	private Integer stock;
	private Integer salesVol;
	@ManyToOne(targetEntity = SubClassification.class)
	@JoinColumn(name = "sclassId", referencedColumnName = "sclassId", nullable = false)
	private SubClassification sclass;
	@OneToMany(targetEntity = CommidityImages.class, mappedBy = "commidity")
	private Set<CommidityImages> cimages = new HashSet<CommidityImages>();

	public Commidity(){
		
	}
	
	public Commidity(String commidityCode,String commidityName,String commidityDescription,double price,Integer stock,Integer salesVol){
		this.commidityCode=commidityCode;
		this.commidityName=commidityName;
		this.commidityDescription=commidityDescription;
		this.price=price;
		this.stock=stock;
		this.salesVol=salesVol;
	}
	
	
	public Integer getCommidityId() {
		return commidityId;
	}

	public void setCommidityId(Integer commidityId) {
		this.commidityId = commidityId;
	}

	public String getCommidityCode() {
		return commidityCode;
	}

	public void setCommidityCode(String commidityCode) {
		this.commidityCode = commidityCode;
	}

	public String getCommidityDescription() {
		return commidityDescription;
	}

	public void setCommidityDescription(String commidityDescription) {
		this.commidityDescription = commidityDescription;
	}

	public String getCommidityName() {
		return commidityName;
	}

	public void setCommidityName(String commidityName) {
		this.commidityName = commidityName;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getSalesVol() {
		return salesVol;
	}

	public void setSalesVol(Integer salesVol) {
		this.salesVol = salesVol;
	}

	public SubClassification getSclass() {
		return sclass;
	}

	public void setSclass(SubClassification sclass) {
		this.sclass = sclass;
	}

	public Set<CommidityImages> getCimages() {
		return cimages;
	}

	public void setCimages(Set<CommidityImages> cimages) {
		this.cimages = cimages;
	}
}
