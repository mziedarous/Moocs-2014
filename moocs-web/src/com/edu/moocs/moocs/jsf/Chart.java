package com.edu.moocs.moocs.jsf;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean(name = "grafic")
@ViewScoped
public class Chart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private ExamManagementServiceLocal examManagementServiceLocal;

	@EJB
	private ProfilsManagementServiceLocal profilsManagementServiceLocal;

	private List<Quiz> quizs;
	private CartesianChartModel categoryModel;
	private List<Teacher> teachers;

	@PostConstruct
	public void init() {
		categoryModel = new CartesianChartModel();
		quizs = examManagementServiceLocal.findAllQuiz();
		teachers = profilsManagementServiceLocal.findAllTeacher();
		ChartSeries boys = new ChartSeries();
		boys.setLabel("QUIZ");
		for (Teacher teacher : teachers) {
			boys.set(teacher.getLogin(),
					examManagementServiceLocal.findNumberOfQuiz(teacher));
		}
		// boys.set("2004", 120);
		// boys.set("2005", 100);
		// boys.set("2006", 44);
		// boys.set("2007", 150);
		// boys.set("2008", 25);

		// ChartSeries girls = new ChartSeries();
		// girls.setLabel("Student");
		//
		// girls.set("2004", 52);
		// girls.set("2005", 60);
		// girls.set("2006", 110);
		// girls.set("2007", 135);
		// girls.set("2008", 120);

		categoryModel.addSeries(boys);
		// categoryModel.addSeries(girls);
	}

	public String goToStat() {
		String navTo = "";

		navTo = "/pages/teacher/chart/chart";

		return navTo;
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}

	public void setCategoryModel(CartesianChartModel categoryModel) {
		this.categoryModel = categoryModel;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

}
