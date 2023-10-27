package com.tcs.web.repo;

import com.tcs.web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Long> {

}
