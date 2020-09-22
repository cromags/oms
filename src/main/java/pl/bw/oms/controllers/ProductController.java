package pl.bw.oms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bw.oms.domain.model.Client;
import pl.bw.oms.domain.model.Product;
import pl.bw.oms.domain.model.ProductCategory;
import pl.bw.oms.domain.model.Supplier;
import pl.bw.oms.domain.repository.CategoryRepository;
import pl.bw.oms.domain.repository.ProductRepository;
import pl.bw.oms.domain.repository.SupplierRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository,
                             SupplierRepository supplierRepository,
                             CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }

    // *** show product homepage ***

    @RequestMapping(value = {"/productHome", "/index/productHome"})
    public String prepareProductHomePage() {
        return "productHome";
    }

    // *** show all products ***

    @RequestMapping(value = "/products")
    public String prepareProductsPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products/list";
    }

    // *** add product ***

    @RequestMapping(value = "/addProduct")
    public String prepareAddProductPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("suppliers", supplierRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());

        return "products/add";
    }

    @RequestMapping(value = "/doAddProduct", method = RequestMethod.POST)
    public String processAddProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "products/add";
        }
        productRepository.save(product);
        return "redirect:/index";
    }

    // *** edit product ***

    @RequestMapping(value = "/editProduct/{id}")
    public String prepareEditProductPage(@PathVariable Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        model.addAttribute("suppliers", supplierRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "products/edit";

    }

    @RequestMapping(value = "/doEditProduct", method = RequestMethod.POST)
    public String processEditClient(@Valid Product product, BindingResult bindingResult) {
        System.out.println(">>> " + product.getId());
        Optional<Product> productFrom = productRepository.findById(product.getId());
        Product p = productFrom.orElse(null);
        if (bindingResult.hasErrors() || p == null) {
            return "products/edit";
        }
        p.setProductName(product.getProductName());
        p.setSupplierPrice(product.getSupplierPrice());
        p.setMyPrice(product.getMyPrice());
        p.setSupplier(product.getSupplier());
        p.setProductCategory(product.getProductCategory());
        p.setDescription(product.getDescription());
        productRepository.save(p);
        return "redirect:/index";
    }

    // *** delete product ***

    @RequestMapping(value = "/deleteProduct/{id}")
    public String processDeleteProduct(@PathVariable Long id, Model model) {

        Optional<Product> productFrom = productRepository.findById(id);
        Product p = productFrom.orElse(null);
        if (p == null) {
            return "info/notfound";
        }
        productRepository.delete(p);
        return "redirect:/products";
    }


}
