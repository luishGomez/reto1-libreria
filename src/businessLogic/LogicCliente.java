/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import clases.User;
import exceptions.DAOException;
import exceptions.EsperaCompletaException;
import exceptions.LogicException;
import exceptions.LoginIDException;
import exceptions.PasswordException;
import exceptions.ServerException;

/**
 *
 * @author Usuario
 */
public interface LogicCliente {
    public User login(String id,String password) throws LogicException,PasswordException,LoginIDException, DAOException, ServerException;
    public boolean registro(User user) throws EsperaCompletaException,LogicException,LoginIDException, DAOException,ServerException;
}
