package com.edu.moocs.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "t_quiz")
public class Quiz implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id ;
	private int duration ;
	private Date dateOfExam;
	private String nameQuiz;
	
	private Teacher teacher;
	private List<Question> questions;
	private Chapter chapter;
	
	private List<NoteQuiz> noteQuizs;

	public Quiz() {
		
	}
	
	
	public Quiz(int duration, Date dateOfExam,String nameQuiz) {
		this.duration = duration;
		this.dateOfExam = dateOfExam;
		this.nameQuiz = nameQuiz;
	}


	@Id 
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameQuiz() {
		return nameQuiz;
	}


	public void setNameQuiz(String nameQuiz) {
		this.nameQuiz = nameQuiz;
	}


	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Column(name = "date_exam")
	public Date getDateOfExam() {
		return dateOfExam;
	}
	public void setDateOfExam(Date dateOfExam) {
		this.dateOfExam = dateOfExam;
	}
	@ManyToOne
	@JoinColumn(name ="teacher_fk")
	public Teacher getTeacher() {
		return teacher;
	}



	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	@OneToMany(mappedBy = "quiz",cascade= {CascadeType.PERSIST , CascadeType.REMOVE})
	public List<Question> getQuestions() {
		return questions;
	}



	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	

	@OneToOne
	@JoinColumn( name = "chapter_fk")
	public Chapter getChapter() {
		return chapter;
	}



	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}





    @Override
	public String toString() {
		return "Quiz [id=" + id + ", duration=" + duration + ", dateOfExam="
				+ dateOfExam + ", nameQuiz=" + nameQuiz + "]";
	}


	@OneToMany(mappedBy="quiz")
	public List<NoteQuiz> getNoteQuizs() {
		return noteQuizs;
	}


	public void setNoteQuizs(List<NoteQuiz> noteQuizs) {
		this.noteQuizs = noteQuizs;
	}
	
	
	

}
