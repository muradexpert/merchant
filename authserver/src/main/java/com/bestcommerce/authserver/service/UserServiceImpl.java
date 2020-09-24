package com.bestcommerce.authserver.service;

import com.bestcommerce.authserver.entity.User;
import com.bestcommerce.authserver.repository.UserRepository;
import com.bestcommerce.authserver.security.SecurityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;

   public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
      this.userRepository = userRepository;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }

   @Override
   public User save(User user) {
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

      return userRepository.save(user);
   }

   @Transactional(readOnly = true)
   public Optional<User> getUserWithAuthorities() {
      return SecurityUtils.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
   }

}
