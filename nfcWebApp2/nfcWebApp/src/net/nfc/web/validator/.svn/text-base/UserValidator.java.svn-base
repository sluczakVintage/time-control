package net.nfc.web.validator;

import java.util.List;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CUsers;
import net.nfc.web.forms.CNewUserForm;
import net.nfc.web.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils; 
import org.springframework.validation.Validator;

@Repository("userValidator")
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CNewUserForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CNewUserForm user = (CNewUserForm) target;
		
		
		if(user.getUserName().length() == 0) errors.rejectValue("userName", "userName.required");
		if(user.getUserPassword().length() == 0) errors.rejectValue("userPassword", "userPassword.required");		
		if(user.getUserPassword2().length() ==0) errors.rejectValue("userPassword", "userPassword2.required");		
	
		if(! user.getUserPassword().equals(user.getUserPassword2())) errors.rejectValue("userPassword2", "userPassword2.error");
		if(user.getUserName().length() > 12) errors.rejectValue("userName", "userName.error");
		if(user.getUserPassword().length() < 8) errors.rejectValue("userPassword", "userPassword.error");		
	
		try {
		List<CUsers> list = userService.listUsers();
		
		for (CUsers cUser : list) {
			if(cUser.getUserName().equals(user.getUserName())) errors.rejectValue("userName" ,"userNameDuplicate.error"); 
		}
		
		
		}catch(AdException e) {
			 
		}
		
		
	}

}
