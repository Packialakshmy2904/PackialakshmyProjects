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
public class LoginController {
	@Autowired
	public UserDaoImpl userService;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String login(){
	    return "redirect:Welcome.jsp";
	}  
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new Login());

		return mav;
	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") Login login) {

		User u=userService.validateUser(login);

		if(null!=u)
		{
			login.setMsg("");
			return new ModelAndView("LoginSuccess", "pojo", login);
		}
		else
		{
			login.setMsg("Invalid credentilas..... Please provide valid credentials");
			return new ModelAndView("login", "login", login);
		}
		//return new ModelAndView("LoginSuccess", "firstname", u.getFirstname());
	}
}
