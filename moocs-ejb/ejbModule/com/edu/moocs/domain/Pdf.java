package com.edu.moocs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_pdf")
public class Pdf extends Contain implements Serializable {


	private static final long serialVersionUID = 1L;
	private String titlePdf;

	public Pdf() {
	}

	public Pdf(String typeContain, String titlePdf) {
		super(typeContain);
		this.titlePdf = titlePdf;
	}

	@Column(name = "title_pdf")
	public String getTitlePdf() {
		return titlePdf;
	}

	public void setTitlePdf(String titlePdf) {
		this.titlePdf = titlePdf;
	}

	public String toString() {
		return "Pdf [titlePdf=" + titlePdf + ", getId()=" + getId()
				+ ", getTypeContain()=" + getTypeContain() + ", getChapter()="
				+ getChapter() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}
