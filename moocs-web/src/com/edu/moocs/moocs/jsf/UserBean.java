package com.edu.moocs.moocs.jsf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import tn.edu.pdev.moocs.domain.Student;
import tn.edu.pdev.moocs.domain.User;
import tn.edu.pdev.moocs.services.profilsManagement.ProfilsManagementServiceLocal;

@ManagedBean
@RequestScoped
public class UserBean{


	private static final long serialVersionUID = 1L;

	
	private Student student ;
	
	@EJB
	private ProfilsManagementServiceLocal profilsManagementServiceLocal;
	
	@ManagedProperty("#{authBean}")
	private AuthenticationBean authBean;
	
	@ManagedProperty("#{profB}")
	private ProfilManagedBean profilBean;
	
	@ManagedProperty("#{certifBean}")
	private CertificationBean certificationBean;
	
	@ManagedProperty("#{findC}")
	private StudentQuizBean studentQuizBean;
	
	@ManagedProperty("#{picturebean}")
    private PictureUploadBean pictureUploadBean;




	@PostConstruct
	public void initModel(){
		student = new Student();
	}

	
	public String saveStudent(){
		
		String navTo = null;
		
		profilsManagementServiceLocal.createUser(student);
		authBean.setUser(student);
		profilBean.setUser(student);
		certificationBean.setUser(student);
		studentQuizBean.setUser(student);
		studentQuizBean.setUser2(student);
		pictureUploadBean.setUser(student);
		
		navTo = authBean.doLogin();
		
		return navTo;
	}
	
	
	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}


	

	public ProfilsManagementServiceLocal getProfilsManagementServiceLocal() {
		return profilsManagementServiceLocal;
	}


	public void setProfilsManagementServiceLocal(
			ProfilsManagementServiceLocal profilsManagementServiceLocal) {
		this.profilsManagementServiceLocal = profilsManagementServiceLocal;
	}


	public AuthenticationBean getAuthBean() {
		return authBean;
	}


	public void setAuthBean(AuthenticationBean authBean) {
		this.authBean = authBean;
	}

	

	public ProfilManagedBean getProfilBean() {
		return profilBean;
	}


	public void setProfilBean(ProfilManagedBean profilBean) {
		this.profilBean = profilBean;
	}

	public CertificationBean getCertificationBean() {
		return certificationBean;
	}


	public void setCertificationBean(CertificationBean certificationBean) {
		this.certificationBean = certificationBean;
	}

	public void validate(FacesContext context, UIComponent arg1, Object value)
			throws ValidatorException {
		
		String loginToValidate = (String) value;
		if(loginToValidate == null|| loginToValidate.trim().isEmpty())
		{
			return;
		}
		
		boolean loginInUse = profilsManagementServiceLocal.loginExists(loginToValidate);
		if (loginInUse) {
			throw new ValidatorException(new FacesMessage("login already in use!"));
		}
		
	}

	

	public StudentQuizBean getStudentQuizBean() {
		return studentQuizBean;
	}


	public void setStudentQuizBean(StudentQuizBean studentQuizBean) {
		this.studentQuizBean = studentQuizBean;
	}


	public PictureUploadBean getPictureUploadBean() {
		return pictureUploadBean;
	}


	public void setPictureUploadBean(PictureUploadBean pictureUploadBean) {
		this.pictureUploadBean = pictureUploadBean;
	}
	
	
	
	
}
