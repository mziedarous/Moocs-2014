package com.edu.moocs.services.examManagement;

import java.util.List;

import javax.ejb.Local;

import com.edu.moocs.domain.Answers;
import com.edu.moocs.domain.Certification;
import com.edu.moocs.domain.Chapter;
import com.edu.moocs.domain.NoteQuiz;
import com.edu.moocs.domain.Question;
import com.edu.moocs.domain.Quiz;
import com.edu.moocs.domain.Student;
import com.edu.moocs.domain.Teacher;
import com.edu.moocs.domain.Thematic;

@Local
public interface ExamManagementServiceLocal {
	
	void createCertification(Certification certification);

	void createQuiz(Quiz quiz);

	void createQuestion(Question question);

	void createAnswers(Answers answers);

	void updateCertification(Certification certification);

	void updateQuiz(Quiz quiz);

	void updateQuestion(Question question);

	void updateAnswers(Answers answers);

	Certification findCertification(int id);

	Quiz findQuiz(int id);

	Question findQuestion(int id);

	Answers findAnswers(int id);

	Quiz findQuizByName(String nameQuiz);

	void deleteCertification(Certification certification);

	void deleteQuiz(Quiz quiz);

	void deleteQuestion(Question question);

	void deleteAnswers(Answers answers);

	List<Certification> findAllCertification();

	List<Quiz> findAllQuiz();

	List<Question> findAllQuestion();

	List<Answers> findAllAnswers();

	List<Answers> getAnswersByQuestion(Question question);

	List<Question> findAllQuestionByChap(Chapter chapter);

	List<Answers> finAllAnserwerByQuestion(Question question);

	Answers finAllAnserwerByQuestion2(Question question);

	void noteQuizStudent(Student student, Quiz quiz, int note);

	Quiz findQuizByChapter(Chapter chapter);

	List<Quiz> findQuizsByChapter(Chapter chapter);

	List<Quiz> findQuizsByTeacher(Teacher teacher);

	Long numberOfQuizInThematic(Thematic thematic);

	List<Quiz> findQuizByThematic(Thematic thematic);

	NoteQuiz numberOfStudentPassedQuiz(Student student, Quiz quiz);

	List<Question> findAllQuestionByThematic(String thematic);

	List<Question> findAllQuestionByThematic2(String name);

	List<Question> findAllQuestionByCertification(Certification certification);

	boolean certifExist(String thematic);

	Certification finfCertificationByThematic(String thematic);

	Long findNumberOfQuiz(Teacher teacher);

}
