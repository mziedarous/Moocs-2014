package com.edu.moocs.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "t_admin")
public class Admin extends User implements Serializable{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pseudo;

	public Admin() {
	}
	
	public Admin(String firstName, String lastName, Long cin,
			Date dateOfBirth, String sexe, String mail, String login,
			String password,String pseudo) {
		super(firstName, lastName, cin, dateOfBirth, sexe, mail, login, password);
		this.pseudo=pseudo;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String toString() {
		return "Admin [pseudo=" + pseudo + ", getId()=" + getId()
				+ ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getCin()=" + getCin()
				+ ", getDateOfBirth()=" + getDateOfBirth() + ", getSexe()="
				+ getSexe() + ", getMail()=" + getMail() + ", getLogin()="
				+ getLogin() + ", getPassword()=" + getPassword()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
	
}
