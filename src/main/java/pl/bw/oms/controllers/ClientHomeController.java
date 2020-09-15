package pl.bw.oms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientHomeController {
    @RequestMapping({"/clientHome", "/index/clientHome"})
    public String prepareClientHomePage(){
        return "clientHome";
    }
}
