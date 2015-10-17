package com.edu.moocs.moocs.jsf;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.print.PrintServiceLookup;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import tn.edu.pdev.moocs.domain.Student;
import tn.edu.pdev.moocs.domain.Teacher;
import tn.edu.pdev.moocs.domain.User;
import tn.edu.pdev.moocs.services.profilsManagement.ProfilsManagementServiceLocal;

@ManagedBean(name ="profB")
@SessionScoped
public class ProfilManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Student student;
	private Teacher teacher;
	private StreamedContent image;
	private List<String> texts;
	private List<String> techears;
	private boolean notifOK;
	
	
	@EJB
	private ProfilsManagementServiceLocal profilsManagementServiceLocal;
	
	@PostConstruct
	public void InitModel() throws FileNotFoundException{
		
		user = new User();
		student = new Student();
		
		if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the view. Return a stub StreamedContent so
			// that it will generate right URL.
			setImage(new DefaultStreamedContent());
		} else {
			// So, browser is requesting the image. Return a real
			// StreamedContent with the image bytes.
			InputStream stream = new BufferedInputStream(new FileInputStream("C:\\tmp\\zied.jpg"));
	
				setImage(new DefaultStreamedContent(stream));
			
			}
	     notifOK=true;;
		
		texts = new ArrayList<String>();
		techears = new ArrayList<String>();
		

	}
    public boolean findUser(){
    	
    	
		String x = user.getLogin(); 
		String p = user.getPassword();
	
		
		student = profilsManagementServiceLocal.findStudent(x, p);
    	
    	return false;
    }
    
    
    public boolean notifi(){
    	
    	if(texts !=null){
    		for (String text : texts) {
				
    			FacesMessage context = new FacesMessage(FacesMessage.SEVERITY_INFO, "notifications", " we have :  "+text+" as a new course"); 
    			FacesContext.getCurrentInstance().addMessage(null, context);  
    			
			}
    		texts =new ArrayList<String>();
    	}
   
    	
    	
    	return false;
    }
    
    public boolean findTeacher2(){
    	
		String x = user.getLogin(); 
		String p = user.getPassword();
	
    	teacher = profilsManagementServiceLocal.findTeacher(x, p);
    	
    	
    	return false;
    }
    
    
    public StreamedContent ht() throws FileNotFoundException{
    	
		
    	if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the view. Return a stub StreamedContent so
			// that it will generate right URL.
			setImage(new DefaultStreamedContent());
		} else {
			// So, browser is requesting the image. Return a real
			// StreamedContent with the image bytes.
			try
			{
			InputStream stream = new BufferedInputStream(new FileInputStream("C:\\tmp\\"+user.getLogin()+".jpg"));
	
				setImage(new DefaultStreamedContent(stream));
			
			}catch(FileNotFoundException exception){
				InputStream stream = new BufferedInputStream(new FileInputStream("C:\\tmp\\default.jpg"));
				
				setImage(new DefaultStreamedContent(stream));
				
			}
			
			}
    	return image;
    }
    
    public boolean findTeacher(){
    	
    	
		String x = user.getLogin(); 
		String p = user.getPassword();
	
		teacher = profilsManagementServiceLocal.findTeacher(x, p);
		
    	
    	return false;
    }
    
    
    public String erName(){
    	
    	System.out.println(user.getLogin());
    	
    	return "";
    }
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public StreamedContent getImage() {
		return image;
	}
	public void setImage(StreamedContent image) {
		this.image = image;
	}
	
	
	public List<String> getTexts() {
		return texts;
	}

	public void setTexts(List<String> texts) {
		this.texts = texts;
	}

	public List<String> getTechears() {
		return techears;
	}

	public void setTechears(List<String> techears) {
		this.techears = techears;
	}
	public boolean isNotifOK() {
		return notifOK;
	}
	public void setNotifOK(boolean notifOK) {
		this.notifOK = notifOK;
	}

}
