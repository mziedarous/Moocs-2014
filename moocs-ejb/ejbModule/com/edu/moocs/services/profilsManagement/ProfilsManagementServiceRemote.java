package com.edu.moocs.services.profilsManagement;

import java.util.List;

import javax.ejb.Remote;

import com.edu.moocs.domain.Admin;
import com.edu.moocs.domain.Student;
import com.edu.moocs.domain.Teacher;
import com.edu.moocs.domain.User;

@Remote
public interface ProfilsManagementServiceRemote {

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

}
