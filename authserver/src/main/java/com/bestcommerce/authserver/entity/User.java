package com.bestcommerce.authserver.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "USER")
public class User {

   @JsonIgnore
   @Id
   @Column(name = "ID")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "USERNAME", length = 50, unique = true)
   @NotNull
   @Size(min = 4, max = 50)
   private String username;


   @Column(name = "PASSWORD", length = 100)
   @NotNull
   @Size(min = 4, max = 100)
   private String password;

   @Column(name = "OWNERNAME", length = 50)
   @Size(min = 4, max = 50)
   private String ownerName;


   @Column(name = "EMAIL", length = 50)
   @Size(min = 4, max = 50)
   @Email
   private String email;

   @Column(name = "TYPE", length = 50)
   private String type;

   @JsonIgnore
   @Column(name = "ACTIVATED")
   private boolean activated=true;

   @ManyToMany
   @JoinTable(
           name = "USER_AUTHORITY",
           joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
           inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
   private Set<Authority> authorities = new HashSet<>();
}