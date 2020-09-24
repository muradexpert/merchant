package com.bestcommerce.authserver.dto;

import com.bestcommerce.authserver.entity.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserDto {

    private String username;

    private String ownerName;

    private String email;

    private String type;

    private Set<Authority> authorities = new HashSet<>();
}
