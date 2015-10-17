package com.edu.moocs.moocs.jsf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "authBean")
@SessionScoped
public class AuthenticationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;
	private String userType;
	private boolean loggedIn;
	private boolean loggedOut;

	@PostConstruct
	public void intModel() {
		user = new User();
		userType = "";
		loggedIn = true;
		loggedOut = false;
	}

	@EJB
	private ProfilsManagementServiceLocal profilsManagementServiceLocal;

	public AuthenticationBean() {
	}

	public String doLogin() {
		String navigateTo = null;
		User found = profilsManagementServiceLocal.autehnticate(
				user.getLogin(), user.getPassword());
		if (found instanceof Student) {
			setUserType("student");
			loggedIn = false;
			loggedOut = true;
			navigateTo = "/pages/home?faces-redirect=true";

		}
		if (found instanceof Teacher) {
			loggedIn = false;
			loggedOut = true;
			setUserType("teacher");
			navigateTo = "/pages/homeTeacher?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"login_form:login_submit",
					new FacesMessage("Wrong parameter!"));

		}
		return navigateTo;
	}

	public String doLogOut() {

		String navigateTo = "";
		intModel();
		navigateTo = "/welcome";

		return navigateTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean isLoggedOut() {
		return loggedOut;
	}

	public void setLoggedOut(boolean loggedOut) {
		this.loggedOut = loggedOut;
	}

}
