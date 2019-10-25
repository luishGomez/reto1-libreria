/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package businessLogic;

import clases.Mensaje;
import clases.User;
import exceptions.DAOException;
import exceptions.EsperaCompletaException;
import exceptions.LogicException;
import exceptions.LoginIDException;
import exceptions.PasswordException;
import exceptions.ServerException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;

/**
 *
 * @author Luis & Ricardo
 */
public class LogicClienteImplementation implements LogicCliente{
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger("businessLogic.LogicClienteImplementation");
    private final int port=Integer.parseInt(ResourceBundle.getBundle("businessLogic.logicConfig").getString("port"));
    private final String ip=ResourceBundle.getBundle("businessLogic.logicConfig").getString("ip");
    @Override
    public User login(String id, String password) throws LogicException,
            PasswordException,LoginIDException, DAOException, ServerException, EsperaCompletaException{
        int intentos=0;
        boolean repetir=true;
        User user=null;
        //Declaro socket y los flujos vacios
        Socket miSocket=null;
        ObjectOutputStream flujo_salida=null;
        ObjectInputStream flujo_entrada=null;
        try{
            //Enlazamos el socket y los flujos
            miSocket=new Socket(ip,port);
            flujo_salida=new ObjectOutputStream(miSocket.getOutputStream());
            flujo_entrada=new ObjectInputStream(miSocket.getInputStream());
            while(repetir && intentos<10){
                repetir=false;
                //Empezamos la comunicación
                Mensaje mensaje=new Mensaje(2,new User(id,password));
                flujo_salida.writeObject(mensaje);
                Mensaje resultado=(Mensaje)flujo_entrada.readObject();
                //Ahora intempretamos el resutado
                switch(resultado.getOpc()){
                    case 2:
                        user=(User)resultado.getData();
                        break;
                    case -1:
                        intentos++;
                        //DUERME UN SEGUNDO
                        repetir=true;
                        break;
                    case -3:
                        throw new LoginIDException("Ese ID no existe");
                    case -4:
                        throw new PasswordException();
                    case -5:
                        throw new DAOException();
                    case -6:
                        throw new ServerException();
                }
            }
        }catch(IOException e){
            LOGGER.severe(e.getMessage());
            throw new LogicException("El servidor no responde");
        }catch(ClassNotFoundException e){
            LOGGER.severe(e.getMessage());
        }finally{
            try{
                if(flujo_entrada!=null)
                    flujo_entrada.close();
                if(flujo_salida!=null)
                    flujo_salida.close();
                if(miSocket!=null)
                    miSocket.close();
                if(intentos == 10){
                    throw new EsperaCompletaException();
                }
            }catch(IOException e){
                LOGGER.severe(e.getMessage());
            }
        }
        return user;
    }
    
    @Override
    public boolean registro(User user) throws EsperaCompletaException,LogicException,LoginIDException, DAOException,ServerException {
        int intentos=0;
        boolean repetir=true;
        boolean retorno=false;
        //Declaro socket y los flujos vacios
        Socket miSocket=null;
        ObjectOutputStream flujo_salida=null;
        ObjectInputStream flujo_entrada=null;
        try{
            //Enlazamos el socket y los flujos
            miSocket=new Socket(ip,port);
            flujo_salida=new ObjectOutputStream(miSocket.getOutputStream());
            flujo_entrada=new ObjectInputStream(miSocket.getInputStream());
            while(repetir && intentos<10){
                repetir=false;
                //Empezamos la comunicación
                Mensaje mensaje=new Mensaje(1,user);
                flujo_salida.writeObject(mensaje);
                Mensaje resultado=(Mensaje)flujo_entrada.readObject();
                //Ahora intempretamos el resutado
                switch(resultado.getOpc()){
                    case 1:
                        retorno=(Boolean)mensaje.getData();
                        break;
                    case -1:
                        intentos++;
                        //DUERME UN SEGUNDO
                        repetir=true;
                        break;
                    case -2:
                        throw new LoginIDException("Ya existe alguien con ese IDE");
                    case -5:
                        throw new DAOException();
                    case -6:
                        throw new ServerException();
                }
            }
        }catch(IOException e){
            LOGGER.severe(e.getMessage());
            throw new LogicException("El servidor no responde");
        }catch(ClassNotFoundException e){
            LOGGER.severe(e.getMessage());
        }finally{
            try{
                if(flujo_entrada!=null)
                    flujo_entrada.close();
                if(flujo_salida!=null)
                    flujo_salida.close();
                if(miSocket!=null)
                    miSocket.close();
                if(intentos==10)
                    throw new EsperaCompletaException();
            }catch(IOException e){
                LOGGER.severe(e.getMessage());
            }
        }
        return retorno;
    }
    
}
