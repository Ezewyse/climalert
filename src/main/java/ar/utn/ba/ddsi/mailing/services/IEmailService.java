package ar.utn.ba.ddsi.mailing.services;

import ar.utn.ba.ddsi.mailing.models.dtoEmail.EmailDTO;
import ar.utn.ba.ddsi.mailing.models.entities.Email;
import java.util.List;

public interface IEmailService {
    EmailDTO crearEmail(EmailDTO emailDTO);
    List<EmailDTO> obtenerEmails(Boolean pendiente);
    void procesarPendientes();
    void loguearEmailsPendientes();
} 