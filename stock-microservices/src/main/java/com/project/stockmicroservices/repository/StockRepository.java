package com.project.stockmicroservices.repository;
import com.project.stockmicroservices.entity.Product;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface StockRepository extends CrudRepository<Product,Long> {

    List<Product> findAll();

    Optional<Product> findById(Long product_id);
}
