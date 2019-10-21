/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import clases.User;

/**
 *
 * @author Usuario
 */
public interface LogicCliente {
    public User login(String id,String password);
    public boolean registro(User user);
}
