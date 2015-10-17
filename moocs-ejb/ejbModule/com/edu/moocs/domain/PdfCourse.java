package com.edu.moocs.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Pdf_Course")
public class PdfCourse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
    private String PdfName;
    
    private Course course;
    
    public PdfCourse() {
	}
    
    
    
    public PdfCourse(String pdfName) {
		this.PdfName = pdfName;
	}


    @Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
	public String getPdfName() {
		return PdfName;
	}

	public void setPdfName(String pdfName) {
		PdfName = pdfName;
	}


    @OneToOne(mappedBy="pdfCourse")
	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}	
	

}
