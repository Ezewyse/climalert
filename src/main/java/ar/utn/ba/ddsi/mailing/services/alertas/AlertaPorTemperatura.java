package ar.utn.ba.ddsi.mailing.services.alertas;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;

public class AlertaPorTemperatura implements TipoAlerta {
    private double temperaturaLimite;

    public AlertaPorTemperatura(double temperaturaLimite) {
        this.temperaturaLimite = temperaturaLimite;
    }

    @Override
    public boolean esCumplidaPor(Clima clima) {
        return clima.getCondiciones().getTemperaturaCelsius() > temperaturaLimite;
    }
}
