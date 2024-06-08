package pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(){

        return "home";
    }
}
