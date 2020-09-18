package pl.bw.oms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bw.oms.domain.model.Supplier;
import pl.bw.oms.domain.model.Transport;
import pl.bw.oms.domain.repository.SupplierRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class SupplierController {

    private final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    // *** show supplier homepage ***

    @RequestMapping(value = {"/supplierHome", "/index/supplierHome"})
    public String prepareSupplierHomePage() {
        return "supplierHome";
    }

    // *** show all suppliers ***

    @RequestMapping(value = "/suppliers")
    public String prepareSuppliersPage(Model model) {
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "suppliers/list";
    }

    // *** add supplier ***

    @RequestMapping(value = "/addSupplier")
    public String prepareAddSupplierPage(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "suppliers/add";
    }

    @RequestMapping(value = "/doAddSupplier", method = RequestMethod.POST)
    public String processAddSupplier(@Valid Supplier supplier, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "suppliers/add";
        }
        supplierRepository.save(supplier);
        return "redirect:/index";
    }

    // *** edit supplier ***

    @RequestMapping(value = "/editSupplier/{id}")
    public String prepareEditSupplierPage(@PathVariable Long id, Model model) {
        model.addAttribute("supplier", supplierRepository.findById(id));
        return "suppliers/edit";

    }

    @RequestMapping(value = "/doEditSupplier", method = RequestMethod.POST)
    public String processEditSupplier(@Valid Supplier supplier, BindingResult bindingResult) {
        Optional<Supplier> supplierFrom = supplierRepository.findById(supplier.getId());
        Supplier s = supplierFrom.orElse(null);
        if (bindingResult.hasErrors() || s == null) {
            return "suppliers/edit";
        }
        s.setName(supplier.getName());
        s.setZipCode(supplier.getZipCode());
        s.setCity(supplier.getZipCode());
        s.setAddress(supplier.getCity());
        s.setTel(supplier.getTel());
        s.setEmail(supplier.getEmail());
        s.setWww(supplier.getWww());
        s.setComments(supplier.getComments());
        supplierRepository.save(s);
        return "redirect:/index";
    }

    // *** delete supplier ***

    @RequestMapping(value = "/deleteSupplier/{id}")
    public String processDeleteSupplier(@PathVariable Long id, Model model) {

        Optional<Supplier> supplierFrom = supplierRepository.findById(id);
        Supplier s = supplierFrom.orElse(null);
        if (s == null) {
            return "info/notfound";
        }
        supplierRepository.delete(s);
        return "redirect:/suppliers";
    }

}
