package com.scm.scm20.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.scm20.entities.User;
import com.scm.scm20.entities.providers;
import com.scm.scm20.helpers.AppConstants;
import com.scm.scm20.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                
                //data save karna h hume
                DefaultOAuth2User user=(DefaultOAuth2User)authentication.getPrincipal();

                String email=user.getAttribute("email").toString();
                String name=user.getAttribute("name").toString();
                String picture=user.getAttribute("picture").toString();

                User user1=new User();

                user1.setEmail(email);
                user1.setName(name);
                user1.setProfilePic(picture);
                user1.setPassword("password");
                user1.setUserId(UUID.randomUUID().toString());
                user1.setProvider(providers.GOOGLE);
                user1.setEnabled(true);
                user1.setEmailVerified(true);
                user1.setProviderUserId(user.getName());
                user1.setRoleList(List.of(AppConstants.ROLE_USER));
                user1.setAbout("this is user account");

                User user2=userRepo.findByEmail(email).orElse(null);// this is checking whether user is allready present in database

                if(user2==null){
                    userRepo.save(user1);
                }

                //redirect se pehle hum data save karenge
                new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}
