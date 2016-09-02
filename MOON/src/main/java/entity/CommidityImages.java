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
@Table(name = "commidity_images")
public class CommidityImages {
	@Id
	@Column(name="cimageId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cimageId;
	private String cimagePath;
	@ManyToOne(targetEntity = Commidity.class)
	@JoinColumn(name = "commidityId", referencedColumnName = "commidityId", nullable = false)
	private Commidity commidity;

	public Integer getCimageId() {
		return cimageId;
	}

	public void setCimageId(Integer cimageId) {
		this.cimageId = cimageId;
	}

	public String getCimagePath() {
		return cimagePath;
	}

	public void setCimagePath(String cimagePath) {
		this.cimagePath = cimagePath;
	}

	public Commidity getCommidity() {
		return commidity;
	}

	public void setCommidity(Commidity commidity) {
		this.commidity = commidity;
	}
}
