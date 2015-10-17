package com.edu.moocs.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_validation")
public class Validation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Student student;
	private Certification certification;
	private int result;
	
	private Validation_pk pk;
	
	public Validation() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Validation(Student student, Certification certification, int result) {
		
		
		this.getPk().setStudentId(student.getId());
		this.getPk().setCertificationId(certification.getId());
		this.result = result;
	}



	@ManyToOne
	@JoinColumn(name="student_fk" , insertable = false, updatable = false)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@ManyToOne
	@JoinColumn(name = "certification_fk" , insertable = false, updatable = false)
	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	@EmbeddedId
	public Validation_pk getPk() {
		
		if(pk ==null){
			pk = new Validation_pk();
		}
		return pk;
	}

	public void setPk(Validation_pk pk) {
		this.pk = pk;
	}



	public int getResult() {
		return result;
	}



	public void setResult(int result) {
		this.result = result;
	}
}
