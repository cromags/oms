package pl.bw.oms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bw.oms.domain.model.Client;
import pl.bw.oms.domain.model.Transport;
import pl.bw.oms.domain.repository.TransportRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class TransportController {

    private final TransportRepository transportRepository;

    public TransportController(TransportRepository transportRepository){
        this.transportRepository = transportRepository;
    }

    // *** show transport homepage ***

    @RequestMapping(value = {"/transportHome", "/index/transportHome"})
    public String prepareTransportHomePage() {
        return "transportHome";
    }

    // *** show all transports ***

    @RequestMapping(value = "/transports")
    public String prepareClientsPage(Model model) {
        model.addAttribute("transports", transportRepository.findAll());
        return "transport/list";
    }

    // *** add transport ***

    @RequestMapping(value = "/addTransport")
    public String prepareAddTransportPage(Model model) {
        model.addAttribute("transport", new Transport());
        return "transport/add";
    }

    @RequestMapping(value = "/doAddTransport", method = RequestMethod.POST)
    public String processAddTransport(@Valid Transport transport, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "transport/add";
        }
        transportRepository.save(transport);
        return "redirect:/index";
    }

    // *** edit transport ***

    @RequestMapping(value = "/editTransport/{id}")
    public String prepareEditTransportPage(@PathVariable Long id, Model model) {
        model.addAttribute("transport", transportRepository.findById(id));
        return "transport/edit";

    }

    @RequestMapping(value = "/doEditTransport", method = RequestMethod.POST)
    public String processEditTransport(@Valid Transport transport, BindingResult bindingResult) {
        Optional<Transport> transportFrom = transportRepository.findById(transport.getId());
        Transport t = transportFrom.orElse(null);
        if(bindingResult.hasErrors() || t == null) {
            return "transport/edit";
        }
        t.setName(transport.getName());
        t.setZipCode(transport.getZipCode());
        t.setCity(transport.getZipCode());
        t.setAddress(transport.getCity());
        t.setTel(transport.getTel());
        t.setEmail(transport.getEmail());
        t.setWww(transport.getWww());
        t.setComments(transport.getComments());
        transportRepository.save(t);
        return "redirect:/index";
    }

    // *** delete transport ***

    @RequestMapping(value = "/deleteTransport/{id}")
    public String processDeleteTransport(@PathVariable Long id, Model model) {

        Optional<Transport> transportFrom = transportRepository.findById(id);
        Transport t = transportFrom.orElse(null);
        if (t == null) {
            return "info/notfound";
        }
        transportRepository.delete(t);
        return "redirect:/transports";
    }
}
