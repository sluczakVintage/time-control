package net.nfc.web.validator;

import net.nfc.web.forms.CAddPrivilege;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Repository("addPrivilegeValidator")
public class AddPrivilegeValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return CAddPrivilege.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CAddPrivilege role = (CAddPrivilege )target;
		
		if(role.equals("null")) errors.rejectValue("role", "addPrivilegesRole.required");
		
	}

}
