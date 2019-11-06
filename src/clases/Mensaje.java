package clases;

import java.io.Serializable;

/**
 * Es el mensaje que enviamos en las peticiones contra el servidor.
 * It’s the message we send in the requests against the server.
 * @author Equipo.
 */
public class Mensaje implements Serializable{
    private static final long serialVersionUID = 1L;
    private int opc;
    private Object data;

    public Mensaje() {
    }

    public Mensaje(int opc, Object data) {
        this.opc = opc;
        this.data = data;
    }
    
    
    public int getOpc() {
        return opc;
    }

    public void setOpc(int opc) {
        this.opc = opc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
}
