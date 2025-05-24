package ar.utn.ba.ddsi.mailing.models.entities;
import java.time.LocalDateTime;



public class Alerta {
    private Long id;
    private Clima clima;
    private String descripcion;
    private LocalDateTime fechaGeneracion;
    private boolean enviada;

    public Alerta(Clima clima, String descripcion) {
        this.clima = clima;
        this.descripcion = descripcion;
        this.fechaGeneracion = LocalDateTime.now();
        this.enviada = false;
    }

    public Long getId() {
        return id;
    }

    public Clima getClima() {
        return clima;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public boolean isEnviada() {
        return enviada;
    }

    public void marcarComoEnviada() {
        this.enviada = true;
    }
}

