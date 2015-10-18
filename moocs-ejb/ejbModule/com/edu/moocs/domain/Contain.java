package com.edu.moocs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "t_contain")
@NamedQuery(name = "all-contains", query = "select c from Contain c")
public class Contain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String typeContain;

	// relationship attributes
	private Chapter chapter;

	public Contain() {
	}

	public Contain(String typeContain) {
		this.typeContain = typeContain;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "type_contain")
	public String getTypeContain() {
		return typeContain;
	}

	public void setTypeContain(String typeContain) {
		this.typeContain = typeContain;
	}

	@ManyToOne
	@JoinColumn(name = "chapter_fk")
	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public String toString() {
		return "Contain [typeContain=" + typeContain + "]";
	}

}
