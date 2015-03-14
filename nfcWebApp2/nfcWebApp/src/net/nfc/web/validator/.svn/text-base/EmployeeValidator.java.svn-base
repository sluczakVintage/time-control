package net.nfc.web.validator;

import net.nfc.db.entity.CEmployee;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Repository("employeeValidator")
public class EmployeeValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CEmployee.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		CEmployee employee = (CEmployee) target;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "employeeName.required");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "employeeLastName.required");
		
		if(employee.getName().length() > 20) errors.rejectValue("name", "employeeName.error");
		if(employee.getLastName().length() > 30) errors.rejectValue("lastName", "employeeLastName.error");
		if(employee.getStreet().length() > 40) errors.rejectValue("street", "employeeStreet.error");
		if(employee.getCity().length() > 30) errors.rejectValue("city", "employeeCity.error");
		if(employee.getPostCode().length() > 6) errors.rejectValue("postCode", "employeePostCode.error");
		if(employee.getEmail().length() > 50) errors.rejectValue("email", "employeeEmail.error");
		if(employee.getPosition().length() > 100) errors.rejectValue("position", "employeePosition.error");

	}

}
