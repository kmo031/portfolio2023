package com.sangmin.portfolio.service;

import javax.servlet.http.HttpSession;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.sangmin.portfolio.config.CustomUserDetails;
import com.sangmin.portfolio.model.OAuthAttributes;
import com.sangmin.portfolio.model.SessionUser;
import com.sangmin.portfolio.model.Users;
import com.sangmin.portfolio.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
 
    private final UserRepository userRepository;
    private final HttpSession httpSession;
 
    public CustomOAuth2UserService(UserRepository userRepository, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;
    }
 
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        log.info("userRequest : "+userRequest); 
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
     
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
 
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        log.info("registrationId : "+registrationId );
        log.info("userNameAttributeName : "+userNameAttributeName );
        log.info("getAttributes : "+oAuth2User.getAttributes() );
        
        Users user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));
 
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
//                attributes.getAttributes(),
//                attributes.getNameAttributeKey()
//        );
        return CustomUserDetails.create(user,oAuth2User.getAttributes());
//        return null;
    }
 
    private Users saveOrUpdate(OAuthAttributes attributes) {
        Users user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), null)) 
                .orElse(attributes.toEntity());
 
        return userRepository.save(user);
    }
}
