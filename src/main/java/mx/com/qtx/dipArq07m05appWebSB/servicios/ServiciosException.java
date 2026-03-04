package mx.com.qtx.dipArq07m05appWebSB.servicios;

public class ServiciosException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServiciosException(String message) {
        super(message);
    }

    public ServiciosException(String message, Throwable cause) {
        super(message, cause);
    }
}
