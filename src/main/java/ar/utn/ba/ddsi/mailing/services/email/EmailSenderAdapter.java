package ar.utn.ba.ddsi.mailing.services.email;
import ar.utn.ba.ddsi.mailing.models.dtoEmail.EmailDTO;

public interface EmailSenderAdapter {
    void enviarEmail(EmailDTO emailDTO);
}
