package pl.bw.oms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bw.oms.domain.model.Client;
import pl.bw.oms.domain.model.ProductCategory;
import pl.bw.oms.domain.model.Supplier;
import pl.bw.oms.domain.repository.CategoryRepository;
import pl.bw.oms.domain.repository.ClientRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ProductCategoryContoller {

    private final CategoryRepository categoryRepository;

    public ProductCategoryContoller(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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


    //VVVVVVVVVVVVVVVVVVVVVVVVVVVVVV

    // *** add category ***

    @RequestMapping(value = "/addCategory")
    public String prepareAddCategoryPage(Model model) {
        model.addAttribute("category", new ProductCategory());
        return "categories/add";
    }

    @RequestMapping(value = "/doAddCategory", method = RequestMethod.POST)
    public String processAddClient(@Valid ProductCategory cat, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "categories/add";
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
    public String processEditSupplier(@Valid ProductCategory productCategory, BindingResult bindingResult) {
        Optional<ProductCategory> productCategoryFrom = categoryRepository.findById(productCategory.getId());
        ProductCategory pc = productCategoryFrom.orElse(null);
        if (bindingResult.hasErrors() || pc == null) {
            return "categories/edit";
        }
        pc.setCategoryName(productCategory.getCategoryName());
        pc.setDescription(productCategory.getDescription());

        categoryRepository.save(pc);
        return "redirect:/index";
    }
    /// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


    // *** delete category ***

    @RequestMapping(value = "/deleteCategory/{id}")
    public String processDeleteCategory(@PathVariable Long id, Model model) {

        Optional<ProductCategory> categoryFrom = categoryRepository.findById(id);
        ProductCategory pc = categoryFrom.orElse(null);
        if (pc == null) {
            return "info/notfound";
        }
        categoryRepository.delete(pc);
        return "redirect:/categories";
    }

}
