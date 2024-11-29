package co.edu.cue.inventario.Fabricas;

import co.edu.cue.inventario.ElementosDti.Audiovisual;
import co.edu.cue.inventario.ElementosDti.ElementosDti;
import co.edu.cue.inventario.Enums.EstadosElementos;
import co.edu.cue.inventario.Enums.TipoDeElementos;

import java.time.LocalDate;

public class FabricaDeAudiovisual implements ElementosDtiFabrica{
    @Override
    public ElementosDti crearElementoDti(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion) {
        return new Audiovisual(identificacion, nombre, descripcion, tipo, estado, fechaCreacion);
    }
}
