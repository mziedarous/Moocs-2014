package com.edu.moocs.moocs.jsf;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.event.SelectEvent;

import tn.edu.pdev.moocs.domain.Thematic;
import tn.edu.pdev.moocs.services.courseManagement.CouseManagementLocal;

@ManagedBean
@ViewScoped
public class ThematicBean implements Serializable{
	
	@EJB
	private CouseManagementLocal local ;
	
	private Thematic thematic = new Thematic();
	private List<Thematic>thematics;
	private boolean fromDisplayed = false ;
	private List<Thematic> filtredThelatics;
	
	public ThematicBean() {
	}

	@PostConstruct
	public void init(){
		thematics=local.findAllThematic();
	}
	
	public void createThematic(){
		local.updateThematic(thematic);
		thematics=local.findAllThematic();
		fromDisplayed = false;
	}
	
	
	public void doNew(){
		thematic = new Thematic();
		fromDisplayed = true;
		
	}
	
	public void doCancel(){
		thematic = new Thematic();
		thematics=local.findAllThematic();
		fromDisplayed = false;		
	}
	
	
	public void deleteThematic(){
		local.deleteThematic(thematic);
		thematics=local.findAllThematic();
		fromDisplayed = true;
	}
	
	public void onRowSelect(SelectEvent event){
		fromDisplayed = true;
	}
	
	
	
	
	public void validate(FacesContext context, UIComponent arg1, Object value)
			throws ValidatorException {
		String thematicToValidate = (String) value;
		
		if(thematicToValidate == null|| thematicToValidate.trim().isEmpty())
		{
			return;
			
		}
		
		boolean thematicInUser = local.thematicExists(thematicToValidate);
		if (thematicInUser) {
			
		
			throw new ValidatorException(new FacesMessage("thematic already in use!"));
			
		}
	}
	
	
	
	public Thematic getThematic() {
		return thematic;
	}


	public void setThematic(Thematic thematic) {
		this.thematic = thematic;
	}
 

	public List<Thematic> getThematics() {
		return thematics;
	}


	public void setThematics(List<Thematic> thematics) {
		this.thematics = thematics;
	}

	public boolean isFromDisplayed() {
		return fromDisplayed;
	}

	public void setFromDisplayed(boolean fromDisplayed) {
		this.fromDisplayed = fromDisplayed;
	}

	public List<Thematic> getFiltredThelatics() {
		return filtredThelatics;
	}

	public void setFiltredThelatics(List<Thematic> filtredThelatics) {
		this.filtredThelatics = filtredThelatics;
	}
}
