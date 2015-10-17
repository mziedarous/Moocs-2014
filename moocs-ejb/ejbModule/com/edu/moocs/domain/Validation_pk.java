package com.edu.moocs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Validation_pk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int studentId;
	private int certificationId;
	
	public Validation_pk() {
		// TODO Auto-generated constructor stub
	}

	@Column(name="student_fk")
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Column(name="certification_fk")
	public int getCertificationId() {
		return certificationId;
	}

	public void setCertificationId(int certificationId) {
		this.certificationId = certificationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + certificationId;
		result = prime * result + studentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Validation_pk other = (Validation_pk) obj;
		if (certificationId != other.certificationId)
			return false;
		if (studentId != other.studentId)
			return false;
		return true;
	}
	
	
	
}
