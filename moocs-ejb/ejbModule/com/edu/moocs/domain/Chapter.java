package com.edu.moocs.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_chapter")
/**
 * 
 * @author hp
 *
 */
public class Chapter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private boolean hasQuiz;
	// relationShip attribuates

	private Course course;
	private Quiz quiz;

	private List<Contain> contains;

	public Chapter() {
	}

	public Chapter(String name, boolean hasQuiz) {
		this.name = name;
		this.hasQuiz = hasQuiz;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "course_fk")
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@OneToMany(mappedBy = "chapter", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE })
	public List<Contain> getContains() {
		return contains;
	}

	public void setContains(List<Contain> contains) {
		this.contains = contains;
	}

	@OneToOne(mappedBy = "chapter", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE })
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHasQuiz() {
		return hasQuiz;
	}

	public void setHasQuiz(boolean hasQuiz) {
		this.hasQuiz = hasQuiz;
	}

	public String toString() {
		return "Chapter [name=" + name + ", hasQuiz=" + hasQuiz + "]";
	}

}
