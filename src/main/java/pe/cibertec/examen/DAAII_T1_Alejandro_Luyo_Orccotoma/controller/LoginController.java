package pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.model.bd.Usuario;
import pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.service.UsuarioService;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private  UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(){
        return "auth/formlogin";
    }

    @GetMapping("/login-success")
    public String loginSuccess(){
        return "redirect:/auth/dashboard";
    }

    @GetMapping("/registro")
    public String registro(Model model){
        model.addAttribute("usuario", new Usuario());
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String registroUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
        return "auth/registro";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomusuario = auth.getName();
        Usuario usuario = usuarioService.buscarUsuarioXNomUsuario(nomusuario);
        model.addAttribute("nomusuario", usuario.getNomusuario());
        return "layout";
    }

}