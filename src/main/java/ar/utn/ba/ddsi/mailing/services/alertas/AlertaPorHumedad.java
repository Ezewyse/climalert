package ar.utn.ba.ddsi.mailing.services.alertas;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;

public class AlertaPorHumedad implements TipoAlerta {
    private int humedadLimite;

    public AlertaPorHumedad(int humedadLimite) {
        this.humedadLimite = humedadLimite;
    }

    @Override
    public boolean esCumplidaPor(Clima clima) {
        return clima.getCondiciones().getHumedad() > humedadLimite;
    }
}
