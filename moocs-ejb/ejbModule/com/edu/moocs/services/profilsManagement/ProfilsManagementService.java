package com.edu.moocs.services.profilsManagement;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.edu.moocs.domain.Admin;
import com.edu.moocs.domain.Course;
import com.edu.moocs.domain.Quiz;
import com.edu.moocs.domain.Student;
import com.edu.moocs.domain.Teacher;
import com.edu.moocs.domain.User;

/**
 * Session Bean implementation class ProfilsManagementService
 */
@Stateless
public class ProfilsManagementService implements
		ProfilsManagementServiceRemote, ProfilsManagementServiceLocal {

	/**
	 * Default constructor.
	 */
	public ProfilsManagementService() {
	}

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */

	public User createUser(User user) {
		em.persist(user);
		return user;
	}

	public void updateUser(User user) {
		em.merge(user);
	}

	public Student findStudent(int id) {
		return em.find(Student.class, id);
	}

	public Teacher findTeacher(int id) {
		return em.find(Teacher.class, id);
	}

	public Admin findAdmin(int id) {
		return em.find(Admin.class, id);
	}

	public void deleteUser(User user) {
		em.remove(em.merge(user));
	}

	public List<Student> findAllStudent() {
		List<Student> students = null;
		String jpql = "select s from Student s";
		Query query = em.createQuery(jpql);
		students = query.getResultList();
		return students;
	}

	public List<Teacher> findAllTeacher() {
		List<Teacher> teachers = null;
		String jpql = "select t from Teacher t";
		Query query = em.createQuery(jpql);
		teachers = query.getResultList();
		return teachers;
	}

	public List<Admin> findAllAdmin() {
		List<Admin> admins = null;
		String jpql = "select a from Admin a";
		Query query = em.createQuery(jpql);
		admins = query.getResultList();
		return admins;
	}

	public List<User> findAllUser() {
		List<User> users = null;
		String jpql = "select u from User u";
		Query query = em.createQuery(jpql);
		users = query.getResultList();
		return users;
	}

	public User autehnticate(String login, String password) {
		User found = null;

		Query query = em
				.createQuery("select u from User u where u.login=:x and u.password=:y");
		query.setParameter("x", login);
		query.setParameter("y", password);

		try {
			found = (User) query.getSingleResult();
		} catch (Exception e) {
		}
		return found;
	}

	public boolean loginExists(String login) {
		boolean exists = false;
		String jpql = "select case when (count(u) > 0)  then true else false end from User u where u.login=:login";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		exists = (Boolean) query.getSingleResult();
		return exists;

	}

	public Student findStudent(String login, String pass) {

		Student student = null;
		String jpql = "select s from Student s where s.login=:x and s.password=:y";
		Query query = em.createQuery(jpql);
		query.setParameter("x", login);
		query.setParameter("y", pass);

		student = (Student) query.getSingleResult();

		return student;
	}

	public Student verifSubs(Student student, Course course) {
		try {
			Student student2 = null;
			String jpql = "select s from Student s where :y member of s.courses and s=:x";
			Query query = em.createQuery(jpql);
			query.setParameter("x", student);
			query.setParameter("y", course);
			student2 = (Student) query.getSingleResult();
			return student2;
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Course> findCoursesByStudent(Student student) {
		try {
			List<Course> courses = null;
			String jpql = "select c from Course c where :x member of c.students ";
			Query query = em.createQuery(jpql);
			query.setParameter("x", student);
			courses = query.getResultList();

			return courses;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Teacher findTeacher(String login, String pass) {

		try {
			Teacher teacher = null;
			String jpql = "select s from Teacher s where s.login=:x and s.password=:y";
			Query query = em.createQuery(jpql);
			query.setParameter("x", login);
			query.setParameter("y", pass);

			teacher = (Teacher) query.getSingleResult();

			return teacher;
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Quiz> findQuizByTeacher(int id) {

		List<Quiz> quizs = null;

		String jpql = "select t.quizs from Teacher t where t.id=:x";

		Query query = em.createQuery(jpql);
		query.setParameter("x", id);
		quizs = query.getResultList();

		return quizs;
	}

	public List<Student> findStudentInQuiz(Quiz quiz) {

		List<Student> students = null;
		String jpql = "select s from Student s join s.noteQuizs n where n.quiz=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x", quiz);
		students = query.getResultList();

		return students;
	}

	public int noteDeEtudent(Student student, Quiz quiz) {
		int note = 0;

		String jpql = "select n.note from NoteQuiz n where n.student=:x and n.quiz=:y";
		Query query = em.createQuery(jpql);
		query.setParameter("x", student);
		query.setParameter("y", quiz);

		note = (int) query.getSingleResult();
		return note;
	}

	public User findUserByNameAndLogin(String login, String passe) {
		try {
			User user = null;
			String jpql = "select s from User s where s.login=:x and s.password=:y";
			Query query = em.createQuery(jpql);
			query.setParameter("x", login);
			query.setParameter("y", passe);

			user = (User) query.getSingleResult();

			return user;
		} catch (NoResultException e) {
			return null;
		}
	}

}
