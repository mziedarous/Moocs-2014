package com.edu.moocs.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Classe Entité Teacher extends User
 *
 * 
 */
@Entity
public class Teacher extends User implements Serializable {


	private static final long serialVersionUID = 1L;

	// teacher information
	private String diploma;
	// relationship attributes
	private List<Course> courses;
	private List<Quiz> quizs;
	// default constructor
	public Teacher() {
	}

	public Teacher(String firstName, String lastName, Long cin,
			Date dateOfBirth, String sexe, String mail, String login,
			String password, String diploma) {
		super(firstName, lastName, cin, dateOfBirth, sexe, mail, login,
				password);
		this.diploma = diploma;
	}

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	@OneToMany(mappedBy = "teacher", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE })
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@OneToMany(mappedBy = "teacher")
	public List<Quiz> getQuizs() {
		return quizs;
	}

	public void setQuizs(List<Quiz> quizs) {
		this.quizs = quizs;
	}

	public String toString() {
		return "Teacher [diploma=" + diploma + ", getId()=" + getId()
				+ ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getCin()=" + getCin()
				+ ", getDateOfBirth()=" + getDateOfBirth() + ", getSexe()="
				+ getSexe() + ", getMail()=" + getMail() + ", getLogin()="
				+ getLogin() + ", getPassword()=" + getPassword()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
