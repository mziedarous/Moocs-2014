package com.edu.moocs.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_certification")
public class Certification implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date dateCertif;
	private float timeCertif;

	private Thematic thematic;
	private List<Question> questions;

	private List<Validation> validations;

	public Certification() {
	}

	public Certification(Date dateCertif, float timeCertif) {

		this.dateCertif = dateCertif;
		this.timeCertif = timeCertif;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "date_certif")
	public Date getDateCertif() {
		return dateCertif;
	}

	public void setDateCertif(Date dateCeritf) {
		this.dateCertif = dateCeritf;
	}

	@Column(name = "time_certif")
	public float getTimeCertif() {
		return timeCertif;
	}

	public void setTimeCertif(float timeCertif) {
		this.timeCertif = timeCertif;
	}

	public String toString() {
		return "Certification [id=" + id + ", dateCeritf=" + dateCertif
				+ ", timeCertif=" + timeCertif + "]";
	}

	@OneToOne(mappedBy = "certification", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE })
	public Thematic getThematic() {
		return thematic;
	}

	public void setThematic(Thematic thematic) {
		this.thematic = thematic;
	}

	@OneToMany(mappedBy = "certification")
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@OneToMany(mappedBy = "certification")
	public List<Validation> getValidations() {
		return validations;
	}

	public void setValidations(List<Validation> validations) {
		this.validations = validations;
	}

}
