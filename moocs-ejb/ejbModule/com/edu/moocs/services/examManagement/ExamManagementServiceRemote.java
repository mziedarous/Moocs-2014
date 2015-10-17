package com.edu.moocs.services.examManagement;


import java.util.List;

import javax.ejb.Remote;

import com.edu.moocs.domain.Admin;
import com.edu.moocs.domain.Answers;
import com.edu.moocs.domain.Certification;
import com.edu.moocs.domain.Chapter;
import com.edu.moocs.domain.Contain;
import com.edu.moocs.domain.Course;
import com.edu.moocs.domain.Image;
import com.edu.moocs.domain.Pdf;
import com.edu.moocs.domain.Question;
import com.edu.moocs.domain.Quiz;
import com.edu.moocs.domain.StaticText;
import com.edu.moocs.domain.Student;
import com.edu.moocs.domain.Teacher;
import com.edu.moocs.domain.Thematic;
import com.edu.moocs.domain.User;
import com.edu.moocs.domain.Video;

@Remote
public interface ExamManagementServiceRemote {
	public void createCertification(Certification certification);
	public void createQuiz(Quiz quiz);
	public void createQuestion(Question question);
	public void createAnswers(Answers answers);
	
	public void updateCertification(Certification certification);
	public void updateQuiz(Quiz quiz);
	public void updateQuestion(Question question);
	public void updateAnswers(Answers answers);
	
	public Certification findCertification( int id);
	public Quiz findQuiz(int id );
	public Question findQuestion(int id);
	public Answers findAnswers(int id);
	public Quiz findQuizByName(String nameQuiz);
	
	
	public void deleteCertification(Certification certification);
	public void deleteQuiz(Quiz quiz);
	public void deleteQuestion(Question question);
	public void deleteAnswers(Answers answers);
	
	
	public List<Certification> findAllCertification();
	public List<Quiz> findAllQuiz();
	public List<Question> findAllQuestion();
	public List<Answers> findAllAnswers();
	public List<Answers> getAnswersByQuestion(Question question);
	
	public List<Question> findAllQuestionByChap(Chapter chapter);
	public List<Answers> finAllAnserwerByQuestion(Question question);
	public boolean certifExist(String thematic);
	public List<Question> findAllQuestionByCertification(Certification certification);
	public List<Question> findAllQuestionByThematic(String thematic);
	public List<Question> findAllQuestionByThematic2(String name);
}
