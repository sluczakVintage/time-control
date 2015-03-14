package net.nfc.web.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CConstrains;
import net.nfc.db.entity.CConstrains.locationStatus;
import net.nfc.db.entity.CLocation;
import net.nfc.web.forms.CNewLocationForm;
import net.nfc.web.forms.CObjectForm;
import net.nfc.web.forms.CSearchForm;
import net.nfc.web.service.CompanyService;
import net.nfc.web.service.LocationService;
import net.nfc.web.service.UserService;
import net.nfc.web.validator.LocationValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/locations")
public class LocationController {

		@Autowired
		private LocationService locationService;

		@Autowired
		private CompanyService companyService;
		
		@Autowired
		private UserService userService;
		
		@Autowired
		private LocationValidator locationValidator;
		
		/**
		 * Zapisywanie nowo utworzonego LOCATION
		 * @param event
		 * @param result
		 * @return
		 * @throws AdException
		 */
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public ModelAndView saveLocation(@ModelAttribute("newLocationForm") CNewLocationForm locationForm,
				BindingResult result) throws AdException{
			locationForm.setStatus(" ");
			locationValidator.validate(locationForm, result);
			if (result.hasErrors()) {
				return addLocations(locationForm, result);
			} else {
				CLocation location = new CLocation();
				
				location.setCity(locationForm.getCity());
				location.setCompany(companyService.getCompany(locationForm.getCompany_id()));
				location.setDetails(locationForm.getDetails());
				location.setName(locationForm.getName());
				location.setPostCode(locationForm.getPostCode());
				location.setSerialNumber(locationForm.getSerialNumber());
				location.setStreet(locationForm.getStreet());
				location.setTagID(locationForm.getTagID());
				location.setStatus(locationStatus.CREATED.getText());
				location.setCreationDate(new Timestamp(new java.util.Date().getTime()));
				location.setCreator(userService.getUser(3));
				
				 locationService.addLocation(location);
				return new ModelAndView("redirect:/index.jsp");
			}
		}

		/**
		 * Domyœlne dzia³anie kontrolera - listowanie wszystkich CLocation
		 * @return
		 * @throws AdException
		 */
		@RequestMapping(method = RequestMethod.GET)
		public ModelAndView listLocations(@ModelAttribute("locationSearchForm")CSearchForm searchForm, @ModelAttribute("list") CObjectForm objectForm) throws AdException {
			Map<String, Object> model = new HashMap<String, Object>();
			if(objectForm.getObjectList() == null) {
			model.put("locations",  locationService.listLocations());
			}
			else {
				model.put("locations", objectForm.getObjectList());
			}
			
			model.put("locationStatus", CConstrains.getLocationMap());
			
			return new ModelAndView("location/locationList", model);
		}

		
		@RequestMapping(value="/search", method= RequestMethod.POST)
		public ModelAndView searchLocation(@ModelAttribute("locationSearchForm") CSearchForm searchForm) throws AdException {
			
			//je¿eli user nie wprowadzi³ ¿adnych kryteriów to odeœlij go do poprzedniego widoku
			if(searchForm.getName().equals("null") && searchForm.getStatus().equals("null") && searchForm.getCity().equals("null")) {
				return listLocations(searchForm, new CObjectForm());
			}
			else {
				List<CLocation> locationList = locationService.searchLocation(searchForm);
				CObjectForm objectForm = new CObjectForm();
				objectForm.setObjectList(locationList);
				
				ModelAndView mav = listLocations(searchForm, objectForm);
				return mav;	
			}
		}
		
		
		
		
		@RequestMapping(value = "/add", method = RequestMethod.GET)
		public ModelAndView addLocations(@ModelAttribute("newLocationForm") CNewLocationForm locationForm,
				BindingResult result) throws AdException{
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("companies", companyService.listCompanies());
			return new ModelAndView("location/addLocation", model);
		}

		/**
		 * Wyœwietlanie szczegó³ów danego CLocation
		 * @param id
		 * @return
		 * @throws AdException
		 */
		@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
		public ModelAndView getLocation(@PathVariable("id") long id) throws AdException {
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("location",  locationService.getLocation(id));
			
			return new ModelAndView("location/locationDetails", model);
		}

		@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
		public ModelAndView editLocation(@PathVariable("id") long id, @ModelAttribute("editLocationForm") CNewLocationForm locationForm,
				BindingResult result) throws AdException {
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("location",  locationService.getLocation(id));
			model.put("companies", companyService.listCompanies());
			
			return new ModelAndView("location/editLocation", model);
		}
		
		@RequestMapping(value = "/update/{locationID}", method = RequestMethod.POST)
		public ModelAndView updateLocation(@PathVariable("locationID") long id, @ModelAttribute("editLocationForm") CNewLocationForm locationForm,
				BindingResult result) throws AdException{
			
			locationValidator.validate(locationForm, result);
			if (result.hasErrors()) {
				ModelAndView mav = editLocation(id, locationForm, result);
				return mav ;//new ModelAndView("company/editCompany");
			} else {
				CLocation location = locationService.getLocation(locationForm.getId());
				
				location.setCity(locationForm.getCity());
				location.setCompany(companyService.getCompany(locationForm.getCompany_id()));
				location.setDetails(locationForm.getDetails());
				location.setName(locationForm.getName());
				location.setPostCode(locationForm.getPostCode());
				location.setSerialNumber(locationForm.getSerialNumber());
				location.setStreet(locationForm.getStreet());
				location.setTagID(locationForm.getTagID());
				location.setStatus(locationForm.getStatus());
				
				locationService.updateLocation(location);
				ModelAndView mav = getLocation(id);
				return mav;
			}
		}

}

