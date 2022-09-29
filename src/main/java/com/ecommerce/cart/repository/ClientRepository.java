package com.ecommerce.cart.repository;

import com.ecommerce.cart.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
