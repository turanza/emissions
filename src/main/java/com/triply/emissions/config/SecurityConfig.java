package com.triply.emissions.config;

import com.triply.emissions.security.JwtUserDetailsClass;
import com.triply.emissions.security.jwt.JwtConfigurer;
import com.triply.emissions.security.jwt.JwtTokenFilter;
import com.triply.emissions.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig{

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtUserDetailsClass jwtUserDetailsClass;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider, JwtUserDetailsClass jwtUserDetailsClass) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtUserDetailsClass = jwtUserDetailsClass;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(jwtUserDetailsClass);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .httpBasic(Customizer.withDefaults())
                .csrf().disable()
                .sessionManagement(Customizer.withDefaults())
                .formLogin(form->form
                        .loginPage("/login")
                        .permitAll())
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .apply(new JwtConfigurer(jwtTokenProvider));
        return http.build();
    }
}
