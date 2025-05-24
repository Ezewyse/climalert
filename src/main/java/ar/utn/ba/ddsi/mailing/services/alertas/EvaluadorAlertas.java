package ar.utn.ba.ddsi.mailing.services.alertas;
import ar.utn.ba.ddsi.mailing.models.entities.Clima;
import ar.utn.ba.ddsi.mailing.services.alertas.TipoAlerta;
import java.util.List;

public class EvaluadorAlertas {
    private List<TipoAlerta> condiciones;

    public EvaluadorAlertas(List<TipoAlerta> condiciones) {
        this.condiciones = condiciones;
    }

    public boolean cumpleCondiciones(Clima clima) {
        return condiciones.stream().allMatch(condicion -> condicion.esCumplidaPor(clima));
    }
}
