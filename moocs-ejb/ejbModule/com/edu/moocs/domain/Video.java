package com.edu.moocs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_video")
public class Video extends Contain implements Serializable {

	private static final long serialVersionUID = 1L;
	private String titleVideo;

	public Video() {
	}

	public Video(String typeContain, String titleVideo) {
		super(typeContain);
		this.titleVideo = titleVideo;
	}

	@Column(name = "title_video")
	public String getTitleVideo() {
		return titleVideo;
	}

	public void setTitleVideo(String titleVideo) {
		this.titleVideo = titleVideo;
	}

	public String toString() {
		return "Video [titleVideo=" + titleVideo + ", getId()=" + getId()
				+ ", getTypeContain()=" + getTypeContain() + ", getChapter()="
				+ getChapter() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}
