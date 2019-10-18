/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocioReto1;

import java.io.Serializable;

/**
 *
 * @author 2dam
 */
public class Mensaje implements Serializable{
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
