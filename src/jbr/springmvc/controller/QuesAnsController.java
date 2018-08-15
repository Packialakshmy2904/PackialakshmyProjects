package jbr.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jbr.springmvc.dao.UserDaoImpl;
import jbr.springmvc.model.Answer;
import jbr.springmvc.model.Login;
import jbr.springmvc.model.Query;
import jbr.springmvc.model.QuesAns;
import jbr.springmvc.model.SuccessPojo;
import jbr.springmvc.model.User;

@Controller
public class QuesAnsController {
	@Autowired
	public UserDaoImpl userService;
	
	
	
	@RequestMapping(value = "/postQuery", method = RequestMethod.GET)
	public ModelAndView postQuery(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("queryWindow");
		String uname=request.getParameter("uname");
		System.out.println(uname);
		mav.addObject("uname", uname);

		return mav;
	}

	@RequestMapping(value = "/quesProcess", method = RequestMethod.POST)
	public ModelAndView quesProcess(HttpServletRequest request, HttpServletResponse response) {

		System.out.println(request.getParameter("message"));
		System.out.println(request.getParameter("uname"));
		
		String msg=request.getParameter("message");
		String uname=request.getParameter("uname");
		
		Query query=new Query();
		query.setQuery(msg);
		query.setUsername(uname);
		
		
		userService.addQuery(query);
		
		SuccessPojo pojo=new SuccessPojo();
		pojo.setMsg("Successfully added your query......");
		pojo.setUsername(uname);

		return new ModelAndView("LoginSuccess", "pojo", pojo);
	}
	
	
	
	
	@RequestMapping(value = "/viewAns", method = RequestMethod.POST)
	public ModelAndView viewAns(HttpServletRequest request, HttpServletResponse response) {

		System.out.println(request.getParameter("message"));
		
		String uname=request.getParameter("uname");
		
		QuesAns ques=userService.ViewQuery(uname);
		
		

		return new ModelAndView("ViewMyQues", "ques", ques);
	}
	
	@RequestMapping(value = "/viewAllAns", method = RequestMethod.POST)
	public ModelAndView viewAllAns(HttpServletRequest request, HttpServletResponse response) {

		System.out.println(request.getParameter("message"));
		
		String uname=request.getParameter("uname");
		
		QuesAns ques=userService.ViewAllQuery(uname);
		
		return new ModelAndView("ViewMyQues", "ques", ques);
	}
	
	
	@RequestMapping(value = "/ansProcess", method = RequestMethod.POST)
	public ModelAndView ansProcess(HttpServletRequest request, HttpServletResponse response) {

		System.out.println(request.getParameter("message"));
		
		String msg=request.getParameter("message");
		String uname=request.getParameter("uname");
		String qid=request.getParameter("qid");
		
		
		Answer ans=new Answer();
		ans.setAnswer(msg);
		ans.setQid(qid);
		ans.setUname(uname);
		
		
		userService.addAnswer(ans);
		
		SuccessPojo pojo=new SuccessPojo();
		pojo.setMsg("Successfully added your answer......");
		pojo.setUsername(uname);

		return new ModelAndView("LoginSuccess", "pojo", pojo);
	}
	
}
