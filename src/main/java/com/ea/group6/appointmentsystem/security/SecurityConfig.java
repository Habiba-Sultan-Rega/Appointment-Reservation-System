package com.ea.group6.appointmentsystem.security;

import com.ea.group6.appointmentsystem.security.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    JWTAuthenticationProvider jwtAuthenticationProvider;
    AuthenticationFilter authenticationFilter;

    @Autowired
    public void setJwtAuthenticationProvider(JWTAuthenticationProvider jwtAuthenticationProvider) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    @Autowired
    public void setAuthenticationFilter(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors().and()
                .addFilterBefore(authenticationFilter, BasicAuthenticationFilter.class)
                .csrf()
                .disable()
                .httpBasic()
                .disable()
                .authorizeRequests()
                .mvcMatchers("/authenticate").permitAll()
                .mvcMatchers("/appointments/**").hasAnyAuthority("Provider", "Admin")
                .mvcMatchers(HttpMethod.PATCH,"/reservations/**").hasAnyAuthority("Provider", "Admin")
                .mvcMatchers(HttpMethod.GET,"/reservations/**").hasAnyAuthority("Client", "Admin")
                .mvcMatchers(HttpMethod.POST,"/reservations/**").hasAnyAuthority("Client", "Admin")
                .mvcMatchers(HttpMethod.DELETE,"/reservations/**").hasAnyAuthority("Client", "Admin")
                .mvcMatchers(HttpMethod.PUT,"/reservations/**").hasAnyAuthority("Client", "Admin")
                .mvcMatchers("/admin/**").hasAuthority("Admin")
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
//        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }
}

