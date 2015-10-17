package com.edu.moocs.moocs.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("empValidator")
public class EmptyValidator implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value)
			throws ValidatorException {

		
		if(value.toString().trim().isEmpty())
			throw new ValidatorException(new FacesMessage("error value", "field is empty"));
	}

}
