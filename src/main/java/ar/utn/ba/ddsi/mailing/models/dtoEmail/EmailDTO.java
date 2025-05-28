package ar.utn.ba.ddsi.mailing.models.dtoEmail;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class EmailDTO {
    private String destinatario;
    private String remitente;
    private String asunto;
    private String mensaje;
}
