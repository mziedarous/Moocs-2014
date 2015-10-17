package com.edu.moocs.services.profilsManagement;

import java.util.List;

import javax.ejb.Remote;

import com.edu.moocs.domain.Admin;
import com.edu.moocs.domain.Course;
import com.edu.moocs.domain.Quiz;
import com.edu.moocs.domain.Student;
import com.edu.moocs.domain.Teacher;
import com.edu.moocs.domain.User;

@Remote
public interface ProfilsManagementServiceRemote {

	public User createUser(User user);
	
	public void updateUser(User user);
	
	public Student findStudent(int id);
	public Teacher findTeacher(int id);
	public Admin findAdmin(int id);
	
	public void deleteUser(User user);
	
	public List<Student> findAllStudent();
	public List<Teacher> findAllTeacher();
	public List<Admin> findAllAdmin();
	public List<User> findAllUser();
	
	User autehnticate(String login,String password);
	

}
