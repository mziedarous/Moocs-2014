package com.edu.moocs.moocs.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.edu.pdev.moocs.domain.Quiz;
import tn.edu.pdev.moocs.domain.Student;
import tn.edu.pdev.moocs.domain.Teacher;
import tn.edu.pdev.moocs.domain.User;
import tn.edu.pdev.moocs.services.examManagement.ExamManagementServiceLocal;
import tn.edu.pdev.moocs.services.profilsManagement.ProfilsManagementServiceLocal;

@ManagedBean(name="teaBean")
@SessionScoped
public class TeacherBeen implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Teacher teacher;
	private List<Quiz> quizs2;

	@EJB
	private ProfilsManagementServiceLocal profilsManagementServiceLocal;
	
	@EJB
	private ExamManagementServiceLocal examManagementServiceLocal;
	
	@PostConstruct
	public void initModel() {
		user = new User();
		teacher = new Teacher();
		quizs2 = new ArrayList<Quiz>();
	}

	public String doSeeResult(){
		
		String navTo = "";
		teacher = profilsManagementServiceLocal.findTeacher(user.getLogin(), user.getPassword());
		//System.out.println(teacher);
		int note = 0;
	//	System.out.println("hey");
		setQuizs2(profilsManagementServiceLocal.findQuizByTeacher(teacher.getId()));
		


//		for (Quiz quiz : quizs2) {
//			System.out.println(quiz);
//			List<Student> students2 = profilsManagementServiceLocal.findStudentInQuiz(quiz);
//	        for (Student student : students2) {
//				System.out.println(student.getFirstName());
//				note = profilsManagementServiceLocal.noteDeEtudent(student, quiz);
//				System.out.println(note);
//			}
//		}
	
		navTo="/pages/teacher/resultatEtudiant/viewOfStudentResult";
	return navTo;
	}
	
	public List<Student> doSeeStudentResult(Quiz quiz){
		
		List<Student> students = profilsManagementServiceLocal.findStudentInQuiz(quiz);
		return students;
	}

	public int noteOfStudent(Student student,Quiz quiz){
		
		int res = 0;
		
		res = profilsManagementServiceLocal.noteDeEtudent(student, quiz);
		
		return res;
		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Quiz> getQuizs2() {
		return quizs2;
	}

	public void setQuizs2(List<Quiz> quizs2) {
		this.quizs2 = quizs2;
	}
	


}
