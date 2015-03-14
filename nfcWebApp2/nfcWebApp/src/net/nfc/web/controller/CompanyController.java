package net.nfc.web.controller;

/**
 * I know that the OP mayn't be interested but I would still answer it so that if somebody look for this kind of exception in future he can have some idea.
I google for this exception and find one or two solutions and none of them was relevant to my situation.
I am using Tomcat and I have created one project using tomcat plugin in MyEclipse 6. One day I start my tomcat tomcat and start getting above exception.
If I remove the context definition file from tomcat then it starts without any exception or error. I cleaned up my classes and rebuild the project ofcourse using IDE facility but no help.
After that I concluded that there some problem with my project only so I opened the myProject.xml file under Tomcat 5.5\conf\Catalina\localhost directory and find that it points to work folder also.
So I opened the work folder of my project and remove everything into that including two SER files (tldCache.ser and session.ser) and other Java files(complied JSPs). And, and the error is gone.
Hope this is not Thread Hijecking.  
 */




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CCompany;
import net.nfc.web.forms.CObjectForm;
import net.nfc.web.forms.CSearchForm;
import net.nfc.web.service.CompanyService;
import net.nfc.web.service.LocationService;
import net.nfc.web.validator.CompanyValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/companies")
public class CompanyController {

		@Autowired
		private LocationService locationService;

		@Autowired
		private CompanyService companyService;
		
		@Autowired
		private CompanyValidator companyValidator;
		
		/**
		 * Zapisywanie nowo utworzonego company
		 * @param event
		 * @param result
		 * @return
		 * @throws AdException
		 */
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public ModelAndView saveCompany(@ModelAttribute("newCompanyForm") CCompany  company,
				BindingResult result) throws AdException{
			
			companyValidator.validate(company, result);
			if (result.hasErrors()) {
				return new ModelAndView("company/addCompany");
			} else {
				 companyService.addCompany( company);
					return new ModelAndView("redirect:/index.jsp");
			}
//			 companyService.addCompany( company);
//			return new ModelAndView("redirect:/index.jsp");
		}

		/**
		 * Domyœlne dzia³anie kontrolera - listowanie wszystkich CCompany
		 * @return
		 * @throws AdException
		 */
		@RequestMapping(method = RequestMethod.GET)
		public ModelAndView listCompanies(@ModelAttribute("companySearchForm")CSearchForm searchForm, @ModelAttribute("list") CObjectForm objectForm) throws AdException {
			Map<String, Object> model = new HashMap<String, Object>();
			if(objectForm.getObjectList() == null) {
			model.put("companies",  companyService.listCompanies());
			}
			else {
				model.put("companies", objectForm.getObjectList());
			}
			return new ModelAndView("company/companyList", model);
		}

		
		@RequestMapping(value="/search", method= RequestMethod.POST)
		public ModelAndView searchCompany(@ModelAttribute("companySearchForm") CSearchForm searchForm) throws AdException {
			
			//je¿eli user nie wprowadzi³ ¿adnych kryteriów to odeœlij go do poprzedniego widoku
			if(searchForm.getName().equals("null") && searchForm.getNip().equals("null") && searchForm.getCity().equals("null")) {
				return listCompanies(searchForm, new CObjectForm());
			}
			else {
				List<CCompany> companyList = companyService.searchCompany(searchForm);
				CObjectForm objectForm = new CObjectForm();
				objectForm.setObjectList(companyList);
				
				ModelAndView mav = listCompanies(searchForm, objectForm);
				return mav;	
			}
		}
		
		
		
		@RequestMapping(value = "/add", method = RequestMethod.GET)
		public ModelAndView addCompany(@ModelAttribute("newCompanyForm") CCompany company,
				BindingResult result) throws AdException{
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			return new ModelAndView("company/addCompany", model);
		}

		/**
		 * Wyœwietlanie szczegó³ów danego CCompany
		 * @param id
		 * @return
		 * @throws AdException
		 */
		@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
		public ModelAndView getCompany(@PathVariable("id") long id) throws AdException {
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("company",  companyService.getCompany(id));
			model.put("locations", locationService.listLocationsForCompany(id));
			
			return new ModelAndView("company/companyDetails", model);
		}

		@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
		public ModelAndView editComapny(@PathVariable("id") long id, @ModelAttribute("editCompanyForm") CCompany company,
				BindingResult result) throws AdException {
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("company",  companyService.getCompany(id));
			
			return new ModelAndView("company/editCompany", model);
		}
		
		@RequestMapping(value = "/update/{companyID}", method = RequestMethod.POST)
		public ModelAndView updateComapny(@PathVariable("companyID") long id , @ModelAttribute("editCompanyForm") CCompany company,
				BindingResult result) throws AdException{
			
			companyValidator.validate(company, result);
			if (result.hasErrors()) {
				ModelAndView mav = editComapny(id, company, result);
				return mav ;//new ModelAndView("company/editCompany");
			} else {
				companyService.updateCompany(company);
				ModelAndView mav = getCompany(id);
				return mav;
			}
		}

}
