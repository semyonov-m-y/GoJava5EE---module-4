package ua.petshop.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Errors;
import ua.petshop.model.Product;
import ua.petshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ProductsControllerTest {

    public static final String REDIRECT_PRODUCTS = "redirect:/products/all";
    @Mock
    private ProductService service;
    @Mock
    private Errors errors;
    @InjectMocks
    private ProductsController controller;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = standaloneSetup(controller).build();
    }

    @Test
    public void addProduct() throws Exception {
        Product product = new Product();
        when(errors.hasErrors()).thenReturn(false);
        assertEquals(REDIRECT_PRODUCTS, controller.addProduct(product, errors).getViewName());
        verify(service, times(1)).addProduct(product);
    }

    @Test
    public void deleteProduct() throws Exception {
        mvc.perform(get("/products/1/delete")).andExpect(view().name(REDIRECT_PRODUCTS));
        verify(service, times(1)).deleteProduct(1L);
    }

    @Test
    public void updateProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        mvc.perform(post("/products/1/update").requestAttr("product", product)).andExpect(view().name
            ("redirect:/products/1"));
        verify(service, times(1)).updateProduct(product);
    }

    @Test
    public void getProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        when(service.getById(1L)).thenReturn(product);
        mvc.perform(get("/products/1")).andExpect(view().name("productInfo")).andExpect(model().attribute("product",
            product));
    }

    @Test
    public void getAll() throws Exception {
        List<Product> list = new ArrayList<>();
        when(service.getAll()).thenReturn(list);
        mvc.perform(get("/products/all")).andExpect(view().name("products")).andExpect(model().attribute("productsList",
            list));
    }
}
