package net.nfc.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CConstrains;
import net.nfc.db.entity.CConstrains.devicesStatus;
import net.nfc.db.entity.CDevices;
import net.nfc.web.forms.CObjectForm;
import net.nfc.web.forms.CSearchForm;
import net.nfc.web.service.DeviceService;
import net.nfc.web.service.EmployeeService;
import net.nfc.web.validator.DeviceValidaotr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/devices")
public class DeviceController {

		@Autowired
		private DeviceService deviceService;
		
		@Autowired
		private EmployeeService employeeService;
		
		@Autowired
		private DeviceValidaotr deviceValidator;
		
		/**
		 * Zapisywanie nowo utworzonego Devices
		 * @param event
		 * @param result
		 * @return
		 * @throws AdException
		 */
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public ModelAndView saveDevices(@ModelAttribute("newDeviceForm") CDevices  device,
				BindingResult result) throws AdException{

			deviceValidator.setMode("save");
			deviceValidator.validate(device, result);
			if (result.hasErrors()) {
				return new ModelAndView("device/addDevice");
			} else {
				device.setDeviceStatus(devicesStatus.CREATED.getText());
				deviceService.addDevice( device);
				return new ModelAndView("redirect:/index.jsp");
			}
		}

		/**
		 * Domyœlne dzia³anie kontrolera - listowanie wszystkich CDevices
		 * @return
		 * @throws AdException
		 */
		@RequestMapping(method = RequestMethod.GET)
		public ModelAndView listDevices(@ModelAttribute("deviceSearchForm")CSearchForm searchForm, @ModelAttribute("list") CObjectForm objectForm) throws AdException {
			Map<String, Object> model = new HashMap<String, Object>();
			if(objectForm.getObjectList() == null) {
			model.put("devices",  deviceService.listDevices());
			}
			else {
				model.put("devices", objectForm.getObjectList());
			}
			model.put("deviceStatus", CConstrains.getDeviceMap());
			
			return new ModelAndView("device/devicesList", model);
		}
		
		@RequestMapping(value="/search", method= RequestMethod.POST)
		public ModelAndView searchDevice(@ModelAttribute("deviceSearchForm") CSearchForm searchForm) throws AdException {
			
			//je¿eli user nie wprowadzi³ ¿adnych kryteriów to odeœlij go do poprzedniego widoku
			if(searchForm.getName().equals("null") && searchForm.getStatus().equals("null")) {
				return listDevices(searchForm, new CObjectForm());
			}
			else {
				List<CDevices> deviceList = deviceService.searchDevice(searchForm);
				CObjectForm objectForm = new CObjectForm();
				objectForm.setObjectList(deviceList);
				
				ModelAndView mav = listDevices(searchForm, objectForm);
				return mav;	
			}
		}

		
		@RequestMapping(value = "/add", method = RequestMethod.GET)
		public ModelAndView addDevice(@ModelAttribute("newDeviceForm") CDevices device,
				BindingResult result) throws AdException{
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			return new ModelAndView("device/addDevice", model);
		}

		/**
		 * Wyœwietlanie szczegó³ów danego CDevices
		 * @param id
		 * @return
		 * @throws AdException
		 */
		@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
		public ModelAndView getDevice(@PathVariable("id") long id) throws AdException {
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("device",  deviceService.getDevice(id));
			
			return new ModelAndView("device/deviceDetails", model);
		}

		@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
		public ModelAndView editDevice(@PathVariable("id") long id, @ModelAttribute("editDeviceForm") CDevices device,
				BindingResult result) throws AdException {
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("device",  deviceService.getDevice(id));
			
			return new ModelAndView("device/editDevice", model);
		}
		
		@RequestMapping(value = "/update/{deviceID}", method = RequestMethod.POST)
		public ModelAndView updateDevice(@PathVariable("deviceID") long id ,@ModelAttribute("editDeviceForm") CDevices device,
				BindingResult result) throws AdException{
			
			deviceValidator.setMode("update");
			deviceValidator.validate(device, result);
			if (result.hasErrors()) {
				ModelAndView mav = editDevice(id, device, result);
				return mav ;//new ModelAndView("company/editCompany");
			} else {
				
				device.setEmployee(deviceService.getDevice(device.getId()).getEmployee());
				deviceService.updateDevice(device);
				ModelAndView mav = getDevice(id);
				return mav;
			}
	}

}

