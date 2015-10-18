package com.edu.moocs.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/*
 * Class Entity Student extends User
 */
@Entity
public class Student extends User implements Serializable {


	private static final long serialVersionUID = 1L;
	// Student information
	private String education;
	// Relationship attributes
	private List<Course> courses;
	private List<NoteQuiz> noteQuizs;
	private List<Validation> validations;

	// Default constructor
	public Student() {
	}

	public Student(String firstName, String lastName, Long cin,
			Date dateOfBirth, String sexe, String mail, String login,
			String password, String education) {
		super(firstName, lastName, cin, dateOfBirth, sexe, mail, login,
				password);

		this.education = education;

	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@ManyToMany
	@JoinTable(name = "t_Student_Course", joinColumns = { @JoinColumn(name = "student_fk") }, inverseJoinColumns = { @JoinColumn(name = "course_fk") })
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String toString() {
		return "Student [education=" + education + ", getId()=" + getId()
				+ ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getCin()=" + getCin()
				+ ", getDateOfBirth()=" + getDateOfBirth() + ", getSexe()="
				+ getSexe() + ", getMail()=" + getMail() + ", getLogin()="
				+ getLogin() + ", getPassword()=" + getPassword()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	@OneToMany(mappedBy = "student")
	public List<NoteQuiz> getNoteQuizs() {
		return noteQuizs;
	}

	public void setNoteQuizs(List<NoteQuiz> noteQuizs) {
		this.noteQuizs = noteQuizs;
	}

	@OneToMany(mappedBy = "student")
	public List<Validation> getValidations() {
		return validations;
	}

	public void setValidations(List<Validation> validations) {
		this.validations = validations;
	}

}
