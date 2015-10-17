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

	Course createCourse(Course course);

	Thematic createThematic(Thematic thematic);

	Chapter createChapter(Chapter chapter);

	Contain createContain(Contain contain);

	void updateCourse(Course course);

	void updateThematic(Thematic thematic);

	void updateChapter(Chapter chapter);

	void updateContain(Contain contain);

	Course findCourse(int id);

	Thematic findThematic(int id);

	Chapter findChapter(int id);

	StaticText findStaticText(int id);

	Pdf findPdf(int id);

	Video findVideo(int id);

	Image findImage(int id);

	void deleteCourse(Course course);

	void deleteThematic(Thematic thematic);

	void deleteChapter(Chapter chapter);

	void deleteContain(Contain contain);

	List<Course> findAllCourse();

	List<Thematic> findAllThematic();

	List<Chapter> findAllChapter();

	List<StaticText> findAllStaticText();

	List<Pdf> findAllPdf();

	List<Video> findAllVideo();

	List<Image> findAllImage();

	List<Course> findCourseByName(String cour);

	List<Chapter> findAllChapter(Course course);

	List<StaticText> findAllContain(Course course);

	List<Chapter> findChaptersByCourse(Course course);

	List<Course> findAllCourseByTeacher(Teacher teacher);

	List<Chapter> findAllChapterByCourse(Course course);

	List<Thematic> findAllThematicByTeacher(Teacher teacher);

	StaticText findSticTextBychapter(Chapter chapter);

	boolean thematicExists(String name);

	void doPostComment(User user, Course course, String comment);

	List<Comment> showAllCommentOfThisCourse(Course course);

	List<Chapter> findAllChapterByCourseAndHasQuiz(Course course);
}
