package pl.bw.oms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bw.oms.domain.model.Client;
import pl.bw.oms.domain.model.OrderMethod;
import pl.bw.oms.domain.repository.OrderMethodsRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class OrderMethodController {

    private final OrderMethodsRepository orderMethodsRepository;

    public OrderMethodController(OrderMethodsRepository orderMethodsRepository) {
        this.orderMethodsRepository = orderMethodsRepository;
    }

    // *** show order_methods homepage ***

    @RequestMapping(value = {"/orderMethodsHome", "/index/orderMethodsHome"})
    public String prepareOrderMethodsHomePage() {
        return "orderMethodsHome";
    }

    // *** show all order methods ***

    @RequestMapping(value = "/orderMethods")
    public String prepareOrderMethodsPage(Model model) {
        model.addAttribute("orderMethods", orderMethodsRepository.findAll());
        return "orderMethods/list";
    }

    // *** add new order method ***

    @RequestMapping(value = "/addOrderMethod")
    public String prepareAddOrderMethodPage(Model model) {
        model.addAttribute("ordermethod", new OrderMethod());
        return "orderMethods/add";
    }

    @RequestMapping(value = "/doAddOrderMethod", method = RequestMethod.POST)
    public String processAddOrderMethod(@Valid OrderMethod orderMethod, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //'method' to solve problem with validation of this data
            return "redirect:/addOrderMethod";
        }
        orderMethodsRepository.save(orderMethod);
        return "redirect:/index";
    }

    // *** delete orderMethod ***

    @RequestMapping(value = "/deleteOrderMethod/{id}")
    public String processDeleteOrderMethod(@PathVariable Long id, Model model) {

        Optional<OrderMethod> orderMethodFrom = orderMethodsRepository.findById(id);
        OrderMethod om = orderMethodFrom.orElse(null);
        if (om == null) {
            return "info/notfound";
        }
        orderMethodsRepository.delete(om);
        return "redirect:/orderMethods";
    }
}
