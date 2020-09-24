package pl.bw.oms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bw.oms.domain.model.ClientOrder;
import pl.bw.oms.domain.model.OrderDetails;
import pl.bw.oms.domain.repository.ClientRepository;
import pl.bw.oms.domain.repository.OrderMethodsRepository;
import pl.bw.oms.domain.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class OrderController {

    private final ClientRepository clientRepository;
    private final OrderMethodsRepository orderMethodsRepository;
    private final ProductRepository productRepository;

    public OrderController(ClientRepository clientRepository,
                           OrderMethodsRepository orderMethodsRepository,
                           ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.orderMethodsRepository = orderMethodsRepository;
        this.productRepository = productRepository;
    }

    // *** show orders homepage ***

    @RequestMapping(value = {"/orderHome", "/index/orderHome"})
    public String prepareOrderHomePage() {
        return "orderHome";
    }

    // *** add new order ***

    @RequestMapping(value = "/addOrder")
    public String prepareClientAddOrderPage(Model model) {
        model.addAttribute("clientorder", new ClientOrder());
        model.addAttribute("orderdetails", new OrderDetails());
        model.addAttribute("clients",clientRepository.findAll());
        model.addAttribute("methods",orderMethodsRepository.findAll());
        return "orders/add";
    }

    @RequestMapping(value = "/chooseProducts", method = RequestMethod.POST)
    public String prepareProductAddOrderPage(ClientOrder clientOrder,
                                             OrderDetails orderDetails,
                                             HttpServletRequest request,
                                             Model model) {

        HttpSession session = request.getSession();
        Long clientId = clientOrder.getClient().getId();
        Long orderMethodId = clientOrder.getClient().getId();
        session.setAttribute("clientId", clientId);
        session.setAttribute("orderMethodId", orderMethodId);
        LocalDate dateOfOrderByClient = orderDetails.getDateOfOrderByClient();
        session.setAttribute("dateOfOrderByClient", dateOfOrderByClient);

        model.addAttribute("clientorder", new ClientOrder());
        model.addAttribute("orderdetails", new OrderDetails());
        model.addAttribute("products",productRepository.findAll());



        //      clientRepository.save(client);
        return "orders/addProducts";
    }

    @RequestMapping(value = "/orderComments")
    public String prepareCommentsAddOrderPage(Model model,
                                              ClientOrder clientOrder,
                                              OrderDetails orderDetails,
                                              HttpServletRequest request) {

        //Teścik
        System.out.println(orderDetails.getProduct().getProductName() + " "+orderDetails.getProduct().getId());
        System.out.println(orderDetails.getQuantity());
        HttpSession session = request.getSession();
        System.out.println(">> " + (Long)session.getAttribute("clientId"));
        return "redirect:/index";
    }
}
