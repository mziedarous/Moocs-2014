package com.edu.moocs.moocs.jsf;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


@ManagedBean(name="pmgBean")
@RequestScoped
public class ProfilImageBean {
	private StreamedContent image;
    private String name;
    
	
	@PostConstruct
	public void initModel() throws FileNotFoundException{
		if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the view. Return a stub StreamedContent so
			// that it will generate right URL.
			setImage(new DefaultStreamedContent());
		} else {
			// So, browser is requesting the image. Return a real
			// StreamedContent with the image bytes.
			InputStream stream = new BufferedInputStream(new FileInputStream("C:\\tmp\\zied.jpg"));
	
				setImage(new DefaultStreamedContent(stream));
			
			}
		
		System.out.println(name);
	}
	
	public StreamedContent getImage() {
		return image;
	}
	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
