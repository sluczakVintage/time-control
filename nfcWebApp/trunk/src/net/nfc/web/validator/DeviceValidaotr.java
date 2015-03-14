package net.nfc.web.validator;

import net.nfc.db.entity.CDevices;
import net.nfc.web.forms.CNewEventForm;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Repository("devicesValidator")
public class DeviceValidaotr implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CDevices.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		CDevices device = (CDevices) target;
		
		ValidationUtils.rejectIfEmpty(errors, "deviceImei", "deviceImei.required");
		
		
		if(device.getDeviceType().length() > 100) errors.rejectValue("deviceType", "deviceType.error");
		if(device.getDeviceImei().length() > 100)	errors.rejectValue("deviceImei", "deviceImei.error");
		if(device.getImsi().length() > 15) errors.rejectValue("imsi", "deviceImsi.error");
		if(device.getDescription().length() > 100) errors.rejectValue("description", "deviceDescription.error");
		if(device.getPhoneNumber().length() > 12) errors.rejectValue("phoneNumber", "devicePhoneNumber.error");
		
		
	}
	
}
