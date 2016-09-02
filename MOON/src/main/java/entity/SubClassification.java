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
@Table(name = "subclass")
public class SubClassification {
	@Id
	@Column(name="sclassId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sclassId;
	private String sclassName;
	@ManyToOne(targetEntity = ParentClassification.class)
	@JoinColumn(name = "pclassId", referencedColumnName = "pclassId", nullable = false)
	private ParentClassification pclass;
	@OneToMany(targetEntity = Commidity.class, mappedBy = "sclass")
	private Set<Commidity> commiditys = new HashSet<Commidity>();

	public Integer getSclassId() {
		return sclassId;
	}

	public void setSclassId(Integer sclassId) {
		this.sclassId = sclassId;
	}

	public String getSclassName() {
		return sclassName;
	}

	public void setSclassName(String sclassName) {
		this.sclassName = sclassName;
	}

	public ParentClassification getPclass() {
		return pclass;
	}

	public void setPclass(ParentClassification pclass) {
		this.pclass = pclass;
	}

	public Set<Commidity> getCommiditys() {
		return commiditys;
	}

	public void setCommiditys(Set<Commidity> commiditys) {
		this.commiditys = commiditys;
	}
}
