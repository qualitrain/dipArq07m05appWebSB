package mx.com.qtx.dipArq07m05appWebSB.web.monitoreo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import mx.com.qtx.dipArq07m05appWebSB.web.monitoreo.dtos.ComponenteWebInfo;
import mx.com.qtx.dipArq07m05appWebSB.web.monitoreo.dtos.PeticionWebInfo;

@Service
public class ServicioMonitoreo {

    private static final int MAX_PETICIONES = 50;
    
    // CopyOnWriteArrayList para concurrencia segura sin bloqueos excesivos
    private final List<PeticionWebInfo> ultimasPeticiones = new CopyOnWriteArrayList<>();
    private final List<ComponenteWebInfo> filtros = new ArrayList<>();
    private final List<ComponenteWebInfo> servlets = new ArrayList<>();

    public void registrarPeticion(PeticionWebInfo info) {
        ultimasPeticiones.add(0, info); // Agrega al principio (más reciente primero)
        if (ultimasPeticiones.size() > MAX_PETICIONES) {
            ultimasPeticiones.remove(ultimasPeticiones.size() - 1); // Elimina el más viejo
        }
    }

    public List<PeticionWebInfo> getUltimasPeticiones() {
        // Retorna una vista inmodificable para proteger la colección interna
        return Collections.unmodifiableList(ultimasPeticiones);
    }

    public synchronized void agregarFiltro(ComponenteWebInfo filtro) {
        this.filtros.add(filtro);
    }

    public synchronized void agregarServlet(ComponenteWebInfo servlet) {
        this.servlets.add(servlet);
    }

    public synchronized void limpiarComponentesWeb() {
        this.filtros.clear();
        this.servlets.clear();
    }

    public synchronized List<ComponenteWebInfo> getFiltros() {
        return new ArrayList<>(filtros); // Retorna copia
    }

    public synchronized List<ComponenteWebInfo> getServlets() {
        return new ArrayList<>(servlets); // Retorna copia
    }
}
