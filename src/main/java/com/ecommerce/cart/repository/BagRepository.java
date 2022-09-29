package com.ecommerce.cart.repository;

import com.ecommerce.cart.model.Bag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BagRepository extends JpaRepository<Bag, Long> {
}
