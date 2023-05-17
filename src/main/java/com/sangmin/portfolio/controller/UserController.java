package com.sangmin.portfolio.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sangmin.portfolio.dto.UserDto;
import com.sangmin.portfolio.service.UserDetailService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/users")
public class UserController  {
	
	  @Autowired
	  UserDetailService userDtailService;
	
	  @GetMapping("/login")
	  public String loginForm() {
	      return "/user/login";
	  }
	  
	  @PostMapping("/login")
	  public String loginFormPost() {
	      return "/user/login";
	  }

		/*
		 * 회원가입 폼
		 * @return
		 */
	  @GetMapping("/signUp")
	    public String signUpForm( Model model) {
		  
	
	        return "/user/signUp";
	    }

	    /**
	     * 회원가입 진행
	     * @param user
	     * @return
	     */
		@PostMapping("/signUp")
		public String joinProc(@Valid UserDto.RequestUserDto userDto, BindingResult bindingResult, Model model) {
			
			/* 검증 */
			if(bindingResult.hasErrors()) {
				/* 회원가입 실패 시 입력 데이터 값 유지 */
				model.addAttribute("userDto", userDto);
				
				/* 유효성 검사를 통과하지 못 한 필드와 메시지 핸들링 */
				Map<String, String> errorMap = new HashMap<>();
				
				for(FieldError error : bindingResult.getFieldErrors()) {
					errorMap.put("valid_"+error.getField(), error.getDefaultMessage());
					log.info("회원가입 실패 ! error message : "+error.getDefaultMessage());
				}
				
				/* Model에 담아 view resolve */
				for(String key : errorMap.keySet()) {
					model.addAttribute(key, errorMap.get(key));
				}

				/* 회원가입 페이지로 리턴 */
				return "/user/signUp";
			}
			
			// 회원가입 성공 시
			userDtailService.userJoin(userDto);
			log.info("회원가입 성공");
			return "redirect:/";
		}

   
}