package ar.utn.ba.ddsi.mailing.controllers;

import ar.utn.ba.ddsi.mailing.models.entities.Email;
import ar.utn.ba.ddsi.mailing.services.IEmailService;
import ar.utn.ba.ddsi.mailing.services.impl.EmailService;
import ar.utn.ba.ddsi.mailing.models.dtoEmail.EmailDTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/emails")
public class EmailController {
    private final IEmailService emailService;

    public EmailController(IEmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public EmailDTO crearEmail(@RequestBody EmailDTO emailDTO) {
        return emailService.crearEmail(emailDTO);
    }

    @GetMapping
    public List<EmailDTO> obtenerEmails(@RequestParam(required = false) Boolean pendiente) {
        return emailService.obtenerEmails(pendiente);
    }
} 