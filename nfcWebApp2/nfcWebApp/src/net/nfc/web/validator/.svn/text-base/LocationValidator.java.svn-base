package net.nfc.web.validator;


import java.util.List;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CLocation;
import net.nfc.web.forms.CNewLocationForm;
import net.nfc.web.service.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Repository("locationValidator")
public class LocationValidator implements Validator {

		@Autowired
		private LocationService locationService;
	

		@Override
		public boolean supports(Class<?> clazz) {
			return CNewLocationForm.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
		
			CNewLocationForm location = (CNewLocationForm) target;
			
			//System.out.println(location.getSerialNumber());
			//ValidationUtils.rejectIfEmpty(errors, "serialNumber", "locationSerialNumber.required");
			
			try {
				List<CLocation> lista = locationService.listLocations();
				for (CLocation cLocation : lista) {
					if(cLocation.getTagID().equals(location.getTagID()) && cLocation.getId() != location.getId()) {
						errors.rejectValue("tagID", "locationTagIdAlreadyExist.error");
					}
				}
				
			} catch (AdException e) {
			}
			
			
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
			if(location.getTagID().length() ==0 ) errors.rejectValue("tagID", "locationTagID.required");
		}

}


