package com.scm.scm20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

import com.scm.scm20.serviceImpementation.SecurityCustomUserDetailService;


@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Autowired
    private OAuthAuthenticationSuccessHandler handler;

    //configuration for authentication provider uses : password encoder to encode passeword enterred by login also done matching password by getting user from user detail servide and match password with entered password
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService); //user detail service ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); //password encorer ka object 
        return daoAuthenticationProvider; 
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(authorize->{
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        //agar form login se related kuch kaam huya toh yaha aayenge
        httpSecurity.formLogin(formLogin-> formLogin
            .loginPage("/login")
            .loginProcessingUrl("/authenticate")
            //.successForwardUrl("/user/dashboard")
            //formLogin.failureForwardUrl("/login?error=true");
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/user/dashboard",true)
            .permitAll()
        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.logout(logoutForm->{
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        //oauth configuration
        httpSecurity.oauth2Login(oauth -> oauth
        .loginPage("/login")
        .successHandler(handler)
        ); 

        return httpSecurity.build();
    }


    // used password encoder 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password hashing
    }
}
 