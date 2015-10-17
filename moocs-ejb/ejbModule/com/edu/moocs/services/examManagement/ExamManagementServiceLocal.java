package com.edu.moocs.services.examManagement;

import java.util.List;

import javax.ejb.Local;

import com.edu.moocs.domain.Admin;
import com.edu.moocs.domain.Answers;
import com.edu.moocs.domain.Certification;
import com.edu.moocs.domain.Chapter;
import com.edu.moocs.domain.Contain;
import com.edu.moocs.domain.Course;
import com.edu.moocs.domain.Image;
import com.edu.moocs.domain.NoteQuiz;
import com.edu.moocs.domain.Pdf;
import com.edu.moocs.domain.Question;
import com.edu.moocs.domain.Quiz;
import com.edu.moocs.domain.StaticText;
import com.edu.moocs.domain.Student;
import com.edu.moocs.domain.Teacher;
import com.edu.moocs.domain.Thematic;
import com.edu.moocs.domain.User;
import com.edu.moocs.domain.Video;

@Local
public interface ExamManagementServiceLocal {
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
	public Answers finAllAnserwerByQuestion2(Question question);
	
	
	public void noteQuizStudent(Student student , Quiz quiz , int note);
    public Quiz findQuizByChapter(Chapter chapter);
    public List<Quiz>findQuizsByChapter(Chapter chapter);
    public List<Quiz>findQuizsByTeacher(Teacher teacher);
    
    public Long numberOfQuizInThematic(Thematic thematic);
    public List<Quiz> findQuizByThematic(Thematic thematic);
    public NoteQuiz numberOfStudentPassedQuiz(Student student, Quiz quiz);
    public List<Question> findAllQuestionByThematic(String thematic);
    public List<Question> findAllQuestionByThematic2(String name);
    public List<Question> findAllQuestionByCertification(Certification certification);
    public boolean certifExist(String thematic);
    public Certification finfCertificationByThematic(String thematic);
    public Long findNumberOfQuiz(Teacher teacher);
    

}
