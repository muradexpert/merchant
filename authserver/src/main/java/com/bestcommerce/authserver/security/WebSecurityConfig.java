package com.bestcommerce.authserver.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   private final JwtTokenProvider jwtTokenProvider;
   private final CorsFilter corsFilter;
   private final JwtAuthenticationEntryPoint authenticationErrorHandler;
   private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

   public WebSecurityConfig(
           JwtTokenProvider jwtTokenProvider,
           CorsFilter corsFilter,
           JwtAuthenticationEntryPoint authenticationErrorHandler,
           JwtAccessDeniedHandler jwtAccessDeniedHandler
   ) {
      this.jwtTokenProvider = jwtTokenProvider;
      this.corsFilter = corsFilter;
      this.authenticationErrorHandler = authenticationErrorHandler;
      this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
   }


   @Override
   protected void configure(HttpSecurity httpSecurity) throws Exception {
      httpSecurity
              .cors().and().csrf().disable()
              .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
              .exceptionHandling()
              .authenticationEntryPoint(authenticationErrorHandler)
              .accessDeniedHandler(jwtAccessDeniedHandler)
              .and()
              .headers()
              .frameOptions()
              .sameOrigin()
              .and()
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              .authorizeRequests()
              .antMatchers("/api/authenticate").permitAll()
              .antMatchers("/api/register").permitAll()
              .anyRequest().authenticated()
              .and()
              .apply(securityConfigurerAdapter());
   }

   private JWTConfigurer securityConfigurerAdapter() {
      return new JWTConfigurer(jwtTokenProvider);
   }
}
