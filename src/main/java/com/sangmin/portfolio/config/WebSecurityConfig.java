package com.sangmin.portfolio.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.sangmin.portfolio.model.enms.Role;
import com.sangmin.portfolio.photoBoard.controller.PhotoBoardController;
import com.sangmin.portfolio.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
@Slf4j
public class WebSecurityConfig {//extends WebSecurityConfigurerAdapter {


	   private final CustomOAuth2UserService customOAuth2UserService;

	   @Bean
	   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		   

		   http
           .csrf().disable()
           .headers().frameOptions().disable()
           .and()
               .authorizeRequests()
               .antMatchers("/**").permitAll()
               .antMatchers("/admin/**").hasRole(Role.ADMIN.name())    
               .anyRequest().authenticated()   
           .and()
           .formLogin()
           .loginPage("/login")
           .loginProcessingUrl("/loginProc")
           .defaultSuccessUrl("/")
           .failureHandler(failureHandler())
           .usernameParameter("username")
           .passwordParameter("password")
           .permitAll()
           .and()
               .logout()
                   .logoutSuccessUrl("/").permitAll()
           
           .and()
               .oauth2Login() 
               .loginPage("/login")
                   .userInfoEndpoint()
                       .userService(customOAuth2UserService);
		   
		    
	
		   

	       return http.build();
	   }
	   
	   @Bean
		public BCryptPasswordEncoder encodePwd() {
			return new BCryptPasswordEncoder();
		}
	   
	   // 실패 처리를 위한 Handler
		  @Bean
		  public AuthenticationFailureHandler failureHandler() {
			  return new CustomAuthFailureHandler();
		  }
	   
	   

}