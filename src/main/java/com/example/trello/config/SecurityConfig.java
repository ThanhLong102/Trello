package com.example.trello.config;

import com.example.trello.security.jwt.JWTConfigurer;
import com.example.trello.security.jwt.TokenProvider;
import com.example.trello.security.oauth.CustomOAuth2UserService;
import com.example.trello.security.oauth.OAuth2LoginFailureHandler;
import com.example.trello.security.oauth.OAuth2LoginSuccessHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@Configuration
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final TokenProvider tokenProvider;

    private final SimpleCORSFilter simpleCORSFilter;

    private final CustomOAuth2UserService customOAuth2UserService;

    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;

    public SecurityConfig(TokenProvider tokenProvider,
                          SimpleCORSFilter simpleCORSFilter, CustomOAuth2UserService customOAuth2UserService, OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler, OAuth2LoginFailureHandler oAuth2LoginFailureHandler) {
        this.tokenProvider = tokenProvider;
        this.simpleCORSFilter = simpleCORSFilter;
        this.customOAuth2UserService = customOAuth2UserService;
        this.oAuth2LoginSuccessHandler = oAuth2LoginSuccessHandler;
        this.oAuth2LoginFailureHandler = oAuth2LoginFailureHandler;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .addFilterBefore(simpleCORSFilter, SessionManagementFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-resources/**", "/v2/api-docs").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/", "/login", "/oauth/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/**").authenticated()
//                .and()
//                .oauth2Login()
//                .userInfoEndpoint().userService(customOAuth2UserService)
//                .and()
//                .successHandler(oAuth2LoginSuccessHandler)
//                .failureHandler(oAuth2LoginFailureHandler)
                .and()
                .httpBasic()
                .and()
                .apply(securityConfigurerAdapter())
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
        // @formatter:on
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }

}
