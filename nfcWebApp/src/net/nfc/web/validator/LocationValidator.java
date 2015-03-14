package net.nfc.web.validator;


import net.nfc.web.forms.CNewLocationForm;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Repository("locationValidator")
public class LocationValidator implements Validator {


		@Override
		public boolean supports(Class<?> clazz) {
			return CNewLocationForm.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
		
			CNewLocationForm location = (CNewLocationForm) target;
			
			//System.out.println(location.getSerialNumber());
			//ValidationUtils.rejectIfEmpty(errors, "serialNumber", "locationSerialNumber.required");
			
			if(location.getSerialNumber().length() == 0) errors.rejectValue("serialNumber", "locationSerialNumber.required");
			if(location.getName().length() > 40) errors.rejectValue("name", "locationName.error");
			if(location.getStreet().length() > 40) errors.rejectValue("street", "locationStreet.error");
			if(location.getCity().length() > 40) errors.rejectValue("city", "locationCity.error");
			if(location.getPostCode().length() > 6) errors.rejectValue("postCode", "locationPostCode.error");
			if(location.getDetails().length() > 500 ) errors.rejectValue("details", "locationDetails.error");
			if(location.getTagID().length() > 50 ) errors.rejectValue("tagID", "locationTagID.error");
			if(location.getCompany_id() == 0 ) errors.rejectValue("company_id", "locationCompanyID.error");
			if(location.getSerialNumber().length() > 1000 ) errors.rejectValue("serialNumber", "locationSerialNumber.error");
			if(location.getStatus().equals("null")) errors.rejectValue("status", "locationStatus.required");
			
		}

}


