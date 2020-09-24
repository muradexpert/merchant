package com.bestcommerce.authserver.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "AUTHORITY")
public class Authority {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;
   @Column(name = "NAME", length = 50)
   @NotNull
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Authority authority = (Authority) o;
      return name == authority.name;
   }

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }

   @Override
   public String toString() {
      return "Authority{" +
              "name=" + name +
              '}';
   }
}
