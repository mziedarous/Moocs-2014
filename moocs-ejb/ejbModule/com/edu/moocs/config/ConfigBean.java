package com.edu.moocs.config;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.edu.moocs.domain.Admin;
import com.edu.moocs.domain.Student;
import com.edu.moocs.domain.Teacher;
import com.edu.moocs.services.profilsManagement.ProfilsManagementServiceLocal;

@Singleton
@Startup
public class ConfigBean {

	@EJB
	private ProfilsManagementServiceLocal authentication;

	public ConfigBean() {
	}

	@PostConstruct
	public void createData() {
		if (!authentication.loginExists("Zied")) {
			authentication.createUser(new Teacher("Zied", null, null, null,
					null, null, "Arous", "zied2014", null));
		}
	}

	@PostConstruct
	public void createData2() {
		if (!authentication.loginExists("AROUS")) {
			authentication.createUser(new Teacher("AROUS", "AROUS", null, null,
					null, null, "AROUS", "AROUS", null));
		}
	}
}
