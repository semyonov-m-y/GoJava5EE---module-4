package ua.petshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.petshop.dao.ProductsDao;
import ua.petshop.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {

    private ProductsDao productsDao;

    @Autowired
    public ProductService(ProductsDao productsDao) {
        this.productsDao = productsDao;
    }

    public Product addProduct(Product product) {
        return productsDao.save(product);
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        productsDao.findAll().forEach(products::add);
        return products;
    }

    public Product getById(long id) {
        return productsDao.findOne(id);
    }

    public void deleteProduct(long id) {
        productsDao.delete(id);
    }

    public Product updateProduct(Product product) {
        return productsDao.save(product);
    }
}
