package exceptions;

/**
 * Excepción que ocurre cuando falla la base de datos.
 * Exception that occurs when the database fails.
 * @author Ricardo.
 */
public class DAOException extends Exception{
    public DAOException(String message){
        super(message);
    }
    public DAOException(){
    }
    
}
