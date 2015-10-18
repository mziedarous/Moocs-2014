package com.edu.moocs.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_thematic")
public class Thematic implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private int id;
	private String nameThematic;

	// relationship attributes
	private List<Course> courses;
	private Certification certification;

	public Thematic() {
	}

	public Thematic(String nameThematic) {
		this.nameThematic = nameThematic;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name_thematic")
	public String getNameThematic() {
		return nameThematic;
	}

	public void setNameThematic(String nameThematic) {
		this.nameThematic = nameThematic;
	}

	@OneToMany(mappedBy = "thematic", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE })
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Thematic(String nameThematic, List<Course> courses) {
		this.nameThematic = nameThematic;
		this.courses = courses;
	}

	@OneToOne
	@JoinColumn(name = "certification_fk")
	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	@Override
	public String toString() {
		return "Thematic [id=" + id + ", nameThematic=" + nameThematic + "]";
	}

}
