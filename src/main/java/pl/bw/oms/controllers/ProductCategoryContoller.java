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
import pl.bw.oms.domain.repository.ClientRepository;
import pl.bw.oms.domain.repository.ProductRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductCategoryContoller {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductCategoryContoller(CategoryRepository categoryRepository,
                                    ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }


    // *** show productCategory homepage ***

    @RequestMapping(value = {"/productCategoryHome", "/index/productCategoryHome"})
    public String prepareProductCategoryHomePage() {
        return "productCategoryHome";
    }

    // *** show all categories ***

    @RequestMapping(value = "/categories")
    public String prepareCategoriesPage(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories/list";
    }

    // *** add category ***

    @RequestMapping(value = "/addCategory")
    public String prepareAddCategoryPage(Model model) {
        model.addAttribute("category", new ProductCategory());
        return "categories/add";
    }

    @RequestMapping(value = "/doAddCategory", method = RequestMethod.POST)
    public String processAddCategory(@Valid ProductCategory cat, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //'method' to solve problem with validation of this data
            return "redirect:/addCategory";

        }
        categoryRepository.save(cat);
        return "redirect:/index";
    }


    // *** edit category ***

    @RequestMapping(value = "/editCategory/{id}")
    public String prepareEditCategoryrPage(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryRepository.findById(id));
        return "categories/edit";

    }

    @RequestMapping(value = "/doEditCategory", method = RequestMethod.POST)
    public String processEditCategory(@Valid ProductCategory productCategory, BindingResult bindingResult) {
        Optional<ProductCategory> productCategoryFrom = categoryRepository.findById(productCategory.getId());
        ProductCategory pc = productCategoryFrom.orElse(null);
        if (bindingResult.hasErrors() || pc == null) {
            //'method' to solve problem with validation of this data
            return "info/categoryvalidation";
        }
        pc.setCategoryName(productCategory.getCategoryName());
        pc.setDescription(productCategory.getDescription());

        categoryRepository.save(pc);
        return "redirect:/index";
    }

    // *** delete category ***

    @RequestMapping(value = "/deleteCategory/{id}")
    public String processDeleteCategory(@PathVariable Long id, Model model) {

        Optional<ProductCategory> categoryFrom = categoryRepository.findById(id);
        ProductCategory pc = categoryFrom.orElse(null);
        if (pc == null) {
            return "info/notfound";
        }

        List<Product> products =  productRepository.findAll();
        for (int i = 0; i < products.size(); i++){
            if(products.get(i).getProductCategory().getId() == pc.getId()){
                return "info/cannotdelete";
            }
        }

        categoryRepository.delete(pc);
        return "redirect:/categories";
    }

}
