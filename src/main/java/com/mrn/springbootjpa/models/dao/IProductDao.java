package com.mrn.springbootjpa.models.dao;

import com.mrn.springbootjpa.models.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductDao extends CrudRepository<Product, Long> {

    @Query("select p from Product p where p.productName LIKE %?1%") // ?1 represents the param we enter (term)
    List<Product> findByProductName(String term);


    List<Product> findByProductNameLikeIgnoreCase(String term);
}
