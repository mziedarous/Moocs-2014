package com.edu.moocs.moocs.jsf;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import tn.edu.pdev.moocs.domain.Answers;
import tn.edu.pdev.moocs.domain.Certification;
import tn.edu.pdev.moocs.domain.NoteQuiz;
import tn.edu.pdev.moocs.domain.Question;
import tn.edu.pdev.moocs.domain.Quiz;
import tn.edu.pdev.moocs.domain.Student;
import tn.edu.pdev.moocs.domain.Teacher;
import tn.edu.pdev.moocs.domain.Thematic;
import tn.edu.pdev.moocs.domain.User;
import tn.edu.pdev.moocs.services.courseManagement.CouseManagementLocal;
import tn.edu.pdev.moocs.services.examManagement.ExamManagementServiceLocal;
import tn.edu.pdev.moocs.services.profilsManagement.ProfilsManagementServiceLocal;

@ManagedBean(name = "certifBean")
@SessionScoped
public class CertificationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Certification certification;
	private Thematic thematic;
	private User user;
	private boolean affCertif;
	private List<Thematic> thematics;
	private List<Thematic> thematics2;
	private List<Question> questions;
	private List<Question> questions2;
	private Question question;
	private List<Question> questionsOfCertif;
	private int i;
	private List<Integer> vals;
	private int ann1;
	private int ann2;
	private int ann3;
	private int res;
	private double pourcentage;
	private boolean downloadCertif;
	private StreamedContent content;

	//
	private int count;
	private int count2;
	private int count3;
	private boolean startQuiz;
	private boolean goTovalidate;

	private boolean timeOut;
	private Quiz quiz;
	private boolean validateQuiz;

	@EJB
	private CouseManagementLocal couseManagementLocal;

	@EJB
	private ExamManagementServiceLocal examManagementServiceLocal;

	@EJB
	private ProfilsManagementServiceLocal profilsManagementServiceLocal;

	@ManagedProperty("#{Cbean}")
	private CertificationCreationBean certificationCreationBean;

	@PostConstruct
	public void initModel() {
		thematics = couseManagementLocal.findAllThematic();
		affCertif = true;
		thematics2 = new ArrayList<Thematic>();
		questions = new ArrayList<Question>();
		questionsOfCertif = new ArrayList<Question>();
		certification = new Certification();
		question = new Question();
		setVals(new ArrayList<Integer>());
		setRes(0);
		ann1 = 0;
		ann2 = 0;
		ann3 = 0;
		i = 0;
		pourcentage = 0.0;

		//

		count = 20;
		count2 = 60;
		setCount3(20);
		quiz = new Quiz();
		setTimeOut(false);
		startQuiz = true;
		validateQuiz = true;
		goTovalidate = true;
		downloadCertif = false;
	}

	public void initModel2() {
		setRes(0);
		count = 20;
		count2 = 60;
		setCount3(20);

		setTimeOut(false);
		startQuiz = true;
		validateQuiz = true;
		goTovalidate = true;
	}

	public boolean authoraze(Thematic th) {

		boolean auth = true;
		Long b = examManagementServiceLocal.numberOfQuizInThematic(th);
		NoteQuiz val = null;
		int a = 0;
		// System.out.println(b);

		List<Quiz> quizs = examManagementServiceLocal.findQuizByThematic(th);

		Student student = (Student) profilsManagementServiceLocal.autehnticate(
				user.getLogin(), user.getPassword());
		for (Quiz quiz : quizs) {

			val = examManagementServiceLocal.numberOfStudentPassedQuiz(student,
					quiz);

			if ((val != null) && (val.getNote() == 3)) {
				a++;
			}
		}

		System.out.println(a);

		if ((b == a) && (a != 0)) {
			auth = false;
		}
		if (a != b) {
			auth = true;
		}

		return auth;
	}

	public String showThematicByTeacher() {

		Teacher teacher = profilsManagementServiceLocal.findTeacher(
				user.getLogin(), user.getPassword());

		thematics2 = couseManagementLocal.findAllThematicByTeacher(teacher);

		return "/pages/teacher/certification/thematicOfTheacher";
	}

	public String showQuestionByThematic() {

		boolean exist = false;

		questions = examManagementServiceLocal
				.findAllQuestionByThematic(thematic.getNameThematic());
		System.out.println(thematic.getNameThematic());

		exist = examManagementServiceLocal.certifExist(thematic
				.getNameThematic());
		if (exist == true) {
			certification = examManagementServiceLocal
					.finfCertificationByThematic(thematic.getNameThematic());
			questions = examManagementServiceLocal
					.findAllQuestionByThematic(thematic.getNameThematic());

			for (Question question : questions) {
				question.setCertification(null);
				examManagementServiceLocal.updateQuestion(question);
			}
			return "/pages/teacher/certification/chooseQuestions";
		}

		if (exist == false) {
			certificationCreationBean.setThematic(thematic);
			return "/pages/teacher/certification/createCertif";
		}

		return "/pages/teacher/certification/chooseQuestions";

	}

	public String goToCertif() {

		questionsOfCertif = examManagementServiceLocal
				.findAllQuestionByThematic2(thematic.getNameThematic());

		certification = examManagementServiceLocal
				.finfCertificationByThematic(thematic.getNameThematic());

		if (certification != null)

		{
			count = (int) certification.getTimeCertif();
			count2 = (examManagementServiceLocal
					.findAllQuestionByCertification(certification).size())
					* count;
			return "/pages/admin/certification?faces-redirect=true";
		} else {
			RequestContext.getCurrentInstance().showMessageInDialog(
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Remark !!",
							"this certification is not yet ready!!"));

			return "";

		}
	}

	public Thematic getThematic() {
		return thematic;
	}

	public void setThematic(Thematic thematic) {
		this.thematic = thematic;
	}

	public void show() {

		for (Question q : questions2) {

			q.setCertification(certification);
			examManagementServiceLocal.updateQuestion(q);
		}
	}

	public String goToTeacherHome() {
		String navTo = "";

		navTo = "/pages/homeTeacher?faces-redirect=true";

		return navTo;

	}

	public void validate() {

		res = 0;
		int x = vals.size();
		int y = 0;
		int manyQues = questionsOfCertif.size();
		List<Integer> gondAnnsers = new ArrayList<Integer>();

		for (int r = 0; r <= manyQues - 1; r++) {
			Answers answer2 = examManagementServiceLocal
					.finAllAnserwerByQuestion2(questionsOfCertif.get(r));

			gondAnnsers.add(answer2.getGoodAnnser());

		}

		for (int s = manyQues; s >= 1; s--) {

			if (vals.get(x - s) == gondAnnsers.get(y)) {
				res++;
				y++;
			} else {
				y++;
			}

		}

		pourcentage = (res * 100) / manyQues;

		if (pourcentage > 80) {
			downloadCertif = true;
		}

	}

	public String goToResult() {

		String navTo = "";

		navTo = "/pages/admin/resultatCertif?faces-redirect=true";

		return navTo;

	}

	public String doFinishCertif() {
		String navTo = "";

		navTo = "/pages/home?faces-redirect=true";

		return navTo;
	}

	public int getI() {
		vals.add(i);
		return i;
	}

	public void setI(int i) {

		this.i = i;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Thematic> getThematics() {
		return thematics;
	}

	public void setThematics(List<Thematic> thematics) {
		this.thematics = thematics;
	}

	public boolean isAffCertif() {
		return affCertif;
	}

	public void setAffCertif(boolean affCertif) {
		this.affCertif = affCertif;
	}

	public List<Thematic> getThematics2() {
		return thematics2;
	}

	public void setThematics2(List<Thematic> thematics2) {
		this.thematics2 = thematics2;
	}

	public List<Question> getQuestions() {
		// questions.add(questions2.)
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Question> getQuestions2() {
		return questions2;
	}

	public void setQuestions2(List<Question> questions2) {
		this.questions2 = questions2;
	}

	public List<Question> getQuestionsOfCertif() {
		return questionsOfCertif;
	}

	public void setQuestionsOfCertif(List<Question> questionsOfCertif) {
		this.questionsOfCertif = questionsOfCertif;
	}

	public List<Integer> getVals() {
		return vals;
	}

	public void setVals(List<Integer> vals) {
		this.vals = vals;
	}

	public int getAnn1() {
		return ann1;
	}

	public void setAnn1(int ann1) {
		this.ann1 = ann1;
	}

	public int getAnn2() {
		return ann2;
	}

	public void setAnn2(int ann2) {
		this.ann2 = ann2;
	}

	public int getAnn3() {
		return ann3;
	}

	public void setAnn3(int ann3) {
		this.ann3 = ann3;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public double getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}

	public CertificationCreationBean getCertificationCreationBean() {
		return certificationCreationBean;
	}

	public void setCertificationCreationBean(
			CertificationCreationBean certificationCreationBean) {
		this.certificationCreationBean = certificationCreationBean;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void increment() throws IOException {

		count--;
		if (count <= 0) {

		}

	}

	public void increment2() throws IOException {
		count2--;
		if (count2 <= 0) {
			res = 0;
			int x = vals.size();
			int y = 0;
			int manyQues = questionsOfCertif.size();
			List<Integer> gondAnnsers = new ArrayList<Integer>();

			for (int r = 0; r <= manyQues - 1; r++) {
				Answers answer2 = examManagementServiceLocal
						.finAllAnserwerByQuestion2(questionsOfCertif.get(r));

				gondAnnsers.add(answer2.getGoodAnnser());

			}

			for (int s = manyQues; s >= 1; s--) {

				if (vals.get(x - s) == gondAnnsers.get(y)) {
					res++;
					y++;
				} else {
					y++;
				}

			}

			pourcentage = (res * 100) / manyQues;

			if (pourcentage > 80) {
				downloadCertif = true;
			}

			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("resultatCertif.jsf");
		}

	}

	public void increment3() {
		count3--;
		if (count3 <= 0) {
			timeOut = true;
			goTovalidate = false;
		}

	}

	public boolean stopincrem() {

		if (count <= 0) {
			count = 0;
			return true;
		} else
			return false;

	}

	public boolean stopincrem2() {

		if (count2 <= 0) {
			count2 = 0;
			return true;
		} else
			return false;

	}

	public boolean stopincrem3() {

		if (count3 <= 0) {
			count3 = 0;
			return true;
		} else
			return false;

	}

	public void timerIsOff() {
		if (count <= 0) {
			timeOut = false;
		}
	}

	public boolean isTimeOut() {
		return timeOut;
	}

	public void setTimeOut(boolean timeOut) {
		this.timeOut = timeOut;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public boolean isStartQuiz() {
		return startQuiz;
	}

	public void setStartQuiz(boolean startQuiz) {
		this.startQuiz = startQuiz;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

	public boolean isValidateQuiz() {
		return validateQuiz;
	}

	public void setValidateQuiz(boolean validateQuiz) {
		this.validateQuiz = validateQuiz;
	}

	public int getCount3() {
		return count3;
	}

	public void setCount3(int count3) {
		this.count3 = count3;
	}

	public boolean isGoTovalidate() {
		return goTovalidate;
	}

	public void setGoTovalidate(boolean goTovalidate) {
		this.goTovalidate = goTovalidate;
	}

	public boolean isDownloadCertif() {
		return downloadCertif;
	}

	public void setDownloadCertif(boolean downloadCertif) {
		this.downloadCertif = downloadCertif;
	}

	public StreamedContent getContent() throws FileNotFoundException {

		InputStream stream = new BufferedInputStream(new FileInputStream(
				"C:\\tmp\\MOOCS.pdf"));
		content = new DefaultStreamedContent(stream, "application/pdf",
				"certification.pdf");

		return content;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
	}
}
