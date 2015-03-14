package net.nfc.web.validator;

import java.util.List;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CDevices;
import net.nfc.web.controller.DeviceController;
import net.nfc.web.forms.CNewEventForm;
import net.nfc.web.service.DeviceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Repository("devicesValidator")
public class DeviceValidaotr implements Validator {

	@Autowired
	private DeviceService deviceService;
	
	private String mode;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CDevices.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		CDevices device = (CDevices) target;
		
		ValidationUtils.rejectIfEmpty(errors, "deviceImei", "deviceImei.required");
		
		//if(mode.equals("save")) {
			try {
				List<CDevices> lista = deviceService.listDevices();
				
				for (CDevices cDevices : lista) {
					if(cDevices.getDeviceImei().equals(device.getDeviceImei()) && cDevices.getId() != device.getId()) {
						errors.rejectValue("deviceImei", "deviceImeiAlreadyExist.error");
					}
				}
			} catch (AdException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	//	}
		if(device.getDeviceType().length() > 100) errors.rejectValue("deviceType", "deviceType.error");
		if(device.getDeviceImei().length() > 100)	errors.rejectValue("deviceImei", "deviceImei.error");
		if(device.getImsi().length() > 15) errors.rejectValue("imsi", "deviceImsi.error");
		if(device.getDescription().length() > 100) errors.rejectValue("description", "deviceDescription.error");
		if(device.getPhoneNumber().length() > 12) errors.rejectValue("phoneNumber", "devicePhoneNumber.error");
		if(mode.equals("update")) {
			if(device.getDeviceStatus().equals("null")) errors.rejectValue("deviceStatus", "deviceStatus.required");
		}
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
	
	
}
