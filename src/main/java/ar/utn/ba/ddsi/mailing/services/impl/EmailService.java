package ar.utn.ba.ddsi.mailing.services.impl;

import ar.utn.ba.ddsi.mailing.models.entities.Email;
import ar.utn.ba.ddsi.mailing.models.dtoEmail.EmailDTO;
import ar.utn.ba.ddsi.mailing.models.repositories.IEmailRepository;
import ar.utn.ba.ddsi.mailing.services.email.EmailSenderAdapter ;
import ar.utn.ba.ddsi.mailing.services.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService implements IEmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final IEmailRepository emailRepository;
    private final EmailSenderAdapter emailSenderAdapter;

    public EmailService(IEmailRepository emailRepository, EmailSenderAdapter emailSenderAdapter) {
        this.emailRepository = emailRepository;
        this.emailSenderAdapter = emailSenderAdapter;
    }

    @Override
    public EmailDTO crearEmail(EmailDTO emailDTO) {
        Email email = new Email(emailDTO.getDestinatario(), emailDTO.getRemitente(), emailDTO.getAsunto(), emailDTO.getMensaje(), emailSenderAdapter);
        email = emailRepository.save(email);
        return new EmailDTO(email.getDestinatario(), email.getRemitente(), email.getAsunto(), email.getContenido());
    }



    @Override
    public List<EmailDTO> obtenerEmails(Boolean pendiente) {
        return emailRepository.findByEnviado(!pendiente)
                .stream()
                .map(email -> new EmailDTO(email.getDestinatario(), email.getRemitente(), email.getAsunto(), email.getContenido()))
                .collect(Collectors.toList());
    }


    @Override
    public void procesarPendientes() {
        List<Email> pendientes = emailRepository.findByEnviado(false);
        pendientes.forEach(Email::enviar);
        pendientes.forEach(emailRepository::save);
    }

    @Override
    public void loguearEmailsPendientes() {
        List<EmailDTO> pendientes = obtenerEmails(true);
        logger.info("Emails pendientes de envío: {}", pendientes.size());
        pendientes.forEach(email -> 
            logger.info("Email pendiente - Destinatario: {}, Asunto: {}",
                email.getDestinatario(), 
                email.getAsunto())
        );
    }
} 