package exceptions;

/**
 * Excepción que ocurre cuando la espera establecida a sido superada.
 * Exception that occurs when the established wait has been exceeded.
 * @author Ricardo.
 */
public class EsperaCompletaException extends Exception{
    public EsperaCompletaException(String message){
        super(message);
    }
    public EsperaCompletaException(){
    }
    
}
