package com.sangmin.portfolio.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sangmin.portfolio.model.SessionUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {
	
	private final HttpSession httpSession;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getBoardsList(HttpServletRequest request,Model model, Pageable pageable) throws Exception {
    
	Long uniqueId =1L;
	
	SessionUser user = (SessionUser) httpSession.getAttribute("user");

 
    
    if(user != null){
        model.addAttribute("userName", user.getName());
    }
    
    return "index";
    }
}
