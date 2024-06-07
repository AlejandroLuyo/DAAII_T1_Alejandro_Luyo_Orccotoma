package pe.cibertec.examen.DAAII_T1_Alejandro_Luyo_Orccotoma.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResultadoDto {
    private Boolean respuesta;
    private String mensaje;
}

