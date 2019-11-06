package exceptions;

/**
 * Excepción que ocurre cuando el Login del usuario es erróneo o ya existe.
 * Exception that occurs when the user’s login is erroneous or already exists.
 * @author Ricardo.
 */
public class LoginIDException extends Exception{
    public LoginIDException(String message){
        super(message);
    }
    public LoginIDException(){
    }
    
}
