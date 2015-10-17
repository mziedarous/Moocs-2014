package com.edu.moocs.moocs.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import tn.edu.pdev.moocs.domain.Chapter;
import tn.edu.pdev.moocs.domain.Course;
import tn.edu.pdev.moocs.domain.Quiz;
import tn.edu.pdev.moocs.domain.Teacher;
import tn.edu.pdev.moocs.domain.User;
import tn.edu.pdev.moocs.services.courseManagement.CouseManagementLocal;
import tn.edu.pdev.moocs.services.examManagement.ExamManagementServiceLocal;
import tn.edu.pdev.moocs.services.profilsManagement.ProfilsManagementServiceLocal;

@ManagedBean(name = "quizBean")
@SessionScoped
public class QuizAffectionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	private Teacher teacher;
	private List<Course> courses;
	private List<Course> filtredCourses;
	private Course course;
	private List<Chapter> chapters;
	private List<Chapter> filtredChapter;
	private Chapter chapter;
	private List<Quiz> quizs;
	private List<Quiz> filtredQuiz;
	private Quiz quiz;

	private boolean selected1;
	private boolean selected2;
	private boolean selected3;

	@EJB
	private ProfilsManagementServiceLocal profilsManagementServiceLocal;

	@EJB
	private CouseManagementLocal couseManagementLocal;

	@EJB
	private ExamManagementServiceLocal examManagementServiceLocal;

	@PostConstruct
	public void initModel() {
		user = new User();
		teacher = new Teacher();
		course = new Course();
		chapter = new Chapter();
		courses = new ArrayList<Course>();
		chapters = new ArrayList<Chapter>();
		teacher = profilsManagementServiceLocal.findTeacher(user.getLogin(),
				user.getPassword());
		courses = couseManagementLocal.findAllCourseByTeacher(teacher);

		selected1 = true;
		selected2 = true;
		selected3 = true;

	}

	public boolean showAllThematicOfThisTeacher() {

		teacher = profilsManagementServiceLocal.findTeacher(user.getLogin(),
				user.getPassword());
		courses = couseManagementLocal.findAllCourseByTeacher(teacher);

		return false;
	}

	public String goToQuizAffection() {

		System.out.println(teacher);

		teacher = profilsManagementServiceLocal.findTeacher(user.getLogin(),
				user.getPassword());
		courses = couseManagementLocal.findAllCourseByTeacher(teacher);

		return "/pages/teacher/affectQuizToChapter/affectQuizToChapter";
	}

	public String goToChapters() {

		chapters = couseManagementLocal
				.findAllChapterByCourseAndHasQuiz(course);

		if (!chapters.isEmpty()) {

			return "/pages/teacher/affectQuizToChapter/showAllChapter";
		} else {
			RequestContext.getCurrentInstance().showMessageInDialog(
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Remark !!",
							"this course heven t chapter!!"));

			return "";
		}
	}

	public String goToSeletQuiz() {

		System.out.println(chapter);

		quizs = examManagementServiceLocal.findQuizsByTeacher(teacher);

		return "/pages/teacher/affectQuizToChapter/showAllQuiz";
	}

	public boolean refind() {

		quizs = examManagementServiceLocal.findQuizsByTeacher(teacher);
		chapters = couseManagementLocal
				.findAllChapterByCourseAndHasQuiz(course);

		return false;
	}

	public String doAffectQuizToChapter() {

		// quiz.setTeacher(teacher);

		if (quiz.getChapter() != null) {

			RequestContext.getCurrentInstance().showMessageInDialog(
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Remark !!",
							"This quiz is aready affected !!"));
			return "";
		} else {
			quiz.setChapter(chapter);
			couseManagementLocal.updateChapter(chapter);
			examManagementServiceLocal.updateQuiz(quiz);
			return "/pages/homeTeacher";
		}

	}

	public void onRowSelect(SelectEvent event) {

		chapters = couseManagementLocal
				.findAllChapterByCourseAndHasQuiz(course);
		selected1 = false;

	}

	public void onRowSelect2(SelectEvent event) {

		System.out.println("aaaaaaaa" + chapter);
		selected2 = false;

	}

	public void onRowSelect3(SelectEvent event) {

		selected3 = false;

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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Course> getFiltredCourses() {
		return filtredCourses;
	}

	public void setFiltredCourses(List<Course> filtredCourses) {
		this.filtredCourses = filtredCourses;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public List<Chapter> getFiltredChapter() {
		return filtredChapter;
	}

	public void setFiltredChapter(List<Chapter> filtredChapter) {
		this.filtredChapter = filtredChapter;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public List<Quiz> getQuizs() {
		return quizs;
	}

	public void setQuizs(List<Quiz> quizs) {
		this.quizs = quizs;
	}

	public List<Quiz> getFiltredQuiz() {
		return filtredQuiz;
	}

	public void setFiltredQuiz(List<Quiz> filtredQuiz) {
		this.filtredQuiz = filtredQuiz;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public boolean isSelected1() {
		return selected1;
	}

	public void setSelected1(boolean selected1) {
		this.selected1 = selected1;
	}

	public boolean isSelected2() {
		return selected2;
	}

	public void setSelected2(boolean selected2) {
		this.selected2 = selected2;
	}

	public boolean isSelected3() {
		return selected3;
	}

	public void setSelected3(boolean selected3) {
		this.selected3 = selected3;
	}

}
