package com.edu.moocs.services.examManagement;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.edu.moocs.domain.Answers;
import com.edu.moocs.domain.Certification;
import com.edu.moocs.domain.Chapter;
import com.edu.moocs.domain.NoteQuiz;
import com.edu.moocs.domain.Question;
import com.edu.moocs.domain.Quiz;
import com.edu.moocs.domain.Student;
import com.edu.moocs.domain.Teacher;
import com.edu.moocs.domain.Thematic;

/**
 * Session Bean implementation class MoocsService
 */
@Stateless
public class ExamManagementService implements ExamManagementServiceRemote, ExamManagementServiceLocal {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ExamManagementService() {
    }







	public void createCertification(Certification certification) {
		em.persist(certification);
	}



	public void createQuiz(Quiz quiz) {
		em.persist(quiz);
	}

	public void createQuestion(Question question) {
		em.persist(question);
	}

	public void createAnswers(Answers answers) {
		em.persist(answers);
	}






	public void updateCertification(Certification certification) {
		em.merge(certification);
	}



	public void updateQuiz(Quiz quiz) {
		em.merge(quiz);	
	}

	public void updateQuestion(Question question) {
		em.merge(question);
	}

	public void updateAnswers(Answers answers) {
		em.merge(answers);
	}





	public Certification findCertification(int id) {
		return em.find(Certification.class, id);
	}



	public Quiz findQuiz(int id) {
		return em.find(Quiz.class, id);
	}
	public Quiz findQuizByName(String nameQuiz){
		return em.find(Quiz.class, nameQuiz);
	}

	public Question findQuestion(int id) {
		return em.find(Question.class, id);
	}

	public Answers findAnswers(int id) {
		return em.find(Answers.class, id);
	}

	

	public void deleteCertification(Certification certification) {
		em.remove(em.merge(certification));
	}



	public void deleteQuiz(Quiz quiz) {
		em.remove(em.merge(quiz));
	}

	public void deleteQuestion(Question question) {
		em.remove(em.merge(question));
	}

	public void deleteAnswers(Answers answers) {
		em.remove(em.merge(answers));
	}



	
	public List<Certification> findAllCertification() {
		List<Certification> certifications = null;
		String jpql = "select c from Certification c";
		Query query = em.createQuery(jpql);
		certifications = query.getResultList();
		return certifications;
	}



	public List<Quiz> findAllQuiz() {
		List<Quiz> quizs = null;
		String jpql = "select q from Quiz q";
		Query query = em.createQuery(jpql);
		quizs = query.getResultList();
		return quizs;
	}

	public List<Question> findAllQuestion() {
		List<Question> questions = null;
		String jpql = "select q from Question q";
		Query query = em.createQuery(jpql);
		questions = query.getResultList();
		return questions;
	}

	public List<Answers> findAllAnswers() {
		List<Answers> answers = null;
		String jpql = "select a from Answers a";
		Query query = em.createQuery(jpql);
		answers = query.getResultList();
		return answers;
	}
	public List<Answers> getAnswersByQuestion(Question question) {
		List<Answers> answerss = null;
	Query query= em.createQuery("select e from Answers e where e.question=:d");
	query.setParameter("d", question);
		answerss= query.getResultList();
		return answerss;
	}





     public List<Question> findAllQuestionByChap(Chapter chapter) {
		
		List<Question> questions = null;
		
		String jpql = "select q from Question q join q.quiz u where u.chapter=:x ORDER BY RAND()";
		
		Query query = em.createQuery(jpql);
		
		query.setParameter("x", chapter);
		query.setMaxResults(3);
		questions = query.getResultList();
		
		return questions;
	}



	public List<Answers> finAllAnserwerByQuestion(Question question) {
 
		List<Answers> answers = null;
		
		String jpql = "select a from Answers a where a.question=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x", question);
		answers = query.getResultList();
		
