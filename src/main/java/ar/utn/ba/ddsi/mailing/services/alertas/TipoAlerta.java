package ar.utn.ba.ddsi.mailing.services.alertas;
import ar.utn.ba.ddsi.mailing.models.entities.Clima;


public interface TipoAlerta {
    boolean esCumplidaPor(Clima clima);
}
