package ar.utn.ba.ddsi.mailing.models.entities;

import ar.utn.ba.ddsi.mailing.models.dtoEmail.EmailDTO;
import ar.utn.ba.ddsi.mailing.services.email.EmailSenderAdapter;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Email {
    private Long id;
    private String destinatario;
    private String remitente;
    private String asunto;
    private String contenido;
    private boolean enviado;
    private final EmailSenderAdapter emailSenderAdapter;


    public Email(String destinatario, String remitente, String asunto, String contenido, EmailSenderAdapter emailSenderAdapter) {
        this.destinatario = destinatario;
        this.remitente = remitente;
        this.asunto = asunto;
        this.contenido = contenido;
        this.enviado = false;
        this.emailSenderAdapter = emailSenderAdapter;

    }

    public void enviar() {
        emailSenderAdapter.enviarEmail(new EmailDTO(destinatario, remitente, asunto, contenido));
        this.enviado = true;
    }
} 