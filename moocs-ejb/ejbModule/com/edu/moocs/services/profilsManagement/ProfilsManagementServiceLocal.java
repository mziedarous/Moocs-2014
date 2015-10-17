package com.edu.moocs.services.profilsManagement;

import java.util.List;

import javax.ejb.Local;

import com.edu.moocs.domain.Admin;
import com.edu.moocs.domain.Course;
import com.edu.moocs.domain.Quiz;
import com.edu.moocs.domain.Student;
import com.edu.moocs.domain.Teacher;
import com.edu.moocs.domain.User;

@Local
public interface ProfilsManagementServiceLocal {

	User createUser(User user);

	void updateUser(User user);

	Student findStudent(int id);

	Teacher findTeacher(int id);

	Admin findAdmin(int id);

	void deleteUser(User user);

	List<Student> findAllStudent();

	List<Teacher> findAllTeacher();

	List<Admin> findAllAdmin();

	List<User> findAllUser();

	User autehnticate(String login, String password);

	boolean loginExists(String login);

	Student findStudent(String login, String pass);

	Teacher findTeacher(String login, String pass);

	Student verifSubs(Student student, Course course);

	List<Course> findCoursesByStudent(Student student);

	List<Quiz> findQuizByTeacher(int id);

	List<Student> findStudentInQuiz(Quiz quiz);

	int noteDeEtudent(Student student, Quiz quiz);

	User findUserByNameAndLogin(String login, String passe);
}
