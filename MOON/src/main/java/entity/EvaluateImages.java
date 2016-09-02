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
@Table(name = "evaluate_images")
public class EvaluateImages {
	@Id
	@Column(name="eimageId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eimageId;
	private String eimagePath;
	@ManyToOne(targetEntity = Evaluate.class)
	@JoinColumn(name = "evaluateId", referencedColumnName = "evaluateId", nullable = false)
	private Evaluate evaluate;

	public Integer getEimageId() {
		return eimageId;
	}

	public void setEimageId(Integer eimageId) {
		this.eimageId = eimageId;
	}

	public String getEimagePath() {
		return eimagePath;
	}

	public void setEimagePath(String eimagePath) {
		this.eimagePath = eimagePath;
	}

	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}

}
