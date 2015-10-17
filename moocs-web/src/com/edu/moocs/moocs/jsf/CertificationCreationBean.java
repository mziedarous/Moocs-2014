package com.edu.moocs.moocs.jsf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "Cbean")
@SessionScoped
public class CertificationCreationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Certification certification;
	private Thematic thematic;

	@EJB
	private ExamManagementServiceLocal examManagementServiceLocal;

	@EJB
	private CouseManagementLocal couseManagementLocal;

	@PostConstruct
	public void init() {
		certification = new Certification();
		thematic = new Thematic();
	}

	public String doCreateCertification() {

		examManagementServiceLocal.createCertification(certification);

		certification.setThematic(thematic);

		thematic.setCertification(certification);
		examManagementServiceLocal.updateCertification(certification);
		couseManagementLocal.updateThematic(thematic);

		return "/pages/homeTeacher";
	}

	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	public Thematic getThematic() {
		return thematic;
	}

	public void setThematic(Thematic thematic) {
		this.thematic = thematic;
	}

}
