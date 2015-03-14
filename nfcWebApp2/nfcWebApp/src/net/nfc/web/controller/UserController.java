package net.nfc.web.controller;

import java.util.HashMap;
import java.util.Map;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CConstrains;
import net.nfc.db.entity.CEmployee;
import net.nfc.db.entity.CUserPrivileges;
import net.nfc.db.entity.CUsers;
import net.nfc.web.forms.CAddPrivilege;
import net.nfc.web.forms.CNewUserForm;
import net.nfc.web.service.EmployeeService;
import net.nfc.web.service.UserService;
import net.nfc.web.validator.AddPrivilegeValidator;
import net.nfc.web.validator.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private AddPrivilegeValidator addPrivilegeValidator;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private SaltSource saltSource;
//	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listUsers() throws AdException {
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("employees", employeeService.listEmployeesWithUsers());
		
		return new ModelAndView("user/usersList", model);
	}
	
	
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable("id") long id,
			@ModelAttribute("addPrivilegeToUserForm") CAddPrivilege role,
			BindingResult result) throws AdException {
			
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee",  employeeService.getEmployee(id));
		model.put("privilege", userService.getUserPrivileges(employeeService.getEmployee(id).getUser().getId()));
		model.put("roles", CConstrains.getRoles());
		
		
		
		return new ModelAndView("user/userDetails", model);
	}
	
	
	@RequestMapping(value="/addPrivilege/{employeeID}/{userID}", method=RequestMethod.POST)
	public ModelAndView addPrivileges( @PathVariable("employeeID") long employeeID, @PathVariable("userID") long userID,
			@ModelAttribute("addPrivilegeToUserForm") CAddPrivilege roleForm,
			BindingResult result) throws AdException {
		
		addPrivilegeValidator.validate(roleForm, result);
		if (result.hasErrors()) {
			return getUser(employeeID,roleForm,  result);
		} else {
			if(userService.getUserPrivileges(userID) == null ) {
			// nadaj nowe uprawnienia	
				userService.addPrivileges(userService.getUser(userID), roleForm.getRole());
			}
			else{
				CUserPrivileges privileges = userService.getUserPrivileges(userID);
				privileges.setRole(roleForm.getRole());
				userService.updatePrivileges(privileges);
			}
			return getUser(employeeID, new CAddPrivilege(), result);
		}
	}
	
	@RequestMapping(value="/lockUser/{employeeID}/{userID}", method=RequestMethod.GET)
	public ModelAndView lockUser(@PathVariable("employeeID") long employeeID,@PathVariable("userID") long userID) throws AdException {
		
		CUsers user = userService.getUser(userID);
		user.setIsActive('0');
		
		userService.update(user);
		
		return getUser(employeeID, new CAddPrivilege(), null);
	}
	
	@RequestMapping(value="/unlockUser/{employeeID}/{userID}", method=RequestMethod.GET)
	public ModelAndView unlockUser(@PathVariable("employeeID") long employeeID,@PathVariable("userID") long userID) throws AdException {
		
		CUsers user = userService.getUser(userID);
		user.setIsActive('1');
		
		userService.update(user);
		
		return getUser(employeeID, new CAddPrivilege(), null);
	}
	
	
	
	
	@RequestMapping(value = "/save/{employeeID}", method = RequestMethod.POST)
	public ModelAndView saveUser(@PathVariable("employeeID") long id,
			@ModelAttribute("newUserForm") CNewUserForm userForm,
			BindingResult result) throws AdException{
			
		userValidator.validate(userForm, result);
		if (result.hasErrors()) {
			return addUser(id, userForm, result);//new ModelAndView("user/addUser");
		} else {
			CEmployee emp = employeeService.getEmployee(id);
			CUsers user = new CUsers();
			
			user.setUserName(userForm.getUserName());
			user.setUserPassword(passwordEncoder.encodePassword(userForm.getUserPassword(), null));
			
			userService.addUser(user);

			emp.setUser(userService.getUserByName(user.getUserName()));
			employeeService.updateEmployee(emp);
			
			return new ModelAndView("redirect:/index.jsp");
		}
	}
	
	@RequestMapping(value = "/add/{employeeID}", method = RequestMethod.GET)
	public ModelAndView addUser(@PathVariable("employeeID") long id, @ModelAttribute("newUserForm") CNewUserForm user,
			BindingResult result) throws AdException{
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employeeID", id);
		
		return new ModelAndView("user/addUser", model);
	}
	
}
