package com.sangmin.portfolio.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class MainController {
	
	 @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model) {
//	    model.addAttribute("posts", postsService.findAllDesc());
//	    SessionUser user = (SessionUser) httpSession.getAttribute("user");
//	    
//	    if (user != null) {
//	        model.addAttribute("userName", user.getName());
//	    }
	    return "login";
	}
}
