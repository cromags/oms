package pl.bw.oms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bw.oms.domain.model.Client;
import pl.bw.oms.domain.repository.ClientRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // *** show all clients ***

    @RequestMapping("/clients")
    public String prepareClientsPage(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "clients/list";
    }

    // *** add client ***

    @RequestMapping(value = "/addClient")
    public String prepareAddClientPage(Model model) {
        model.addAttribute("client", new Client());
        return "clients/add";
    }

    @RequestMapping(value = "/doAdd", method = RequestMethod.POST)
    public String processAddClient(@Valid Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "clients/add";
        }
        clientRepository.save(client);
        return "redirect:/index";
    }

    // *** edit client ***

    @RequestMapping(value = "/editClient/{id}")
    public String prepareEditClientPage(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientRepository.findById(id));
        return "clients/edit";

    }

    @RequestMapping(value = "/doEdit", method = RequestMethod.POST)
    public String processEditClient(@Valid Client client, BindingResult bindingResult) {
        Optional<Client> clientFrom = clientRepository.findById(client.getId());
        Client c = clientFrom.orElse(null);
        if (bindingResult.hasErrors() || c == null) {
            return "clients/edit";
        }
        c.setFirstName(client.getFirstName());
        c.setLastName(client.getLastName());
        c.setZipCode(client.getZipCode());
        c.setCity(client.getCity());
        c.setAddress(client.getAddress());
        c.setTel(client.getTel());
        c.setEmail(client.getEmail());
        c.setComments(client.getComments());
        clientRepository.save(c);
        return "redirect:/index";
    }

    // *** delete client ***

    @RequestMapping(value = "/deleteClient/{id}")
    public String processDeleteClient(@PathVariable Long id, Model model) {

        Optional<Client> clientFrom = clientRepository.findById(id);
        Client c = clientFrom.orElse(null);
        if (c == null) {
            return "info/notfound";
        }
        clientRepository.delete(c);
        return "redirect:/clients";
    }
}
