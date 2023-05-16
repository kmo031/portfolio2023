package com.sangmin.portfolio.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sangmin.portfolio.dto.UserDto;
import com.sangmin.portfolio.model.Users;
import com.sangmin.portfolio.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService{

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users member = userRepository.findByUserName(userName);
		boolean enabled = true;
	    boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
		if(member == null) {
			throw new UsernameNotFoundException(userName);
		}
		UserDto.ResponseUserDto userCustom = new UserDto.ResponseUserDto(member.getUserName()
                , member.getPassword()
                , enabled, accountNonExpired, credentialsNonExpired, accountNonLocked
                , authorities(member.getRole().toString())
            
                , member.getName() // 이름
        );
		return userCustom;

	}
	
	/* 회원가입 */

	public Long userJoin(UserDto.RequestUserDto dto) {
		/* 비밀번호 암호화 */
		dto.encryptPassword(encoder.encode(dto.getPassword()));
		
		Users user = dto.toEntity();
		userRepository.save(user);
		log.info("DB에 회원 저장 성공");
		
		return user.getId();
	}
	
	 private static Collection authorities(String role){
	        Collection authorities = new ArrayList<>();
	        // DB에 저장한 USER_ROLE 이 1이면 ADMIN 권한, 아니면 ROLE_USER 로 준다.
	        if(role.equals("ADMIN")){
	            authorities.add(new SimpleGrantedAuthority("ADMIN"));
	        }else{
	            authorities.add(new SimpleGrantedAuthority("USER"));
	        }
	        return authorities;
	    }
	

}
