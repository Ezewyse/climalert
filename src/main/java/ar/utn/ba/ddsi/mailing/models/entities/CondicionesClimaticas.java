package ar.utn.ba.ddsi.mailing.models.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CondicionesClimaticas {
    private double temperaturaCelsius;
    private double temperaturaFahrenheit;
    private String condicion;
    private double velocidadVientoKmh;
    private int humedad;

    public CondicionesClimaticas(double temperaturaCelsius, double temperaturaFahrenheit, String condicion, double velocidadVientoKmh, int humedad) {
        this.temperaturaCelsius = temperaturaCelsius;
        this.temperaturaFahrenheit = temperaturaFahrenheit;
        this.condicion = condicion;
        this.velocidadVientoKmh = velocidadVientoKmh;
        this.humedad = humedad;
    }
}