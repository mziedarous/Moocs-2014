package com.edu.moocs.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * Class Entity Course 
 */

@Entity
@Table(name = "t_course")
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	// Course information
	private int id;
	private String courseName;

	// relationship attributes
	private Teacher teacher;
	private Thematic thematic;
	private List<Student> students;
	private List<Chapter> chapters;
	private PdfCourse pdfCourse;

	//
	private List<Comment> comments;

	// default constructor
	public Course() {
	}

	// parameterized constructors
	public Course(String courseName) {
		this.courseName = courseName;
	}

	// primery key
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "course_name")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@ManyToOne
	@JoinColumn(name = "teacher_fk")
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@ManyToOne
	@JoinColumn(name = "thematic_fk")
	public Thematic getThematic() {
		return thematic;
	}

	public void setThematic(Thematic thematic) {
		this.thematic = thematic;
	}

	@ManyToMany(mappedBy = "courses", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE })
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = {
			CascadeType.PERSIST, CascadeType.REMOVE })
	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	@Override
	public String toString() {
		return "Course [courseName=" + courseName + "]";
	}

	@OneToOne
	@JoinColumn(name = "pdfcourse_fk", unique = true)
	public PdfCourse getPdfCourse() {
		return pdfCourse;
	}

	public void setPdfCourse(PdfCourse pdfCourse) {
		this.pdfCourse = pdfCourse;
	}

	@OneToMany(mappedBy = "course")
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
