/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

/**
 *
 * @author Usuario
 */
public class LogicFactory {
      private LogicCliente logic= new LogicClienteImplementation();
      
      public LogicCliente getLogicCliente(){
          return logic;
      }
}
