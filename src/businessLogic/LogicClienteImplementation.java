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
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.ResourceBundle;

/**
 * La implementación de la interfaz LogicCliente.
 * The implementation of the LogicCliente class.
 * @author Luis & Ricardo
 */
public class LogicClienteImplementation implements LogicCliente{
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger("businessLogic.LogicClienteImplementation");
    private final int port=Integer.parseInt(ResourceBundle.getBundle("businessLogic.logicConfig").getString("port"));
    private final String ip=ResourceBundle.getBundle("businessLogic.logicConfig").getString("ip");
    /**
     * Comprueba si existe o no el usuario con la combinacion de login y password.
     * Confirms if exists the user with the same login and password combination.
     * @param id Login del usuario / The user login.
     * @param password La contraseña del usuario / The user password.
     * @return Usuario si es correcto | Null en los demas casos. / The user if 
     * it is correct | Null in the other cases.
     * @throws LogicException Error de logica. / Logic error.
     * @throws PasswordException La contraseña es incorrecta. / The password is 
     * incorrect.
     * @throws LoginIDException El login es incorrecto. / The login is incorrect.
     * @throws DAOException Error al acceso de datos. / Error in the data acces.
     * @throws ServerException Error del servidor. / Server error.
     * @throws EsperaCompletaException El servidor esta antendiendo el máximo
     * de clientes en este momento. /  The server have more clients than it can
     * handle.
     */
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
        while(repetir && intentos<5){
            try{
                //Enlazamos el socket y los flujos
                miSocket=new Socket(ip,port);
                miSocket.setSoTimeout(3000);
                flujo_salida=new ObjectOutputStream(miSocket.getOutputStream());
                flujo_entrada=new ObjectInputStream(miSocket.getInputStream());
                
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
                        LOGGER.info("Espera");
                        sleep(1000);
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
                
            }catch(InterruptedException e){
                LOGGER.info("Despierta");
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
                    if(intentos == 5){
                        throw new EsperaCompletaException();
                    }
                }catch(IOException e){
                    LOGGER.severe(e.getMessage());
                }
            }
        }
        return user;
    }
    /**
     * Comprueba y registra a un usuario.
     * Check and register a user.
     * @param user El usuario a registrar. / The user to register.
     * @return True si se a registrado correctamente | False en los demas casos
     * / True if the user is registed | False in the other cases.
     * @throws EsperaCompletaException El servidor esta antendiendo el máximo
     * de clientes en este momento. /  The server have more clients than it can
     * handle.
     * @throws LogicException Error de logica. / Logic error.
     * @throws LoginIDException Ya existe alguien con ese Login. / Exists other
     * user with this login.
     * @throws DAOException Error al acceso de datos. / Error in the data acces.
     * @throws ServerException Error del servidor. / Server error.
     */
    @Override
    public boolean registro(User user) throws EsperaCompletaException,LogicException,LoginIDException, DAOException,ServerException {
        int intentos=0;
        boolean repetir=true;
        boolean retorno=false;
        //Declaro socket y los flujos vacios
        Socket miSocket=null;
        ObjectOutputStream flujo_salida=null;
        ObjectInputStream flujo_entrada=null;
        while(repetir && intentos<5){
            try{
                //Enlazamos el socket y los flujos
                miSocket=new Socket(ip,port);
                miSocket.setSoTimeout(3000);
                flujo_salida=new ObjectOutputStream(miSocket.getOutputStream());
                flujo_entrada=new ObjectInputStream(miSocket.getInputStream());
                
                repetir=false;
                //Empezamos la comunicación
                Mensaje mensaje=new Mensaje(1,user);
                flujo_salida.writeObject(mensaje);
                Mensaje resultado=(Mensaje)flujo_entrada.readObject();
                //Ahora intempretamos el resutado
                switch(resultado.getOpc()){
                    case 1:
                        retorno=true;
                        break;
                    case -1:
                        intentos++;
                        LOGGER.info("Espera");
                        sleep(1000);
                        repetir=true;
                        break;
                    case -2:
                        throw new LoginIDException("Ya existe alguien con ese IDE");
                    case -5:
                        throw new DAOException();
                    case -6:
                        throw new ServerException();
                }
                if(intentos==5)
                    throw new EsperaCompletaException();
                
            }catch(InterruptedException e){
                LOGGER.info("Despierta");
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
                    
                }catch(IOException e){
                    LOGGER.severe(e.getMessage());
                }
            }
        }
        return retorno;
    }
    
    @Override
    public void finAcceso(String login) throws EsperaCompletaException,LogicException, DAOException,ServerException {
        int intentos=0;
        boolean repetir=true;
        //Declaro socket y los flujos vacios
        Socket miSocket=null;
        ObjectOutputStream flujo_salida=null;
        ObjectInputStream flujo_entrada=null;
        while(repetir && intentos<5){
            try{
                //Enlazamos el socket y los flujos
                miSocket=new Socket(ip,port);
                miSocket.setSoTimeout(3000);
                flujo_salida=new ObjectOutputStream(miSocket.getOutputStream());
                flujo_entrada=new ObjectInputStream(miSocket.getInputStream());
                
                repetir=false;
                //Empezamos la comunicación
                Mensaje mensaje=new Mensaje(3,login);
                flujo_salida.writeObject(mensaje);
                Mensaje resultado=(Mensaje)flujo_entrada.readObject();
                //Ahora intempretamos el resutado
                switch(resultado.getOpc()){
                    case 3:
                        LOGGER.info("Acceso actualiado.");
                        break;
                    case -1:
                        intentos++;
                        LOGGER.info("Espera");
                        sleep(1000);
                        repetir=true;
                        break;
                    case -5:
                        throw new DAOException();
                    case -6:
                        throw new ServerException();
                }
                if(intentos==5)
                    throw new EsperaCompletaException();
                
            }catch(InterruptedException e){
                LOGGER.info("Despierta");
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
                    
                }catch(IOException e){
                    LOGGER.severe(e.getMessage());
                }
            }
        }
    }
    
}
