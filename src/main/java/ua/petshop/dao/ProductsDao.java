package ua.petshop.dao;

import org.springframework.data.repository.CrudRepository;
import ua.petshop.model.Product;

public interface ProductsDao extends CrudRepository<Product, Long> {

}
