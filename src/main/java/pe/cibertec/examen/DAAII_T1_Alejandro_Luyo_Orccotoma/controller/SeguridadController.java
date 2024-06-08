package pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.model.bd.Usuario;
import pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.service.UsuarioService;

@AllArgsConstructor
@Controller
@RequestMapping("/seguridad")
public class SeguridadController {
    private UsuarioService usuarioService;

    @GetMapping("/cambiarpw")
    public String cambiarPassword(Model model) {
        return "seguridad/cambiarpw";
    }

    @PostMapping("/cambiarpw")
    public String cambiarPassword(
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmNewPassword") String confirmNewPassword,
            RedirectAttributes redirectAttributes) {

        if (!validarPassword(newPassword)) {
            redirectAttributes.addFlashAttribute("error", "La contraseña debe ser de mínimo 8 caracteres, al menos una letra mayúscula, una letra minúscula, un número y un carácter especial");
            return "redirect:/seguridad/cambiarpw";
        }

        if (!newPassword.equals(confirmNewPassword)) {
            redirectAttributes.addFlashAttribute("error", "Las contraseñas no coinciden");
            return "redirect:/seguridad/cambiarpw";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombre = auth.getName();
        Usuario usuario = usuarioService.buscarUsuarioXNomUsuario(nombre);
        usuario.setPassword(encodedPassword);
        usuarioService.actualizarUsuario(usuario);

        redirectAttributes.addFlashAttribute("success", "Contraseña actualizada correctamente");
        return "redirect:/seguridad/cambiar-password";
    }

    private boolean validarPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\da-zA-Z]).{8,}$";
        return password.matches(passwordRegex);
    }
}

