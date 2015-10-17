package com.edu.moocs.moocs.jsf;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class ChapterBean implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2282447393285196737L;



	@EJB
	private CouseManagementLocal couseManagementLocal ;
	

	
	private Course course;
	private Chapter chapter;
	private StaticText staticText;
	private List<Chapter> chapters;
	private List<Chapter> filtredChapter;
	
	private Contain contain;
	private boolean formDisplayed = false ;
	private boolean formDisplayed1 = false ;
	private boolean formDisplayed2 = false ;
	private boolean formDisplayed3 = false ;
	private boolean formDisplayed4 = false ;

	private List<Chapter>chapters2;
	private boolean vrify = false;
	
	
	public ChapterBean() {
	}
	
	@PostConstruct
	public void init(){
		System.out.println(course);
		chapter = new Chapter();
		staticText = new StaticText();
		setChapters2(couseManagementLocal.findChaptersByCourse(course));	
		contain = new StaticText();

	}
	
	
	
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public List<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
	
	public String doLinkContainToChapter(){
		String navTo="/pages/teacher/structure/courseDefined.xhtml";
		
		System.out.println(staticText.getTitleText());
		System.out.println(chapter);
		
		staticText.setChapter(chapter);
	    couseManagementLocal.updateContain(staticText);
		
	    
		return navTo;
	}
	
	public String createChapter(){
		String navigateTo = null;
		if(!chapter.getName().trim().equals(""))
		{
		chapter.setCourse(course);
		couseManagementLocal.updateChapter(chapter);
		chapters = couseManagementLocal.findAllChapter();
		chapter.setHasQuiz(false);
		}
		
		formDisplayed = false;
		chapter = new Chapter();
		return navigateTo;
		
	}
	
	
	public String showContain(){
		
		 String navTo = "";
		
		System.out.println("contain ::"+chapter);
		
		staticText = couseManagementLocal.findSticTextBychapter(chapter);
		
		if(staticText == null){
			staticText = new StaticText();
		}
		
		
		navTo ="/pages/teacher/structure/contain.xhtml";
		return navTo;
	}
	
	
	
	public String doNew(){
		String navigateTo = null;
		setFormDisplayed(true);
		return navigateTo;
		
	}
	public String doNew1(){
		String navigateTo = null;
		setFormDisplayed1(true);
		return navigateTo;
		
	}
	public String doNew2(){
		String navigateTo = null;
		setFormDisplayed2(true);
		return navigateTo;
		
	}
	public String doNew3(){
		String navigateTo = null;
		setFormDisplayed3(true);
		return navigateTo;
		
	}
	public String doNew4(){
		String navigateTo = null;
		setFormDisplayed4(true);
		return navigateTo;
		
	}
	

	
	public String goToDefine(){
		String navigateTo = null;
		
		chapters2 = couseManagementLocal.findChaptersByCourse(course);
		if(chapters2.isEmpty()){
	        navigateTo = "/pages/teacher/structure/defineCourse.xhtml";
		}else {
			navigateTo = "/pages/teacher/structure/courseDefined.xhtml";
		}

		return navigateTo;
	}
	
	
	
	public String vall(){
		
		System.out.println(" chappterrrr :"+course);
		
		return "";
	}
	
	public boolean isFormDisplayed() {
		return formDisplayed;
	}




	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}




	public Course getCourse() {
		return course;
	}




	public void setCourse(Course course) {
		this.course = course;
	}

	public boolean isFormDisplayed1() {
		return formDisplayed1;
	}

	public void setFormDisplayed1(boolean formDisplayed1) {
		this.formDisplayed1 = formDisplayed1;
	}

	public boolean isFormDisplayed2() {
		return formDisplayed2;
	}

	public void setFormDisplayed2(boolean formDisplayed2) {
		this.formDisplayed2 = formDisplayed2;
	}

	public boolean isFormDisplayed3() {
		return formDisplayed3;
	}

	public void setFormDisplayed3(boolean formDisplayed3) {
		this.formDisplayed3 = formDisplayed3;
	}

	public boolean isFormDisplayed4() {
		return formDisplayed4;
	}

	public void setFormDisplayed4(boolean formDisplayed4) {
		this.formDisplayed4 = formDisplayed4;
	}

	public List<Chapter> getChapters2() {
		return chapters2;
	}

	public void setChapters2(List<Chapter> chapters2) {
		this.chapters2 = chapters2;
	}

	public boolean isVrify() {
		return vrify;
	}

	public void setVrify(boolean vrify) {
		this.vrify = vrify;
	}

	public StaticText getStaticText() {
		return staticText;
	}

	public void setStaticText(StaticText staticText) {
		this.staticText = staticText;
	}

	public Contain getContain() {
		return contain;
	}

	public void setContain(Contain contain) {
		this.contain = contain;
	}

	public List<Chapter> getFiltredChapter() {
		return filtredChapter;
	}

	public void setFiltredChapter(List<Chapter> filtredChapter) {
		this.filtredChapter = filtredChapter;
	}

}