package businessLogic;

/**
 * La factoria de LogicCliente.
 * The factory of the LogicCliente.
 * @author Luis.
 */
public class LogicFactory {
      private LogicCliente logic= new LogicClienteImplementation();
      
      public LogicCliente getLogicCliente(){
          return logic;
      }
}
