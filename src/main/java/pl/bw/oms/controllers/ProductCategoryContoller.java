package pl.bw.oms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bw.oms.domain.model.Client;
import pl.bw.oms.domain.model.ProductCategory;
import pl.bw.oms.domain.repository.CategoryRepository;
import pl.bw.oms.domain.repository.ClientRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ProductCategoryContoller {

    private final CategoryRepository categoryRepository;
    private final ClientRepository clientRepository;

    public ProductCategoryContoller(CategoryRepository categoryRepository, ClientRepository clientRepository) {
        this.categoryRepository = categoryRepository;
        this.clientRepository = clientRepository;
    }


    // *** show productCategory homepage ***

    @RequestMapping(value = {"/productCategorytHome", "/index/productCategorytHome"})
    public String prepareProductCategoryHomePage() {
        return "productCategoryHome";
    }

    // *** show all categories ***

    @RequestMapping(value = "/categories")
    public String prepareCategoriesPage(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories/list";
    }


    //something wrong here vvvvvvvvvvvvvvvvvvvv

    @RequestMapping(value = "/addCategory")
    public String prepareAddClientPage(Model model) {
        model.addAttribute("cat", new ProductCategory());
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

    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

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
