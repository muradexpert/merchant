package com.bestcommerce.authserver.repository;


import com.bestcommerce.authserver.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
