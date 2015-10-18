package com.edu.moocs.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_question")
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int numQuestion;
	private String nameQuestion;
	private String questionQuiz;

	// relationship attributes
	private Quiz quiz;
	private List<Answers> answers;
	private Certification certification;

	public Question() {
	}

	public Question(int numQuestion, String nameQuestion) {
		this.numQuestion = numQuestion;
		this.nameQuestion = nameQuestion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameQuestion() {
		return nameQuestion;
	}

	public void setNameQuestion(String nameQuestion) {
		this.nameQuestion = nameQuestion;
	}

	@Column(name = "num_question")
	public int getNumQuestion() {
		return numQuestion;
	}

	public void setNumQuestion(int numQuestion) {
		this.numQuestion = numQuestion;
	}

	@ManyToOne
	@JoinColumn(name = "quiz_fk")
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = {
			CascadeType.PERSIST, CascadeType.REMOVE })
	public List<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answers> answers) {
		this.answers = answers;
	}

	public void addQuestionToAnswer(Answers answers) {
		answers.setQuestion(this);
		this.getAnswers().add(answers);
	}

	public void addQuestionToAnswers(List<Answers> answerss) {
		for (Answers answers : answerss) {
			answers.setQuestion(this);
			this.getAnswers().add(answers);
		}
	}

	public String getQuestionQuiz() {
		return questionQuiz;
	}

	public void setQuestionQuiz(String questionQuiz) {
		this.questionQuiz = questionQuiz;
	}

	public String toString() {
		return "Question [id=" + id + ", numQuestion=" + numQuestion + "]";
	}

	@ManyToOne
	@JoinColumn(name = "certification_fk")
	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

}
