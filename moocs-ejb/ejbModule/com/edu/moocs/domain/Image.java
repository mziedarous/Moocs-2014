package com.edu.moocs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_image")
public class Image extends Contain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titleImage;

	public Image() {
	}

	public Image(String typeContain, String titleImage) {
		super(typeContain);
		this.titleImage = titleImage;
	}

	@Column(name = "title_image")
	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public String toString() {
		return "Image [titleImage=" + titleImage + ", getId()=" + getId()
				+ ", getTypeContain()=" + getTypeContain() + ", getChapter()="
				+ getChapter() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}
