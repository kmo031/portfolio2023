//package com.sangmin.portfolio.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sangmin.portfolio.service.LoginService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@RequestMapping(value = "/login/oauth2", produces = "application/json")
//@Slf4j
//public class LoginController {
//
//    LoginService  loginService;
//    public LoginController(LoginService loginService) {
//        this.loginService = loginService;
//    }
//
//    @GetMapping("/code/{registrationId}")
//    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
// 
//        loginService.socialLogin(code, registrationId);
//    }
//}