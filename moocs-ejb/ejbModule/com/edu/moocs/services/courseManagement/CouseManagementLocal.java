package com.edu.moocs.services.courseManagement;

import java.util.List;

import javax.ejb.Local;

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

@Local
public interface CouseManagementLocal {

	public Course createCourse(Course course);
	public Thematic createThematic(Thematic thematic);
	public Chapter createChapter(Chapter chapter);
	public Contain createContain(Contain contain);
	
	public void updateCourse(Course course);
	public void updateThematic(Thematic thematic);
	public void updateChapter(Chapter chapter);
	public void updateContain(Contain contain);
	
	public Course findCourse(int id);
	public Thematic findThematic( int id);
	public Chapter findChapter(int id);
	public StaticText findStaticText( int id);
	public Pdf findPdf(int id);
	public Video findVideo(int id);
	public Image findImage(int id);
	
	public void deleteCourse(Course course);
	public void deleteThematic(Thematic thematic);
	public void deleteChapter(Chapter chapter);
	public void deleteContain(Contain contain);
	
	public List<Course> findAllCourse();
	public List<Thematic> findAllThematic();
	public List<Chapter> findAllChapter();
	public List<StaticText> findAllStaticText();
	public List<Pdf> findAllPdf();
	public List<Video> findAllVideo();
	public List<Image> findAllImage();
	
	
	public List<Course> findCourseByName(String cour);
	public List<Chapter> findAllChapter(Course course);
	public List<StaticText> findAllContain(Course course);
	public List<Chapter> findChaptersByCourse(Course course);
	public List<Course> findAllCourseByTeacher(Teacher teacher);
	public List<Chapter> findAllChapterByCourse(Course course);
	public List<Thematic> findAllThematicByTeacher(Teacher teacher);
	public StaticText findSticTextBychapter(Chapter chapter);
	public boolean thematicExists(String name);
	public void doPostComment(User user, Course course ,String comment);
	public List<Comment> showAllCommentOfThisCourse(Course course);
	public List<Chapter> findAllChapterByCourseAndHasQuiz(Course course);
}