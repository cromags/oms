package pl.bw.oms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bw.oms.domain.model.*;
import pl.bw.oms.domain.repository.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class OrderController {

    private final ClientRepository clientRepository;
    private final OrderMethodsRepository orderMethodsRepository;
    private final ProductRepository productRepository;
    private final TransportRepository transportRepository;
    private final OrderRepository orderRepository;
    private final DetailsRepository detailsRepository;


    public OrderController(ClientRepository clientRepository,
                           OrderMethodsRepository orderMethodsRepository,
                           ProductRepository productRepository,
                           TransportRepository transportRepository,
                           OrderRepository orderRepository,
                           DetailsRepository detailsRepository) {
        this.clientRepository = clientRepository;
        this.orderMethodsRepository = orderMethodsRepository;
        this.productRepository = productRepository;
        this.transportRepository = transportRepository;
        this.orderRepository = orderRepository;
        this.detailsRepository = detailsRepository;

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
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("methods", orderMethodsRepository.findAll());
        return "orders/add";
    }

    @RequestMapping(value = "/chooseProducts", method = RequestMethod.POST)
    public String prepareProductAddOrderPage(ClientOrder clientOrder,
                                             OrderDetails orderDetails,
                                             HttpServletRequest request,
                                             Model model) {

        HttpSession session = request.getSession();
        Client client = clientOrder.getClient();
        OrderMethod orderMethod = orderDetails.getOrderMethod();
        LocalDate dateOfOrderByClient = orderDetails.getDateOfOrderByClient();
        session.setAttribute("client", client);
        session.setAttribute("orderMethod", orderMethod);
        session.setAttribute("dateOfOrderByClient", dateOfOrderByClient);

        model.addAttribute("orderdetails", new OrderDetails());
        model.addAttribute("products", productRepository.findAll());

        return "orders/addProducts";
    }

    @RequestMapping(value = "/orderComments")
    public String prepareCommentsAddOrderPage(OrderDetails orderDetails,
                                              HttpServletRequest request,
                                              Model model) {

        HttpSession session = request.getSession();
        Product product = orderDetails.getProduct();
        session.setAttribute("product", product);
        session.setAttribute("quantity", orderDetails.getQuantity());

        model.addAttribute("clientorder", new ClientOrder());
        model.addAttribute("transports", transportRepository.findAll());

        return "orders/orderComments";
    }

    @RequestMapping(value = "/tkankYou")
    public String prepareThankYouOrderPage(HttpServletRequest request,
                                           ClientOrder clientOrder) {


        HttpSession session = request.getSession();
        Transport transport = clientOrder.getTransport();
        String orderComments = clientOrder.getComments();
        LocalDate dateOfOrderToTransport = clientOrder.getDateOfOrderToTransport();
        LocalDate dateOfSendToClient = clientOrder.getDateOfSendToClient();

        Client client = (Client) session.getAttribute("client");

        ClientOrder newOrder = new ClientOrder(dateOfOrderToTransport,
                dateOfSendToClient,
                orderComments,
                client,
                transport);

        orderRepository.save(newOrder);

        Product product = (Product) session.getAttribute("product");
        int quantity = (int) session.getAttribute("quantity");
        LocalDate dateOfOrderByClient = (LocalDate) session.getAttribute("dateOfOrderByClient");
        OrderMethod orderMethod = (OrderMethod) session.getAttribute("orderMethod");

        OrderDetails orderDetails = new OrderDetails(newOrder,
                product,
                quantity,
                dateOfOrderByClient,
                orderMethod);


        detailsRepository.save(orderDetails);

        return "redirect:/index";
    }

    // *** show all orders ***

    @RequestMapping(value = "/orders")
    public String prepareOrdersPage(Model model) {
        model.addAttribute("orders", orderRepository.findAll());

        return "orders/list";
    }

    // *** edit order ***

    @RequestMapping(value = "/editOrder/{id}")
    public String prepareEditClientPage(@PathVariable Long id, Model model) {
        //later I assumed that it's one-to-one relationship between order and details
        model.addAttribute("clientorder", orderRepository.findById(id));
        model.addAttribute("transports", transportRepository.findAll());
        return "orders/edit";

    }

    @RequestMapping(value = "/doEditOrder", method = RequestMethod.POST)
    public String processEditOrder(@Valid ClientOrder clientOrder, BindingResult bindingResult) {
        Optional<ClientOrder> clientOrderFrom = orderRepository.findById(clientOrder.getId());
        ClientOrder co = clientOrderFrom.orElse(null);
        if (bindingResult.hasErrors() || co == null) {
            return "orders/edit";
        }

        co.setDateOfOrderToTransport(clientOrder.getDateOfOrderToTransport());
        co.setTransport(clientOrder.getTransport());
        co.setDateOfSendToClient(clientOrder.getDateOfSendToClient());

        orderRepository.save(co);

        return "redirect:/index";
    }
}
