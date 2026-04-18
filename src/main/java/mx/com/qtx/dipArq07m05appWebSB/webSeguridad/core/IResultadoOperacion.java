package mx.com.qtx.dipArq07m05appWebSB.webSeguridad.core;

public interface IResultadoOperacion {
    Object getObjResultadoOk();

    void setObjResultadoOk(Object objResultadoOk);

    boolean todoOk();

    void agregarError(int codError, String adicion);

    String getResumenErrores();
}
