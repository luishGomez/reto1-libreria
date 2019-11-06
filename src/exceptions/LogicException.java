package exceptions;

/**
 * Excepción que ocurre cuando hay algún error en la parte de LogicCliente.
 * Exception that occurs when there is some error in the Logiccliente part.
 * @author Ricardo.
 */
public class LogicException extends Exception{
    public LogicException(String message){
        super(message);
    }
    public LogicException(){
    }
    
}
