package com.edu.moocs.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe Entité for all users MOOCs
 * 
 */

@Entity
@Table(name = "t_user")
@NamedQuery(name = "all-uses", query = "select u from User u")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Users information
	private int id; // User ID
	private String firstName;
	private String lastName;
	private Long cin;
	private Date dateOfBirth;
	private String Sexe;
	private String mail;
	private String login;
	private String password;
	private boolean act;

	private List<Comment> comments;

	// default constructors
	public User() {
	}

	// parameterized constructors

	public User(String firstName, String lastName, Long cin, Date dateOfBirth,
			String sexe, String mail, String login, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.cin = cin;
		this.dateOfBirth = dateOfBirth;
		Sexe = sexe;
		this.mail = mail;
		this.login = login;
		this.password = password;
	}

	// primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// Name Column of firstName in the DB
	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// Name Column of lastName in the DB
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getCin() {
		return cin;
	}

	public void setCin(Long cin) {
		this.cin = cin;
	}

	// Name dateOfBirth of lastName in the DB
	@Column(name = "date_of_birth")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSexe() {
		return Sexe;
	}

	public void setSexe(String sexe) {
		Sexe = sexe;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", cin=" + cin + ", dateOfBirth=" + dateOfBirth
				+ ", Sexe=" + Sexe + ", mail=" + mail + ", login=" + login
				+ ", password=" + password + "]";
	}

	public boolean isAct() {
		return act;
	}

	public void setAct(boolean act) {
		this.act = act;
	}

	@OneToMany(mappedBy = "user")
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
