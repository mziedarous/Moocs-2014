package com.edu.moocs.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_comment")
public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Course course;
	private User user;
	private String comm;


	private Date date;
	private Comment_pk pk;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	



	public Comment(Course course, User user, String comm) {
		this.getPk().setCourseID(course.getId());
		this.getPk().setUserid(user.getId());
		this.getPk().setComm(comm);
		this.getPk().setDate(new Date());
	}





	@ManyToOne
	@JoinColumn(name = "course_fk", insertable = false, updatable = false)
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
    @ManyToOne
    @JoinColumn(name="user_fk", insertable = false, updatable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}




	@EmbeddedId
	public Comment_pk getPk() {
		if(pk==null)
		{
			pk= new Comment_pk();
		}
		return pk;
	}


	public void setPk(Comment_pk pk) {
		this.pk = pk;
	}


    @Column(name="comment", insertable = false, updatable = false)
	public String getComm() {
		return comm;
	}





	public void setComm(String comm) {
		this.comm = comm;
	}


	@Override
	public String toString() {
		return "Comment [course=" + course + ", user=" + user + ", comm="
				+ comm + "]";
	}




	@Column(name="date", insertable = false, updatable = false)
	public Date getDate() {
		return date;
	}





	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
	
}
