package com.edu.moocs.moocs.jsf;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.print.PrintServiceLookup;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import tn.edu.pdev.moocs.domain.Answers;
import tn.edu.pdev.moocs.domain.Chapter;
import tn.edu.pdev.moocs.domain.Comment;
import tn.edu.pdev.moocs.domain.Contain;
import tn.edu.pdev.moocs.domain.Course;
import tn.edu.pdev.moocs.domain.Question;
import tn.edu.pdev.moocs.domain.Quiz;
import tn.edu.pdev.moocs.domain.StaticText;
import tn.edu.pdev.moocs.domain.Student;
import tn.edu.pdev.moocs.domain.Teacher;
import tn.edu.pdev.moocs.domain.User;
import tn.edu.pdev.moocs.services.courseManagement.CouseManagement;
import tn.edu.pdev.moocs.services.courseManagement.CouseManagementLocal;
import tn.edu.pdev.moocs.services.examManagement.ExamManagementServiceLocal;
import tn.edu.pdev.moocs.services.profilsManagement.ProfilsManagementServiceLocal;

@ManagedBean(name="findC")
@SessionScoped
public class StudentQuizBean implements Serializable{


	private static final long serialVersionUID = 1L;



	@EJB
	private CouseManagementLocal managementLocal;
	
	@EJB
	private ProfilsManagementServiceLocal profilsManagementServiceLocal;
	
	@EJB
	private ExamManagementServiceLocal examManagementServiceLocal;
	
	

	

	private Course course ;
	private Chapter chapter;
	private boolean suscTest = false;
	private boolean videoButtonView = false;
	private boolean pdfButtonView = false;
	private boolean showQuiz = false;
	private boolean studentConnect = false;
	private List<Course> courses;
	private User user;
	private User user2;
	private List<Course> filteredCour;
	private boolean isSelected;
	private List<Chapter> chapters ;
	private List<StaticText> staticTexts ;
	private int i ;
	private List<Question> questions;
	private List<Answers> answers;
	private int ann1;
	private int ann2;
	private int ann3;
	private Map<Integer,String> reponse;
	private List<String> answs;
	private List<Integer> vals;
	private int res;
	private Student student2; 
	private Teacher teacher;
	//
	private int count;
	private int count2;
	private int count3;
	private boolean startQuiz;
	private boolean goTovalidate;


	private boolean timeOut;
	private Quiz quiz;
	private boolean validateQuiz;
	
	//
	
	private StreamedContent content;
	
	//post comment
	private String comm;
	private List<Comment> comments;

	
	@PostConstruct
	public void initModel(){
		course = new Course();
		courses = null;
		ann1 =0;
		ann2 =0;
		ann3 =0;
		isSelected = true;
		user = new Student();
		user2 = new User();
		chapter = new Chapter();
		setI(0);
		answs = new ArrayList<String>();
		reponse = new HashMap<Integer, String>();
		setVals(new ArrayList<Integer>());
		setRes(0);
		student2 = new Student();
		comments=new ArrayList<Comment>(); 
		//
		count = 20;
		count2 = 60;
		setCount3(20);
		quiz = new Quiz();
		setTimeOut(false);
		startQuiz = true;
		validateQuiz = true;
		goTovalidate = true;
		teacher = new Teacher();
		content =null;
		//
		
		
	}
	
	public void initModel2(){
		setRes(0);
		count = 20;
		count2 = 60;
		setCount3(20);
		
		setTimeOut(false);
		startQuiz = true;
		validateQuiz = true;
		goTovalidate = true;
		quiz = new Quiz();
		vals = new ArrayList<Integer>();
		i=0;
	}
	
	public StudentQuizBean() {
	}
	
	public String find(){
		
		String navigateTo ="";

		courses=managementLocal.findCourseByName(course.getCourseName());
		
		if(courses!=null)
		{

			navigateTo="/pages/admin/resultFind?faces-redirect=true";
			
			
		}
		if(courses.isEmpty())
		{
			navigateTo="/pages/admin/failSearch?faces-redirect=true";
                      		
		}
		
		
		return navigateTo;
	}
	
	
	public String findAllCourses(){
		String navTo = "";
		
		courses=managementLocal.findAllCourse();
		
		if(courses!=null){
			navTo="/pages/admin/resultFind?faces-redirect=true";
		}
		
		return navTo;
	}
	
