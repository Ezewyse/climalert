package ar.utn.ba.ddsi.mailing.models.entities;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class Clima {
    private Long id;
    /*
    private String ciudad;
    private String region;
    private String pais;*/
    private Ubicacion ubicacion;
    /*
    private Double temperaturaCelsius;
    private Double temperaturaFahrenheit;
    private String condicion;
    private Double velocidadVientoKmh;
    private Integer humedad;
    */
    private CondicionesClimaticas condiciones;
    private LocalDateTime fechaActualizacion;
    private boolean procesado;

    public Clima(Ubicacion ubicacion, CondicionesClimaticas condiciones) {
        this.ubicacion = ubicacion;
        this.condiciones = condiciones;
        this.fechaActualizacion = LocalDateTime.now();
        this.procesado = false;
    }

} 