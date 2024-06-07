package pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.model.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private Integer idusuario;
    private String nomusuario;
    private String nombres;
    private String apellidos;
    private Boolean activo;
    private String email;
}
