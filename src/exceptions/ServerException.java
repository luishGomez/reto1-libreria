package exceptions;

/**
 * Excepción que ocurre cuando el servidor falla.
 * Exception that occurs when the server fails.
 * @author Equipo.
 */
public class ServerException extends Exception{
    public ServerException(String message){
        super(message);
    }
    public ServerException(){
    }
    
}
