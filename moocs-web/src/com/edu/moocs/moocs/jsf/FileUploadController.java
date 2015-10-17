package com.edu.moocs.moocs.jsf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "uploadBean")
@SessionScoped
public class FileUploadController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Course course;
	private String destination = "C:\\tmp\\";

	@PostConstruct
	public void initModel() {
		course = new Course();
	}

	public void upload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Success! ", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		// Do what you want with the file
		try {
			copyFile(event.getFile().getFileName(), event.getFile()
					.getInputstream());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void copyFile(String fileName, InputStream in) {
		try {

			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(destination
					+ course.getCourseName() + ".pdf"));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			System.out.println(course.getCourseName());

			in.close();
			out.flush();
			out.close();

			System.out.println("New file created!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public String showCourse() {

		return "";
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
