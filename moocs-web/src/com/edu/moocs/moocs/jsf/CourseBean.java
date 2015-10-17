package com.edu.moocs.moocs.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class CourseBean implements Serializable {

	@EJB
	private CouseManagementLocal couseManagementLocal;

	@EJB
	private ProfilsManagementServiceLocal profilsManagementServiceLocal;

	@ManagedProperty("#{chapterBean}")
	private ChapterBean chapterBean;
	@ManagedProperty("#{uploadBean}")
	private FileUploadController fileUploadController;

	@ManagedProperty("#{profB}")
	private ProfilManagedBean growlBean;

	public ProfilManagedBean getGrowlBean() {
		return growlBean;
	}

	public void setGrowlBean(ProfilManagedBean growlBean) {
		this.growlBean = growlBean;
	}

	private Course course;
	private User user;
	private Teacher teacher;
	private List<Course> courses;
	private boolean formDisplayed = false;
	private List<SelectItem> selectItemsForThematics;
	private int selectedThematicId = -1;
	private List<Course> filtredCourses;
	private List<SelectItem> filterOptions;
	private List<Thematic> thematics;
	private boolean selected;

	public CourseBean() {
	}

	@PostConstruct
	public void init() {
		selected = true;
		course = new Course();
		user = new User();
		teacher = new Teacher();
		thematics = couseManagementLocal.findAllThematic();
		selectItemsForThematics = new ArrayList<SelectItem>(thematics.size());
		for (Thematic thematic : thematics) {
			selectItemsForThematics.add(new SelectItem(thematic.getId(),
					thematic.getNameThematic()));
		}

		filterOptions = new ArrayList<SelectItem>(thematics.size() + 1);
		filterOptions.add(new SelectItem("", "select"));
		for (Thematic thematic : thematics) {
			filterOptions.add(new SelectItem(thematic.getNameThematic(),
					thematic.getNameThematic()));
		}

	}

	public void createCourse() {
		String x = user.getLogin();
		String y = user.getPassword();

		teacher = profilsManagementServiceLocal.findTeacher(x, y);

		course.setTeacher(teacher);
		course.setThematic(couseManagementLocal
				.findThematic(selectedThematicId));
		couseManagementLocal.updateCourse(course);
		System.out.println(course);
		growlBean.getTexts().add(course.getCourseName());
		growlBean.getTechears().add(teacher.getLogin());
		courses = couseManagementLocal.findAllCourseByTeacher(teacher);
		formDisplayed = false;
	}

	public String doInitByTeacher() {
		String nav = "";
		String x = user.getLogin();
		String y = user.getPassword();

		System.out.println(x);

		teacher = profilsManagementServiceLocal.findTeacher(x, y);

		courses = couseManagementLocal.findAllCourseByTeacher(teacher);
		nav = "/pages/teacher/course/manage";
		return nav;
	}

	public void doNew() {
		course = new Course();
		selectedThematicId = -1;
		formDisplayed = true;
	}

	public void doCancel() {
		course = new Course();
		courses = couseManagementLocal.findAllCourseByTeacher(teacher);
		formDisplayed = false;

	}

	public void deleteCourse() {
		couseManagementLocal.deleteCourse(course);
		courses = couseManagementLocal.findAllCourseByTeacher(teacher);
		formDisplayed = true;

	}

	public void onRowSelect(SelectEvent event) {
		formDisplayed = true;
		if (course.getThematic() != null) {
			selectedThematicId = course.getThematic().getId();
		} else {
			selectedThematicId = -1;
		}
	}

	public void onRowSelect2(SelectEvent event) {

		selected = false;
		chapterBean.setCourse(course);
		fileUploadController.setCourse(course);
	}

	public boolean findAllThematicForItem() {

		thematics = couseManagementLocal.findAllThematic();

		selectItemsForThematics = new ArrayList<SelectItem>(thematics.size());
		for (Thematic thematic : thematics) {
			selectItemsForThematics.add(new SelectItem(thematic.getId(),
					thematic.getNameThematic()));
		}

		filterOptions = new ArrayList<SelectItem>(thematics.size() + 1);
		filterOptions.add(new SelectItem("", "select"));
		for (Thematic thematic : thematics) {
			filterOptions.add(new SelectItem(thematic.getNameThematic(),
					thematic.getNameThematic()));
		}

		return false;
	}

	public String back() {
		String navigateTo = null;
		navigateTo = "/pages/teacher/structure/structure.xhtml";
		return navigateTo;
	}

	public String showAllthematic() {

		teacher = profilsManagementServiceLocal.findTeacher(user.getLogin(),
				user.getPassword());

		System.out.println(teacher);

		courses = couseManagementLocal.findAllCourseByTeacher(teacher);

		return "/pages/teacher/structure/structure";
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public List<SelectItem> getSelectItemsForThematics() {
		return selectItemsForThematics;
	}

	public void setSelectItemsForThematics(
			List<SelectItem> selectItemsForThematics) {
		this.selectItemsForThematics = selectItemsForThematics;
	}

	public int getSelectedThematicId() {
		return selectedThematicId;
	}

	public void setSelectedThematicId(int selectedThematicId) {
		this.selectedThematicId = selectedThematicId;
	}

	public List<Course> getFiltredCourses() {
		return filtredCourses;
	}

	public void setFiltredCourses(List<Course> filtredCourses) {
		this.filtredCourses = filtredCourses;
	}

	public List<SelectItem> getFilterOptions() {
		return filterOptions;
	}

	public void setFilterOptions(List<SelectItem> filterOptions) {
		this.filterOptions = filterOptions;
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

	public ChapterBean getChapterBean() {
		return chapterBean;
	}

	public void setChapterBean(ChapterBean chapterBean) {
		this.chapterBean = chapterBean;
	}

	public FileUploadController getFileUploadController() {
		return fileUploadController;
	}

	public void setFileUploadController(
			FileUploadController fileUploadController) {
		this.fileUploadController = fileUploadController;
	}

	public List<Thematic> getThematics() {
		return thematics;
	}

	public void setThematics(List<Thematic> thematics) {
		this.thematics = thematics;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public ProfilsManagementServiceLocal getProfilsManagementServiceLocal() {
		return profilsManagementServiceLocal;
	}

	public void setProfilsManagementServiceLocal(
			ProfilsManagementServiceLocal profilsManagementServiceLocal) {
		this.profilsManagementServiceLocal = profilsManagementServiceLocal;
	}

}
