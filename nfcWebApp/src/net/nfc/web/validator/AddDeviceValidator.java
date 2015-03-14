package net.nfc.web.validator;

import net.nfc.web.forms.CAddDeviceToEmployeeForm;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Repository("addDeviceValidator")
public class AddDeviceValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return CAddDeviceToEmployeeForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		CAddDeviceToEmployeeForm device = (CAddDeviceToEmployeeForm) target;
		
		if(device.getDevice_id() == 0) errors.rejectValue("device_id", "addDeviceToEmployeeID.required");
		
	}
	
}
