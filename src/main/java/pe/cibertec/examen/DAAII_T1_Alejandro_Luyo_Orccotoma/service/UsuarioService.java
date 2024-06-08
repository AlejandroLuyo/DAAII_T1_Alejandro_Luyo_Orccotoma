package pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.model.bd.Rol;
import pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.model.bd.Usuario;
import pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.repository.RolRepository;
import pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.repository.UsuarioRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements IUsuarioService {

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }
    @Override
    public Usuario buscarUsuarioXIdUsuario(Integer idusuario) {
        return usuarioRepository.findById(idusuario).orElse(null);
    }
    @Override
    public Usuario buscarUsuarioXNomUsuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.actualizarUsuario(
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getActivo(),
                usuario.getIdusuario()
        );
    }

}