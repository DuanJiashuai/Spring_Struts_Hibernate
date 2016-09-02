package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "parentclass")
public class ParentClassification {
	@Id
	@Column(name="pclassId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pclassId;
	private String pclassName;
	@OneToMany(targetEntity = SubClassification.class, mappedBy = "pclass")
	private Set<SubClassification> sclasses = new HashSet<SubClassification>();

	public Integer getPclassId() {
		return pclassId;
	}

	public void setPclassId(Integer pclassId) {
		this.pclassId = pclassId;
	}

	public String getPclassName() {
		return pclassName;
	}

	public void setPclassName(String pclassName) {
		this.pclassName = pclassName;
	}

	public Set<SubClassification> getSclasses() {
		return sclasses;
	}

	public void setSclasses(Set<SubClassification> sclasses) {
		this.sclasses = sclasses;
	}
}