		return answers;
	}



	public Answers finAllAnserwerByQuestion2(Question question) {
		Answers answers = null;
		
		String jpql = "select a from Answers a where a.question=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x", question);
		answers = (Answers) query.getSingleResult();
		return answers;
	}





	public void noteQuizStudent(Student student, Quiz quiz, int note) {

		NoteQuiz noteQuiz = new NoteQuiz(student, quiz, note);
		em.merge(noteQuiz);
	}







	public Quiz findQuizByChapter(Chapter chapter) {
		
		Quiz quiz = null;
		String jpql = "select q from Quiz q where q.chapter=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x", chapter);
		quiz = (Quiz) query.getSingleResult();
		return quiz;
	}







	public Long numberOfQuizInThematic(Thematic thematic) {

		Long s= null;
		String jpql = "select count(q) from Quiz q join q.chapter ch join ch.course c where c.thematic=:x ";
		Query query = em.createQuery(jpql);
		query.setParameter("x", thematic);
		s= (Long) query.getSingleResult();
		
		return s;
	}







 
	public List<Quiz> findQuizByThematic(Thematic thematic) {
		List<Quiz> quizs = null;
		String jpql = "select q from Quiz q join q.chapter ch join ch.course c where c.thematic=:x ";
		Query query = em.createQuery(jpql);
		query.setParameter("x", thematic);
	    quizs=query.getResultList();   
		
		return quizs;
	}







	public NoteQuiz numberOfStudentPassedQuiz(Student student, Quiz quiz) {

		try
		{
		NoteQuiz n = null;
		
		String jpql = "select u from NoteQuiz u where u.student=:x and u.quiz=:y";
		Query query = em.createQuery(jpql);
		query.setParameter("x", student);
		query.setParameter("y", quiz);
		n=(NoteQuiz) query.getSingleResult();
		
		return n;
	  } catch(NoResultException e) {
	        return null;
	    }
	}







	public List<Question> findAllQuestionByThematic(String thematic) {

		List<Question> questions = null;
		String jpql = "select distinct q from Question q join q.quiz qu join qu.teacher t join t.courses c where c.thematic.nameThematic=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x", thematic);
		questions = query.getResultList();
		
		return questions;
	}








	public List<Question> findAllQuestionByThematic2(String name) {

		List<Question> questions = null;
		String jpql = "select q from Question q join q.certification c where c.thematic.nameThematic=:x";
		Query query = em.createQuery(jpql);
     	query.setParameter("x", name);
		questions = query.getResultList();
		
		return questions;
	}







	public boolean certifExist(String thematic) {

		boolean exist = false;
		String jpql = "select case when(count(c)>0) then true else false end from Certification c where c.thematic.nameThematic=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x", thematic);
		exist = (boolean) query.getSingleResult();
		
		return exist;
	}







	public Certification finfCertificationByThematic(String thematic) {

		try
		{
     Certification certification = null;
     String jpql = "select c from Certification c where c.thematic.nameThematic=:x";
     Query query = em.createQuery(jpql);
     query.setParameter("x", thematic);
     certification = (Certification) query.getSingleResult();
		
		return certification;
		}
		catch(NoResultException noResultException)
		{
			return null;
		}
	}







	public List<Question> findAllQuestionByCertification(
			Certification certification) {
	
		List<Question> questions =null;
		
		String jpql = "select q from Question q where q.certification=:x";
		
		Query query = em.createQuery(jpql);
		
		query.setParameter("x", certification);
		questions = query.getResultList();
		
		return questions;
	}







	public List<Quiz> findQuizsByChapter(Chapter chapter) {
		
		List<Quiz> quiz = null;
		String jpql = "select q from Quiz q where q.chapter=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x", chapter);
		quiz = query.getResultList();
		return quiz;
		
		
	}




	public Long findNumberOfQuiz(Teacher teacher) {
	      
		Long number = null;
		String jpql = "select count(q) from Quiz q where q.teacher=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x", teacher);
		number = (Long) query.getSingleResult();
		
		return number;
	}







	
	public List<Quiz> findQuizsByTeacher(Teacher teacher) {
		
		List<Quiz> quizs =null;
		String jpql = "select q from Quiz q where q.teacher=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x", teacher);
		quizs=query.getResultList();
		
		return quizs;
	}


















	
}
