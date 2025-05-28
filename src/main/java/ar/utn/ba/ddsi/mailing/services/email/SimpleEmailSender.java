package ar.utn.ba.ddsi.mailing.services.email;
import ar.utn.ba.ddsi.mailing.models.dtoEmail.EmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleEmailSender implements EmailSenderAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SimpleEmailSender.class);

    @Override
    public void enviarEmail(EmailDTO emailDTO) {
        logger.info("Enviando email a de la manera tradicional'");
    }
}


