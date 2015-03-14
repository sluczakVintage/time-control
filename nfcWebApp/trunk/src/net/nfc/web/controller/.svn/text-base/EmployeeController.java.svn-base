package net.nfc.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CCompany;
import net.nfc.db.entity.CDevices;
import net.nfc.db.entity.CEmployee;
import net.nfc.db.entity.CLocation;
import net.nfc.db.entity.CConstrains.devicesStatus;
import net.nfc.web.forms.CAddDeviceToEmployeeForm;
import net.nfc.web.forms.CNewEmployeeForm;
import net.nfc.web.forms.CObjectForm;
import net.nfc.web.forms.CSearchForm;
import net.nfc.web.service.CompanyService;
import net.nfc.web.service.DeviceService;
import net.nfc.web.service.EmployeeService;
import net.nfc.web.service.LocationService;
import net.nfc.web.validator.AddDeviceValidator;
import net.nfc.web.validator.EmployeeValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private EmployeeValidator employeeValidator;
	
	@Autowired
	private AddDeviceValidator addDeviceValidator;
			
	/**
	 * Zapisywanie nowo utworzonego Employye
	 * @param event
	 * @param result
	 * @return
	 * @throws AdException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveEmployye(@ModelAttribute("newEmployeeForm") CEmployee employee,
			BindingResult result) throws AdException{
			
		employeeValidator.validate(employee, result);
		if (result.hasErrors()) {
			return new ModelAndView("employee/addEmployee");
		} else {
			employeeService.addEmployee( employee);
			return new ModelAndView("redirect:/index.jsp");
		}
		

	}

	/**
	 * Domyœlne dzia³anie kontrolera - listowanie wszystkich CEmployye
	 * @return
	 * @throws AdException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listEmployees(@ModelAttribute("employeeSearchForm")CSearchForm searchForm, 
			@ModelAttribute("list") CObjectForm objectForm //,
			//@ModelAttribute("addDeviceToEmployeeForm") CAddDeviceToEmployeeForm deviceForm,
			//BindingResult result
			) throws AdException {
			
		Map<String, Object> model = new HashMap<String, Object>();
		if(objectForm.getObjectList() == null) {
		model.put("employees",  employeeService.listEmployees());
		}
		else {
			model.put("employees", objectForm.getObjectList());
		}
		return new ModelAndView("employee/employeeList", model);
	}
	
	
	@RequestMapping(value="/search", method= RequestMethod.POST)
	public ModelAndView searchEmployee(@ModelAttribute("employeeSearchForm") CSearchForm searchForm) throws AdException {
		
		//je¿eli user nie wprowadzi³ ¿adnych kryteriów to odeœlij go do poprzedniego widoku
		if(searchForm.getName().equals("null") && searchForm.getUserName().equals("null")) {
			return listEmployees(searchForm, new CObjectForm());
		}
		else {
			List<CEmployee> employeeList = employeeService.searchEmployee(searchForm);
			CObjectForm objectForm = new CObjectForm();
			objectForm.setObjectList(employeeList);
			
			ModelAndView mav = listEmployees(searchForm, objectForm);
			return mav;	
		}
	}
	
	

			
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addEmployee(@ModelAttribute("newEmployeeForm") CEmployee employee,
			BindingResult result) throws AdException{
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("devices", deviceService.listDevices());
		
		return new ModelAndView("employee/addEmployee", model);
	}

	/**
	 * Wyœwietlanie szczegó³ów danego CEmployye
	 * @param id
	 * @return
	 * @throws AdException
	 */
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public ModelAndView getEmployee(@PathVariable("id") long id, 
			@ModelAttribute("addDeviceToEmployeeForm") CAddDeviceToEmployeeForm deviceForm,
			BindingResult result) throws AdException {
			
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee",  employeeService.getEmployee(id));
		model.put("devices", deviceService.getDevicesForEmployee(id));
		model.put("freeDevices", deviceService.getFreeDevices());
		
		return new ModelAndView("employee/employeeDetails", model);
	}

	@RequestMapping(value = "/addDevice/{employeeID}", method = RequestMethod.POST)
	public ModelAndView addDeviceToEmployye(@PathVariable("employeeID") long id,
			@ModelAttribute("addDeviceToEmployeeForm") CAddDeviceToEmployeeForm deviceForm,
			BindingResult result) throws AdException{
			
		addDeviceValidator.validate(deviceForm, result);
		if (result.hasErrors()) {
			System.out.println("tu");
			return getEmployee(id,deviceForm,  result);
		} else {
			
			System.out.println("czy tu");
			CDevices device = deviceService.getDevice(deviceForm.getDevice_id());
			device.setEmployee(employeeService.getEmployee(id));
			deviceService.updateDevice(device);
			return getEmployee(id, new CAddDeviceToEmployeeForm(), result);
		}
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editEmployee(@PathVariable("id") long id, @ModelAttribute("editEmployeeForm") CEmployee employee,
			BindingResult result) throws AdException {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee",  employeeService.getEmployee(id));
		model.put("devices", deviceService.listDevices());
		
		return new ModelAndView("employee/editEmployee", model);
	}
			
	@RequestMapping(value = "/update/{employeeID}", method = RequestMethod.POST)
	public ModelAndView updateEmployee(@PathVariable("employeeID") long id,@ModelAttribute("editEmployeeForm") CEmployee employee,
			BindingResult result) throws AdException{
		
		employeeValidator.validate(employee, result);
		if (result.hasErrors()) {
			ModelAndView mav = editEmployee(id, employee, result);
			return mav ;//new ModelAndView("company/editCompany");
		} else {
			
			employee.setUser(employeeService.getEmployee(id).getUser());
			employeeService.updateEmployee(employee);
			ModelAndView mav = getEmployee(id,new CAddDeviceToEmployeeForm(),result);
			return mav;
		}
	}

	@RequestMapping(value = "/unsignDevice/{deviceID}/{employeeID}", method=RequestMethod.GET)
	public ModelAndView unsignDevice(@PathVariable("deviceID") long deviceID,@PathVariable("employeeID") long employeeID) throws AdException{
			
			CDevices device = deviceService.getDevice(deviceID);
			device.setEmployee(null);
			deviceService.updateDevice(device);
			
			return getEmployee(employeeID, new CAddDeviceToEmployeeForm(), null);
		
	}
	
}
	


