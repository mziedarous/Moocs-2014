package com.edu.moocs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NoteQuiz_pk implements Serializable {


	private static final long serialVersionUID = 1L;
	private int studentId;
	private int quizId;

	public NoteQuiz_pk() {
	}

	@Column(name = "student_fk")
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Column(name="quiz_fk")
	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + quizId;
		result = prime * result + studentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoteQuiz_pk other = (NoteQuiz_pk) obj;
		if (quizId != other.quizId)
			return false;
		if (studentId != other.studentId)
			return false;
		return true;
	}

	
	
}
