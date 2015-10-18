package com.edu.moocs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "t_static_text")
public class StaticText extends Contain implements Serializable {


	private static final long serialVersionUID = 1L;
	private String titleText;

	public StaticText() {
	}

	public StaticText(String typeContain, String titleText) {
		super(typeContain);
		this.titleText = titleText;
	}

	@Lob
	@Column(name = "title_text", length = 512, columnDefinition = "TEXT")
	public String getTitleText() {
		return titleText;
	}

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	@Override
	public String toString() {
		return "StaticText [titleText=" + titleText + "]";
	}

}
