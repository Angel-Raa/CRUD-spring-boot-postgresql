package org.caja.ideal.shoppingcart.repository;

import org.caja.ideal.shoppingcart.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findByName(String name);
}
