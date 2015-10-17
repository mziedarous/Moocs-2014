package com.edu.moocs.services.courseManagement;

import java.util.List;

import javax.ejb.Remote;

import com.edu.moocs.domain.Chapter;
import com.edu.moocs.domain.Contain;
import com.edu.moocs.domain.Course;
import com.edu.moocs.domain.Image;
import com.edu.moocs.domain.Pdf;
import com.edu.moocs.domain.StaticText;
import com.edu.moocs.domain.Thematic;
import com.edu.moocs.domain.Video;

@Remote
public interface CouseManagementRemote {

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

	List<Chapter> findAllChapter(Course course);

	List<StaticText> findAllContain(Course course);

}
