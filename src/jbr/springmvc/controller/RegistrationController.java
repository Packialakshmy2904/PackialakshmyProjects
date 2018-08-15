package jbr.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jbr.springmvc.dao.UserDaoImpl;
import jbr.springmvc.model.Login;
import jbr.springmvc.model.User;

@Controller
public class RegistrationController {
	@Autowired
	public UserDaoImpl userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", new User());

		return mav;
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) {
		
		boolean validate=userService.validateRegister(user);
		if(validate)
		{
			userService.register(user);
			Login log=new Login();
			log.setMsg("");
			log.setPassword(user.getPassword());
			log.setUsername(user.getUsername());
			return new ModelAndView("LoginSuccess", "pojo", log);
		}
		else
		{
			ModelAndView mav = new ModelAndView("register");
			User u=new User();
			u.setMsg("User "+user.getUsername()+" is already exist");
			mav.addObject("user", u);
			return mav;
		}
			
		
	}
}
