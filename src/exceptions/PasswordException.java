package exceptions;

/**
 * Excepción que ocurre cuando la contraseña es incorrecta.
 * Exception that occurs when the password is incorrect.
 * @author Equipo.
 */
public class PasswordException extends Exception {
    public PasswordException(String message) {
        super(message);
    }
    
    public PasswordException() {
    }
    
}
