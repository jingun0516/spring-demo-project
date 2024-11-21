package com.estsoft.springdemoproject.user.config;

import com.estsoft.springdemoproject.user.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class WebSecurityConfiguration {

    @Bean
    public WebSecurityCustomizer ignore() {
        return webSecurity -> webSecurity.ignoring()
                .requestMatchers("/static/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(  // 3) 인증, 인가 설정
                    custom -> custom.requestMatchers("/login", "/signup", "/user").permitAll()
                            .anyRequest().authenticated()
                )

                //4) 폼 기반 로그인 설정
                .formLogin(custom -> custom.loginPage("/login")
                        .defaultSuccessUrl("/articles", true))

                // 5) 로그아웃 설정
                .logout(custom -> custom.logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))

                // 6) csrf 비활성화
                .csrf(custom -> custom.disable())
                .build();
    }

    // 패스워드 암호화 방식 (BCryptPasswordEncoder) 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
