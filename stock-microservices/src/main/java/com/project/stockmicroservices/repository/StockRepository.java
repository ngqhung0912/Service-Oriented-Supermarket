package com.project.stockmicroservices.repository;
import com.project.stockmicroservices.entity.Product;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface StockRepository extends CrudRepository<Product,Long> {
    List<Product> findAll();
}
