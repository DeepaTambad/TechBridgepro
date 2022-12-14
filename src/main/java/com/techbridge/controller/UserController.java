package com.techbridge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.techbridge.dto.UserDTO;
import com.techbridge.exception.EmailOrMobileNumberAlreadyExistException;
import com.techbridge.model.UserModel;
import com.techbridge.service.UserModelService;

@Controller
public class UserController {

	@Autowired
	private UserModelService usermodelService;


	@RequestMapping("/index")
	public String welcome() {
		return "index";
	}
	//
	@RequestMapping("/welcome")
	public String welcome1() {
		return "welcome";
	}

	@RequestMapping("/login1")
	public String login1() {
		return "login1";
	}

	@RequestMapping("/update")
	public String update() {
		return "update";
	}
	
	@RequestMapping("/addAccount")
	public String addAccount() {
		return "addAccount";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("command")  UserDTO usermodelDto, Map<String, Object> model) {

		try {

			if(usermodelDto.getRole().equalsIgnoreCase("user")) {
				usermodelService.insertUser(usermodelDto);

				//model.put("usermodels", list);
				model.put("confirmation", "Registred Successfully");
				return "admin";
			}
		}
		catch(EmailOrMobileNumberAlreadyExistException e) {
			model.put("confirmation", "Email/MobileNumber Already Exist");
			return "addAccount";
		}
		return "addAccount";
	}


	@PostMapping(value="/update")
	public String updateUser(@ModelAttribute("command") UserDTO userDto, BindingResult result, Map<String, Object> model ) 
	{
		List<UserDTO> list = usermodelService.getAllUsers();

		if(userDto.getRole().equalsIgnoreCase("Admin") && (userDto.getUserActive().equalsIgnoreCase("Offline"))) 
		{
			model.put("confirmation", "Can not Update Admin as offline");
			return "update";
		}
		try {

			model.put("usermodels", prepareModel(usermodelService.updateUser(userDto)));	
			model.put("usermodels", list);
			model.put("confirmation", "Record is Updated");
			return "usermodelsList";

		}
		catch(EmailOrMobileNumberAlreadyExistException e) {
			model.put("confirmation", "Can't Update!!! Duplicate Records Found!!! ");
			return "update";
		}
		//model.put("usermodels", list);
		//return "usermodelsList";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUserModel(@RequestParam("userId") int userId, Map<String, Object> model) {
		usermodelService.deleteUser(userId);

		List<UserDTO> userDtoList = usermodelService.getAllUsers();

		model.put("usermodels", userDtoList);
		model.put("confirmation", "Record is Deleted");
		return new ModelAndView("usermodelsList");
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editUserModel( @RequestParam("userId") int userId, @ModelAttribute("command") UserDTO userDto,BindingResult result, Map<String, Object> model ) {
		userDto = usermodelService.getUserById(userId);
		//userDto =  usermodelService.getAllUsers();
		model.put("UserDto", userDto);

		return "update";
	}

	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public String getAllUser(Map<String, Object> model) {

		//checking email exist or not
		List<UserDTO> list = usermodelService.getAllUsers();
		model.put("usermodels", list);

		System.out.println(list);
		//return "usermodelsList";
		return "usermodelsList";
	}

	private UserModel prepareModel(UserDTO usermodelDto){
		UserModel usermodel = new UserModel();
		//usermodel.setUserId(usermodelDto.getUserEmail());
		usermodel.setEmail(usermodelDto.getEmail());
		usermodel.setUserName(usermodelDto.getUserName());
		usermodel.setPassword(usermodelDto.getPassword());
		usermodel.setMobileNumber(usermodelDto.getMobileNumber());
		usermodel.setUserActive(usermodelDto.getUserActive());
		usermodel.setRole(usermodelDto.getRole());
		usermodel.setUserId(usermodelDto.getUserId());
		return usermodel;
	}

	private List<UserDTO> prepareListofDto(List<UserDTO> list){
		List<UserDTO> dtos = null;
		if(list != null && !list.isEmpty()){
			dtos = new ArrayList<UserDTO>();
			UserDTO dto = null;
			for(UserDTO usermodel : list){
				dto.setUserId(usermodel.getUserId());
				dto.setEmail(usermodel.getEmail());
				dto.setUserName(usermodel.getUserName());
				dto.setPassword(usermodel.getPassword());
				dto.setMobileNumber(usermodel.getMobileNumber());
				dto.setUserActive(usermodel.getUserActive());
				dto.setRole(usermodel.getRole());
				dto.setUserId(usermodel.getUserId());
				dtos.add(dto);
			}
		}
		return dtos;
	}

	private UserDTO prepareUserModelDTO(UserModel usermodel){
		UserDTO dto = new UserDTO();
		dto.setEmail(usermodel.getEmail());
		dto.setUserName(usermodel.getUserName());
		dto.setPassword(usermodel.getPassword());
		dto.setMobileNumber(usermodel.getMobileNumber());
		dto.setUserActive(usermodel.getUserActive());
		dto.setRole(usermodel.getRole());
		dto.setUserId(usermodel.getUserId());
		return dto;
	}


	

	@RequestMapping(path="/validateuser", method=RequestMethod.POST)
	public String loginValidate(HttpSession session, @Valid @ModelAttribute("command") UserDTO userDto, Map<String, Object> model,Errors errors) {

		List<UserDTO> dtoList = usermodelService.getAllUsers();

		if(errors.hasErrors()) {
			System.out.println("Errors Checking");
			return "admin";
		}
		else 
		{
			for(UserDTO user1: dtoList) {

				if((user1.getEmail().equals(userDto.getEmail())) 
						&& (user1.getPassword().equals(userDto.getPassword())) && 
						(user1.getRole().equalsIgnoreCase("Admin")))
				{
					session.setAttribute("UserId",user1.getUserId());
					session.setAttribute("UserName",user1.getUserName());
					//model.put("confirmation", "Welcome User"+userDto.getUserName());
					return "adminpage";
				} 				
			}

			for(UserDTO user1: dtoList) {
				if((user1.getEmail().equals(userDto.getEmail())) 
						&& (user1.getPassword().equals(userDto.getPassword())) && 
						(user1.getRole().equalsIgnoreCase("user")))
				{
					if(user1.getUserActive().equalsIgnoreCase("Online")) {
						session.setAttribute("UserId",user1.getUserId());
						session.setAttribute("UserName",user1.getUserName());

						return "userpage";
					}
					else {
						model.put("confirmation", "Current User currently Offline");
						return "admin";
					}
				}
			}
		}
		//model.put("confirmation", "Welcome User"+userDto.getUserName());
		//return "userpage";
		model.put("confirmation", "Account does't Exist");
		return "addAccount";
	}
}