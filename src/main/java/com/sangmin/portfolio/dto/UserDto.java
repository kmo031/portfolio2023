package com.sangmin.portfolio.dto;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.sangmin.portfolio.model.Users;
import com.sangmin.portfolio.model.enms.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UserDto {
	
	/* 회원 서비스 요청 RequestDTO 클래스 */
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Builder
	@Setter
	public static class RequestUserDto{
		private Long id;
		
		@NotBlank(message = "아이디는 필수 입력값입니다.")
		@Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
		private String userName;
		
		
		private String name;
		@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
		private String password;
		
		/*
		 * @NotBlank(message = "닉네임은 필수 입력값입니다.")
		 * 
		 * @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$" , message =
		 * "닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.") private String nickname;
		 */
		
//		@NotBlank(message = "이메일은 필수 입력값입니다.")
//		@Email(message = "이메일 형식이 올바르지 않습니다.")
//		private String email;
		
		private Role role;
	
		/* 암호화된 password */
		public void encryptPassword(String BCryptpassword) {
			this.password = BCryptpassword;
		}
		
		/* DTO -> Entity */
		public Users toEntity() {
			Users user = Users.builder()
						.id(id)
						.userName(userName)
						.password(password)
						.name(name)
//						.nickname(nickname)
//						.email(email)
						.role(role.USER)
						.build();
			return user;
		}
	}
	
	/* 회원 로그인 응답 ResponseUserDto 클래스 */
	@Getter
	@Setter
	public static class ResponseUserDto extends User{
		
		
		 // 유저의 정보를 더 추가하고 싶다면 이곳과, 아래의 생성자 파라미터를 조절해야 한다.
	    private String user_email;
	    private String name;

	    public ResponseUserDto(String username, String password
	            , boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked
	            , Collection authorities
	            , String user_name) {
	        super(username, password
	                , enabled, accountNonExpired, credentialsNonExpired, accountNonLocked
	                , authorities);
//	        this.user_email = user_email;
	        this.name = user_name;
	    }

	
	}
	
}