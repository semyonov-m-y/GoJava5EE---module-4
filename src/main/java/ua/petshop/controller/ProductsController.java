package ua.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ua.petshop.model.Product;
import ua.petshop.service.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addProduct(@ModelAttribute("product") @Valid Product product, Errors errors) {
        if (!errors.hasErrors()) {
            productService.addProduct(product);
        }
        return new ModelAndView("redirect:/products/all");
    }

    @RequestMapping(value = "/{id}/delete")
    public ModelAndView deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return new ModelAndView("redirect:/products/all");
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public ModelAndView updateProduct(@ModelAttribute("product") Product product, @PathVariable long id) {
        productService.updateProduct(product);
        return new ModelAndView("redirect:/products/" + id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getProduct(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product" , productService.getById(id));
        modelAndView.setViewName("productInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productsList", productService.getAll());
        modelAndView.setViewName("products");
        return modelAndView;
    }
}
