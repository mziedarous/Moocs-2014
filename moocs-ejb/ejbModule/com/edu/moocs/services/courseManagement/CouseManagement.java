package com.edu.moocs.services.courseManagement;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.edu.moocs.domain.Chapter;
import com.edu.moocs.domain.Comment;
import com.edu.moocs.domain.Contain;
import com.edu.moocs.domain.Course;
import com.edu.moocs.domain.Image;
import com.edu.moocs.domain.Pdf;
import com.edu.moocs.domain.StaticText;
import com.edu.moocs.domain.Teacher;
import com.edu.moocs.domain.Thematic;
import com.edu.moocs.domain.User;
import com.edu.moocs.domain.Video;

/**
 * Session Bean implementation class CouseManagement
 */
@Stateless
public class CouseManagement implements CouseManagementRemote, CouseManagementLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
    public CouseManagement() {
        // TODO Auto-generated constructor stub
    }
	public Course createCourse(Course course) {
		em.persist(course);
		return course;
	}
	

	public Thematic createThematic(Thematic thematic) {
		em.persist(thematic);
		return thematic;
	}
	public Chapter createChapter(Chapter chapter) {
		em.persist(chapter);
		return chapter;
	}

	public Contain createContain(Contain contain){
		em.persist(contain);
		return contain;
	}
	
	public void updateCourse(Course course) {
		em.merge(course);
	}
	public void updateThematic(Thematic thematic) {
		em.merge(thematic);
	}
	public void updateChapter(Chapter chapter) {
		em.merge(chapter);
	}

	public void updateContain(Contain contain){
		em.merge(contain);
	}
	public Course findCourse(int id) {
		return em.find(Course.class, id);
	}
	
	public Thematic findThematic(int id) {
		return em.find(Thematic.class, id);
	}
	
	public Chapter findChapter(int id) {
		return em.find(Chapter.class, id);
	}

	public StaticText findStaticText(int id) {
		return em.find(StaticText.class, id);
	}

	public Pdf findPdf(int id) {
		return em.find(Pdf.class, id);
	}

	public Video findVideo(int id) {
		return em.find(Video.class, id);
	}

	public Image findImage(int id) {
		return em.find(Image.class, id);
	}
	
	public void deleteCourse(Course course) {
		em.remove(em.merge(course));
	}
	public void deleteThematic(Thematic thematic) {
		em.remove(em.merge(thematic));
	}
	public void deleteChapter(Chapter chapter) {
		em.remove(em.merge(chapter));
	}

	public void deleteContain(Contain contain)	{
		em.remove(em.merge(contain));
	}
	
	public List<Course> findAllCourse() {
		 try{
		List<Course> courses = null;
		String jpql = "select c from Course c";
		Query query = em.createQuery(jpql);
		courses = query.getResultList();
		return courses;
		  } catch(NoResultException e) {
		        return null;
		    }
	}
	
	public List<Thematic> findAllThematic() {
		List<Thematic> thematics = null;
		String jpql = "select t from Thematic t";
		Query query = em.createQuery(jpql);
		thematics = query.getResultList();
		return thematics;
	}
	public List<Chapter> findAllChapter() {
		List<Chapter> chapters = null;
		String jpql = "select c from Chapter c";
		Query query = em.createQuery(jpql);
		chapters = query.getResultList();
		return chapters;
	}

	public List<StaticText> findAllStaticText() {
		List<StaticText> staticTexts = null;
		String jpql = "select s from StaticText s";
		Query query = em.createQuery(jpql);
		staticTexts = query.getResultList();
		return staticTexts;
	}

	public List<Pdf> findAllPdf() {
		List<Pdf> pdfs = null;
		String jpql = "select p from Pdf p";
		Query query = em.createQuery(jpql);
		pdfs = query.getResultList();
		return pdfs;
	}

	public List<Video> findAllVideo() {
		List<Video> videos = null;
		String jpql = "select v from Video v";
		Query query = em.createQuery(jpql);
		videos = query.getResultList();
		return videos;
	}

	public List<Image> findAllImage() {
		List<Image> images = null;
		String jpql = "select i from Image i";
		Query query = em.createQuery(jpql);
		images = query.getResultList();
		return images;
	}

	public List<Course> findCourseByName(String cour) {

		List<Course> courses = null;
		String jpql = "select c from Course c where UPPER(c.courseName) like concat('%',:x,'%')  ";
		Query query = em.createQuery(jpql);
		query.setParameter("x", cour);
		courses = query.getResultList();
		
		
		return courses;
	}

	public List<Chapter> findAllChapter(Course course) {

		List<Chapter> chapters = null;
		String jpql ="select c from Chapter c where c.course=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x", course);
		chapters=query.getResultList();
		
		return chapters;
	}
	
	public List<StaticText> findAllContain(Course course) {

		List<StaticText> staticTexts = null;
		String jpql = "select s from StaticText s join s.chapter ch where ch.course=:x ";
		Query query = em.createQuery(jpql);
		query.setParameter("x", course);
		staticTexts = query.getResultList();
		
		return staticTexts;
	}
	public List<Chapter> findChaptersByCourse(Course course) {
		List<Chapter>chapters = null;
		String jpql = "select ch from Chapter ch where ch.course =:x";
		Query query= em.createQuery(jpql);
		query.setParameter("x", course);
		chapters = query.getResultList();
		return chapters;
	}
	public List<Course> findAllCourseByTeacher(Teacher teacher) {
		 try{
		List<Course> courses = null;
		String jpql = "select c from Course c where c.teacher=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x", teacher);
		courses = query.getResultList();
		return courses;
		  } catch(NoResultException e) {
		        return null;
		    }
	}
	public List<Chapter> findAllChapterByCourse( Course course) {
		 try{
		List<Chapter> chapters = null;
		String jpql = "select c from Chapter c where c.course=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x", course);
		chapters = query.getResultList();
		return chapters;
		  } catch(NoResultException e) {
		        return null;
		    }
	}


	public List<Thematic> findAllThematicByTeacher(Teacher teacher) {
		
		List<Thematic> thematics =null;
		
		String jpql = "select distinct t from Thematic t join t.courses c where c.teacher=:x";
		
		Query query = em.createQuery(jpql);
		
		query.setParameter("x", teacher);
		
		thematics = query.getResultList();
		
		return thematics;
	}
	
	@Override
	public StaticText findSticTextBychapter(Chapter chapter) {

		try{
		StaticText contain = null;
		
		String jpql = "select c from StaticText c where c.chapter=:x";
		
		Query query = em.createQuery(jpql);
		
		query.setParameter("x", chapter);
		
		contain = (StaticText) query.getSingleResult();
		return contain;
		  } catch(NoResultException e) {
		        return null;
		    }
		
	}

	public boolean thematicExists(String name) {

		boolean exists = false;
		String jpql = "select case when (count(t) > 0)  then true else false end from Thematic t where t.nameThematic=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x", name);
		exists = (Boolean)query.getSingleResult();
		return exists;
	}


	public void doPostComment(User user, Course course, String comment) {
		
		Comment comment2 = new Comment(course, user, comment);
		em.merge(comment2);
		
	}

	public List<Comment> showAllCommentOfThisCourse(Course course) {
         
		try{
		List<Comment> comments =null;
		String jpql ="select c from Comment c where c.course=:x ORDER BY  c.date";
		Query query = em.createQuery(jpql);
		query.setParameter("x", course);
		comments=query.getResultList();
		
		return comments;
		  } catch(NoResultException e) {
		        return null;
		    }
	}
	@Override
	public List<Chapter> findAllChapterByCourseAndHasQuiz(Course course) {
		List<Chapter> chapters = null;
		String jpql ="select c from Chapter c where c.course=:x and c.hasQuiz=true";
		Query query = em.createQuery(jpql);
		query.setParameter("x", course);
		chapters=query.getResultList();
		
		return chapters;
		
	}
	
   
	
}