	public String doLink(){
		
		String navigateTo = "";
		String x = user.getLogin(); 
		String p = user.getPassword();
		//System.out.println(x+" "+p);
		
		Student student = profilsManagementServiceLocal.findStudent(x, p);
		
		List<Course> courses2 = profilsManagementServiceLocal.findCoursesByStudent(student);
		courses2.add(course);
		pdfButtonView = true;
		student.setCourses(courses2);
		profilsManagementServiceLocal.updateUser(student);

		
		
		return navigateTo;
	}

	
    public boolean verifSub(){
    	boolean val = false;
    	
		String x = user.getLogin(); 
		String p = user.getPassword();
	
		
		Student student = profilsManagementServiceLocal.findStudent(x, p);
    	
    	
    	student2 = profilsManagementServiceLocal.verifSubs(student, course);
    	
    	if(student2 == null)
    	{
    		val = true;
    		this.setPdfButtonView(false);
    		this.setVideoButtonView(false);	
    		this.setShowQuiz(false);
    	}
    	else
    	{
    		val = false;
    		this.setPdfButtonView(true);
    		this.setVideoButtonView(true);
    		this.setShowQuiz(true);
    	}

    	
   
    	return	val;
    		

    	
    	
    }
	

    public String goToTheCourse(){
      
    	String navTo="";

    	comments= managementLocal.showAllCommentOfThisCourse(course);
    	
    	//findC.staticTexts.get(findC.chapters.indexOf(item)).titleText
    	
    	try
    	{
    	StaticText val = staticTexts.get(0);
    	navTo ="/pages/admin/course?faces-redirect=true";
    	}
    	
    	catch(Exception ex)
    	{
			RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Remark !!", "this course is not yet ready!!"));

    	}
    	
    	return navTo;
    }
    
    
    public void onRowSelect(SelectEvent event){
    	
    	
    	isSelected = false;
		chapters = managementLocal.findAllChapter(course);
		staticTexts = managementLocal.findAllContain(course);

    }
    
 
    public boolean showAllTheContain()
    {
    	
    	isSelected = false;
		chapters = managementLocal.findAllChapter(course);
		staticTexts = managementLocal.findAllContain(course);
    	
    	return false;
    }

	
     public String goToQuiz(){
    	 
    	 String navTo="";
    	 
    	 if(chapter != null){
    			questions = examManagementServiceLocal.findAllQuestionByChap(chapter);
    		 navTo="/pages/admin/Quiz?faces-redirect=true";
        
    		 
    	 }
    	 
    	 return navTo;
    	 
     }
     
     public String goToResult(){
    	 
    	 String navTo="";
    	 
        navTo = "/pages/admin/resultat?faces-redirect=true";
    	 
    	 return navTo;
    	 
     }
     
