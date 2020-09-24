package com.bestcommerce.product.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String name;
    private String description;
    private Integer unit;
    private Double price;
    private Integer inventory;

    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id",scope = Category.class)
    @JsonIdentityReference(alwaysAsId=true)
    @ManyToMany
    @JoinTable(name ="product_category",joinColumns =@JoinColumn(name = "product_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"))
    private List<Category> categories;

    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id",scope = Payment.class)
    @JsonIdentityReference(alwaysAsId=true)
    @ManyToMany
    @JoinTable(name = "product_payment",joinColumns = {@JoinColumn(name = "product_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "payment_id",referencedColumnName = "id")})
    private List<Payment> payments;

}
