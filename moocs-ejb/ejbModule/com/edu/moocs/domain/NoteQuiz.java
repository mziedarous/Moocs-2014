package com.edu.moocs.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Resultat_Quiz")
public class NoteQuiz implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Student student;
	private Quiz quiz;
	
	private int note;
	private NoteQuiz_pk pk; 
	
	public NoteQuiz() {
		// TODO Auto-generated constructor stub
	}

	
    
	public NoteQuiz(Student student, Quiz quiz, int note) {
		this.getPk().setStudentId(student.getId());
		this.getPk().setQuizId(quiz.getId());
		this.note = note;
	}



	@ManyToOne
	@JoinColumn(name="student_fk",insertable = false, updatable = false)
	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}

    @ManyToOne
    @JoinColumn(name="quiz_fk", insertable = false, updatable = false)
	public Quiz getQuiz() {
		return quiz;
	}


	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}


	public int getNote() {
		return note;
	}


	public void setNote(int note) {
		this.note = note;
	}


	@EmbeddedId
	public NoteQuiz_pk getPk() {
		if(pk==null)
		{
			pk = new NoteQuiz_pk();
		}
		
		return pk;
	}


	public void setPk(NoteQuiz_pk pk) {
		this.pk = pk;
	}



	public String toString() {
		return "NoteQuiz [student=" + student + ", quiz=" + quiz + ", note="
				+ note + "]";
	}
	
}
