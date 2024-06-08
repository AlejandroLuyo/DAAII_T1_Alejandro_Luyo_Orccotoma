package pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.service;

import pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.model.bd.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> listarUsuario();
    Usuario buscarUsuarioXIdUsuario(Integer idusuario);
    Usuario buscarUsuarioXNomUsuario(String nomusuario);
    Usuario registrarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);

}
