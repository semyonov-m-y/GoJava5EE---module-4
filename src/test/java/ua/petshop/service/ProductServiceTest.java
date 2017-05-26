package ua.petshop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.petshop.dao.ProductsDao;
import ua.petshop.model.Product;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    public static final long ID = 1L;
    @Mock
    private ProductsDao productsDao;
    @InjectMocks
    private ProductService productsService;

    @Test
    public void addProductShouldReturnProduct() throws Exception {
        Product product = new Product();
        when(productsDao.save(product)).thenReturn(product);
        assertEquals(product, productsService.addProduct(product));
        verify(productsDao, times(1)).save(product);
    }

    @Test
    public void getByIdShouldReturnProduct() throws Exception {
        Product product = new Product();
        product.setId(ID);
        when(productsDao.findOne(product.getId())).thenReturn(product);
        assertEquals(product, productsService.getById(product.getId()));
    }

    @Test
    public void deleteProductShouldInvokeDaoDelete() {
        productsService.deleteProduct(ID);
        verify(productsDao, times(1)).delete(ID);
    }

    @Test
    public void updateProductShouldReturnProduct() {
        Product product = new Product();
        when(productsDao.save(product)).thenReturn(product);
        assertEquals(product, productsService.updateProduct(product));
        verify(productsDao, times(1)).save(product);
    }
}
