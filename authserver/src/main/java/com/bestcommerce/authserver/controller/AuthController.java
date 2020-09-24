package com.bestcommerce.authserver.controller;

import com.bestcommerce.authserver.dto.LoginDto;
import com.bestcommerce.authserver.entity.User;
import com.bestcommerce.authserver.security.JWTFilter;
import com.bestcommerce.authserver.security.JwtTokenProvider;
import com.bestcommerce.authserver.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

   private final JwtTokenProvider jwtTokenProvider;
   private final UserService userService;
   private final AuthenticationManagerBuilder authenticationManagerBuilder;

   public AuthController(JwtTokenProvider jwtTokenProvider, UserService userService, AuthenticationManagerBuilder authenticationManagerBuilder) {
      this.jwtTokenProvider = jwtTokenProvider;
      this.userService = userService;
      this.authenticationManagerBuilder = authenticationManagerBuilder;
   }


   @PostMapping("/register")
   public ResponseEntity<?> createUser(@RequestBody User user){
      return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
   }

   @PostMapping("/authenticate")
   public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginDto loginDto) {

      UsernamePasswordAuthenticationToken authenticationToken =
              new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

      Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);

      boolean rememberMe = (loginDto.getRememberMe() == null) ? false : loginDto.getRememberMe();
      String jwt = jwtTokenProvider.createToken(authentication, rememberMe);

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

      return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
   }


   static class JWTToken {

      private String idToken;

      JWTToken(String idToken) {
         this.idToken = idToken;
      }

      @JsonProperty("id_token")
      String getIdToken() {
         return idToken;
      }

      void setIdToken(String idToken) {
         this.idToken = idToken;
      }
   }
}