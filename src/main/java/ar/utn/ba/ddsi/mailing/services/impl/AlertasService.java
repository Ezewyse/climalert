package ar.utn.ba.ddsi.mailing.services.impl;

import ar.utn.ba.ddsi.mailing.models.entities.Alerta;
import ar.utn.ba.ddsi.mailing.services.alertas.EvaluadorAlertas;
import ar.utn.ba.ddsi.mailing.services.alertas.AlertaPorTemperatura;
import ar.utn.ba.ddsi.mailing.services.alertas.AlertaPorHumedad;
import ar.utn.ba.ddsi.mailing.models.entities.Clima;
import ar.utn.ba.ddsi.mailing.models.dtoEmail.EmailDTO;
import ar.utn.ba.ddsi.mailing.services.impl.EmailService;
//import ar.utn.ba.ddsi.mailing.models.entities.Email;
import ar.utn.ba.ddsi.mailing.models.repositories.IClimaRepository;
import ar.utn.ba.ddsi.mailing.services.IAlertasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.List;

@Service
public class AlertasService implements IAlertasService {
    private static final Logger logger = LoggerFactory.getLogger(AlertasService.class);

    private final IClimaRepository climaRepository;
    private final EmailService emailService;
    private final EvaluadorAlertas evaluadorAlertas;
    private final String remitente;
    private final List<String> destinatarios;

    public AlertasService(
            IClimaRepository climaRepository,
            EmailService emailService,
            @Value("${email.alertas.remitente}") String remitente,
            @Value("${email.alertas.destinatarios}") String destinatarios,
            @Value("${alerta.temperatura.limite}") double temperaturaLimite,
            @Value("${alerta.humedad.limite}") int humedadLimite){

        this.climaRepository = climaRepository;
        this.emailService = emailService;
        this.evaluadorAlertas = new EvaluadorAlertas(List.of(
                new AlertaPorTemperatura(temperaturaLimite),
                new AlertaPorHumedad(humedadLimite)
        ));
        this.remitente = remitente;
        this.destinatarios = Arrays.asList(destinatarios.split(","));
    }

    @Override
    public Mono<Void> generarAlertasYAvisar() {
        return Mono.fromCallable(() -> climaRepository.findByProcesado(false))
            .flatMap(climas -> {
                logger.info("Procesando {} registros de clima no procesados", climas.size());
                return Mono.just(climas);
            })
            .flatMap(climas -> {
                climas.stream()
                    .filter(evaluadorAlertas::cumpleCondiciones)
                    .forEach(this::generarYEnviarEmail);
                
                // Marcar todos como procesados
                climas.forEach(clima -> {
                    clima.setProcesado(true);
                    climaRepository.save(clima);
                });
                
                return Mono.empty();
            })
            .onErrorResume(e -> {
                logger.error("Error al procesar alertas: {}", e.getMessage());
                return Mono.empty();
            })
            .then();
    }

    private void generarYEnviarEmail(Clima clima) {
        String asunto = "Alerta de Clima - Condiciones Extremas";
        String mensaje = String.format(
            "ALERTA: Condiciones climáticas extremas detectadas en %s\n\n" +
            "Temperatura: %.1f°C\n" +
            "Humedad: %d%%\n" +
            "Condición: %s\n" +
            "Velocidad del viento: %.1f km/h\n\n" +
            "Se recomienda tomar precauciones.",
            clima.getUbicacion().getCiudad(),
            clima.getCondiciones().getTemperaturaCelsius(),
            clima.getCondiciones().getHumedad(),
            clima.getCondiciones().getCondicion(),
            clima.getCondiciones().getVelocidadVientoKmh()

        );

        for (String destinatario : destinatarios) {
            EmailDTO emailDTO = new EmailDTO(destinatario, remitente, asunto, mensaje);
            emailService.crearEmail(emailDTO);
        }
        
        logger.info("Email de alerta generado para {} - Enviado a {} destinatarios",
                clima.getUbicacion().getCiudad(), destinatarios.size());

    }
} 