     public String goToQuizAgain(){
    	 
    	 String navTo="";
    	 
    	 if(chapter != null){
 			questions = examManagementServiceLocal.findAllQuestionByChap(chapter);
 		 navTo="/pages/admin/Quiz?faces-redirect=true";

 	 }
    	 return navTo;
    	 
     }
     
     
public void onClikQ(){
	
	questions = examManagementServiceLocal.findAllQuestionByChap(chapter);
	
	if(questions == null){
		System.out.println(0);
		
	}
	if(questions !=null)
	{

		System.out.println(questions.size());
		
	}
	
}

public void resultQuizs(SelectEvent event){
	vals.add(i);

}

public void validate() {

	int x = vals.size();
	ann1 = vals.get(x - 3);
	ann2 = vals.get(x - 2);
	ann3 = vals.get(x - 1);


	List<Answers> answers = examManagementServiceLocal
			.finAllAnserwerByQuestion(questions.get(0));
	List<Answers> answers2 = examManagementServiceLocal
			.finAllAnserwerByQuestion(questions.get(1));
	List<Answers> answers3 = examManagementServiceLocal
			.finAllAnserwerByQuestion(questions.get(2));

	int goodAnn1 = answers.get(0).getGoodAnnser();
	int goodAnn2 = answers2.get(0).getGoodAnnser();
	int goodAnn3 = answers3.get(0).getGoodAnnser();

	if (goodAnn1 == ann1) {
		res++;
	}
	if (goodAnn2 == ann2) {
		res++;
	}
	if (goodAnn3 == ann3) {
		res++;
	}

	
	quiz = examManagementServiceLocal.findQuizByChapter(chapter);

	try{
	int lastnote = examManagementServiceLocal.numberOfStudentPassedQuiz(student2, quiz).getNote();
	
	
	if(res>lastnote){
	examManagementServiceLocal.noteQuizStudent(student2, quiz, res);}
	}
	catch(NullPointerException exception){
		examManagementServiceLocal.noteQuizStudent(student2, quiz, res);	
	}
}


   
public int onSeltQuiz(){

	vals.add(i);
	
	return i;
}

public void select1(){
	
	vals.add(i);
	
	
}

public int select2(){
	
	i= 0;
	select1();
	
	
	return i;
}
public int select3(){
	
	vals.add(3);
	
	return 3;
}


public void doReturn(){
	vals = new ArrayList<Integer>();
	res = 0;
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

	public boolean isSuscTest() {
		return suscTest;
	}

	public void setSuscTest(boolean suscTest) {
		this.suscTest = suscTest;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isVideoButtonView() {
		return videoButtonView;
	}

	public void setVideoButtonView(boolean videoButtonView) {
		this.videoButtonView = videoButtonView;
	}

	public boolean isPdfButtonView() {
		return pdfButtonView;
	}

	public void setPdfButtonView(boolean pdfButtonView) {
		this.pdfButtonView = pdfButtonView;
	}

	public List<Course> getFilteredCour() {
		return filteredCour;
	}

	public void setFilteredCour(List<Course> filteredCour) {
		this.filteredCour = filteredCour;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public List<StaticText> getStaticTexts() {
		return staticTexts;
	}

	public void setStaticTexts(List<StaticText> staticTexts) {
		this.staticTexts = staticTexts;
	}

	public int getI() {
		vals.add(i);
		return i;
	}

	public void setI(int i) {
		
		this.i = i;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answers> answers) {
		this.answers = answers;
	}


	public List<String> getAnsws() {
		return answs;
	}

	public void setAnsws(List<String> answs) {
		this.answs = answs;
	}

	public boolean isShowQuiz() {
		return showQuiz;
	}

	public void setShowQuiz(boolean showQuiz) {
		this.showQuiz = showQuiz;
	}

	public boolean isStudentConnect() {
		return studentConnect;
	}

	public void setStudentConnect(boolean studentConnect) {
		this.studentConnect = studentConnect;
	}

	public Map<Integer,String> getReponse() {
		return reponse;
	}

	public void setReponse(Map<Integer,String> reponse) {
		this.reponse = reponse;
	}

	public List<Integer> getVals() {
		return vals;
	}

	public void setVals(List<Integer> vals) {
		this.vals = vals;
	}

	public int getAnn1() {
		return ann1;
	}

	public void setAnn1(int ann1) {
		this.ann1 = ann1;
	}

	public int getAnn2() {
		return ann2;
	}

	public void setAnn2(int ann2) {
		this.ann2 = ann2;
	}

	public int getAnn3() {
		return ann3;
	}

	public void setAnn3(int ann3) {
		this.ann3 = ann3;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

//
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void increment() throws IOException {
		count--;
		if (count <= 0) {

		}

	}

	public void increment2() throws IOException {
		count2--;
		if (count2 <= 0) {
			int x = vals.size();
			ann1 = vals.get(x - 3);
			ann2 = vals.get(x - 2);
			ann3 = vals.get(x - 1);

	
			List<Answers> answers = examManagementServiceLocal
					.finAllAnserwerByQuestion(questions.get(0));
			List<Answers> answers2 = examManagementServiceLocal
					.finAllAnserwerByQuestion(questions.get(1));
			List<Answers> answers3 = examManagementServiceLocal
					.finAllAnserwerByQuestion(questions.get(2));

			int goodAnn1 = answers.get(0).getGoodAnnser();
			int goodAnn2 = answers2.get(0).getGoodAnnser();
			int goodAnn3 = answers3.get(0).getGoodAnnser();

			if (goodAnn1 == ann1) {
				res++;
			}
			if (goodAnn2 == ann2) {
				res++;
			}
			if (goodAnn3 == ann3) {
				res++;
			}
			
			quiz = examManagementServiceLocal.findQuizByChapter(chapter);

			
			try{
				int lastnote = examManagementServiceLocal.numberOfStudentPassedQuiz(student2, quiz).getNote();
			if(res>lastnote || lastnote==0){
			examManagementServiceLocal.noteQuizStudent(student2, quiz, res);}
			
			}
			catch(NullPointerException exception){
				examManagementServiceLocal.noteQuizStudent(student2, quiz, res);	
			}
			
				
			FacesContext.getCurrentInstance().getExternalContext().redirect("resultat.jsf");
		}

	}
	public void increment3() {
		count3--;
		if (count3 <= 0) {
			timeOut = true;
			goTovalidate = false;
		}
		
	}
	
	public boolean stopincrem() {

		if (count <= 0) {
			count = 0;
			return true;
		} else
			return false;

	}
	
	
	public boolean stopincrem2() {

		if (count2 <= 0) {
			count2 = 0;
			return true;
		} else
			return false;

	}
	public boolean stopincrem3() {
		
		if (count3 <= 0) {
			count3 = 0;
			return true;
		} else
			return false;
		
	}
	
	

	public void timerIsOff(){
		if(count <= 0){
			timeOut = false;
		}
	}
	
	


	
	public boolean isTimeOut() {
		return timeOut;
	}

	public void setTimeOut(boolean timeOut) {
		this.timeOut = timeOut;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public boolean isStartQuiz() {
		return startQuiz;
	}

	public void setStartQuiz(boolean startQuiz) {
		this.startQuiz = startQuiz;
	}
	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

	public boolean isValidateQuiz() {
		return validateQuiz;
	}

	public void setValidateQuiz(boolean validateQuiz) {
		this.validateQuiz = validateQuiz;
	}

	public int getCount3() {
		return count3;
	}

	public void setCount3(int count3) {
		this.count3 = count3;
	}

	public boolean isGoTovalidate() {
		return goTovalidate;
	}

	public void setGoTovalidate(boolean goTovalidate) {
		this.goTovalidate = goTovalidate;
	}
	
	public StreamedContent getContent() throws FileNotFoundException {
		
		
		try{
	       InputStream stream = new BufferedInputStream(new FileInputStream("C:\\tmp\\"+course.getCourseName()+".pdf"));
        	content=new DefaultStreamedContent(stream, "application/pdf", course.getCourseName()+".pdf");

        	
		return content;
		}
		catch(FileNotFoundException exception){
		
			
		
		
			return null;
			
		}
		
	}
	


	
	public void setContent(StreamedContent content) {
		this.content = content;
	}
	
	
	// post Comment
	public String valideComment() throws IOException{
		
		System.out.println("PC :"+course.getCourseName());
		
		user2=profilsManagementServiceLocal.findUserByNameAndLogin(user2.getLogin(), user2.getPassword());
		
		System.out.println(user2);
		System.out.println(comm);
		managementLocal.doPostComment(user2, course, comm);
		
		for (Comment comment : comments) {
			System.out.println(comment);
		}
		
		comments= managementLocal.showAllCommentOfThisCourse(course);
		comm="";
		FacesContext.getCurrentInstance().getExternalContext().redirect("");
		return "";
	}

	
	public boolean reloadListOfCommentOfCourse(){
		
		comments= managementLocal.showAllCommentOfThisCourse(course);
		
		return false;
	}
	
	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	///
    private String text;
    
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void doNotif(){
    	
    }
    public void save(ActionEvent actionEvent) {

    }

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	
}


	

