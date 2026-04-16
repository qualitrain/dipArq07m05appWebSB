package mx.com.qtx.dipArq07m05appWebSB.web.monitoreo.dtos;

public class PeticionWebInfo {
    private String method;
    private String path;
    private String resBody;
    private String reqBody;
    private int status;
    private double tCicloCompleto;
    private double tProcesoPeticion;
    private double tProcesoRespuesta;
    private String usuario;

    public PeticionWebInfo(String method, String path, String reqBody, int status, String resBody, 
                           double tProcesoPeticion, double tProcesoRespuesta, double tCicloCompleto, String usuario) {
        this.method = method;
        this.path = path;
        this.reqBody = reqBody;
        this.status = status;
        this.resBody = resBody;
        this.tProcesoPeticion = tProcesoPeticion;
        this.tProcesoRespuesta = tProcesoRespuesta;
        this.tCicloCompleto = tCicloCompleto;
        this.usuario = usuario;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getReqBody() {
        return reqBody;
    }

    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResBody() {
        return resBody;
    }

    public void setResBody(String resBody) {
        this.resBody = resBody;
    }

    public double gettProcesoPeticion() {
        return tProcesoPeticion;
    }

    public void settProcesoPeticion(double tProcesoPeticion) {
        this.tProcesoPeticion = tProcesoPeticion;
    }

    public double gettProcesoRespuesta() {
        return tProcesoRespuesta;
    }

    public void settProcesoRespuesta(double tProcesoRespuesta) {
        this.tProcesoRespuesta = tProcesoRespuesta;
    }

    public double gettCicloCompleto() {
        return tCicloCompleto;
    }

    public void settCicloCompleto(double tCicloCompleto) {
        this.tCicloCompleto = tCicloCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
