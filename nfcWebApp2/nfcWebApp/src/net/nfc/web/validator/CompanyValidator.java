package net.nfc.web.validator;

import java.util.List;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CCompany;
import net.nfc.web.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Repository("companyValidator")
public class CompanyValidator implements Validator {

	@Autowired
	private CompanyService companyService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CCompany.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		CCompany company = (CCompany) target;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "companyName.required");
		ValidationUtils.rejectIfEmpty(errors, "nip", "companyNip.required");
		
		try {
			List<CCompany> lista = companyService.listCompanies();
			
			for (CCompany cCompany : lista) {
				if(cCompany.getNip().equals(company.getNip()) && cCompany.getId() != company.getId()) {
					errors.rejectValue("nip", "companyNipAlreadyExist.error");
				}
			}
			
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
