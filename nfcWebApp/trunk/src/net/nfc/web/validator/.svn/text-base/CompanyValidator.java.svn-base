package net.nfc.web.validator;

import net.nfc.db.entity.CCompany;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Repository("companyValidator")
public class CompanyValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CCompany.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		CCompany company = (CCompany) target;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "companyName.required");
		ValidationUtils.rejectIfEmpty(errors, "nip", "companyNip.required");
		
		if(company.getName().length() > 40) errors.rejectValue("name", "companyName.error");
		if(company.getNip().length() != 10)	errors.rejectValue("nip", "companyNip.error");
		if(company.getStreet().length() > 40) errors.rejectValue("street", "companyStreet.error");
		if(company.getCity().length() > 40) errors.rejectValue("city", "companyCity.error");
		if(company.getPostCode().length() > 6) errors.rejectValue("postCode", "companyPostCode.error");
		if(company.getCountry().length() > 10) errors.rejectValue("country", "companyCountry.error");
		if(company.getPhoneNumber().length() > 12) errors.rejectValue("phoneNumber", "companyPhoneNumber.error");
		if(company.getContactPersonName().length() > 20) errors.rejectValue("contactPersonName", "companyContactPersonName.error");
		if(company.getContactPersonLastName().length() > 20) errors.rejectValue("contactPersonLastName", "companyContactPersonLastName.error");
		if(company.getContactPersonDescription().length() > 50) errors.rejectValue("description", "companyDescription.error");
		if(company.getEmail().length() > 50) errors.rejectValue("email", "companyEmail.error");
		
		
	}

}
