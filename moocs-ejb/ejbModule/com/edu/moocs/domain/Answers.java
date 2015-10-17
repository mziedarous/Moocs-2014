package com.edu.moocs.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_answers")
public class Answers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String annser1;
	private String annser2;
	private String annser3;
	private int goodAnnser;

	// relationship attributes
	private Question question;

	public Answers() {
	}

	public Answers(String annser1, String annser2, String annser3,
			int goodAnnser) {
		super();
		this.annser1 = annser1;
		this.annser2 = annser2;
		this.annser3 = annser3;
		this.goodAnnser = goodAnnser;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "question_fk")
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Answers [annser1=" + annser1 + ", annser2=" + annser2
				+ ", annser3=" + annser3 + ", goodAnnser=" + goodAnnser + "]";
	}

	public String getAnnser1() {
		return annser1;
	}

	public void setAnnser1(String annser1) {
		this.annser1 = annser1;
	}

	public String getAnnser2() {
		return annser2;
	}

	public void setAnnser2(String annser2) {
		this.annser2 = annser2;
	}

	public String getAnnser3() {
		return annser3;
	}

	public void setAnnser3(String annser3) {
		this.annser3 = annser3;
	}

	public int getGoodAnnser() {
		return goodAnnser;
	}

	public void setGoodAnnser(int goodAnnser) {
		this.goodAnnser = goodAnnser;
	}
}
