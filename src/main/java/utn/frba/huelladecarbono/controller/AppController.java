package utn.frba.huelladecarbono.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping({"/recomendaciones"})
    public String recomendaciones() {return "recomendaciones.html";}

    @GetMapping({"/login"})
    public String login() {return "login.html";}


}
