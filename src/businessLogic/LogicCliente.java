package businessLogic;

import clases.User;
import exceptions.DAOException;
import exceptions.EsperaCompletaException;
import exceptions.LogicException;
import exceptions.LoginIDException;
import exceptions.PasswordException;
import exceptions.ServerException;

/**
 * Interfaz de LogicCliente.
 * LogicCliente interface.
 * @author Luis.
 */
public interface LogicCliente {
    /**
     * Comprueba si existe o no el usuario con la combinacion de login y password.
     * Confirms if exists the user with the same login and password combination.
     * @param id Login del usuario / The user login.
     * @param password La contraseña del usuario / The user password.
     * @return Usuario si es correcto | Null en los demas casos. / The user if 
     * it is correct | Null in the other cases.
     * @throws LogicException Error de logica. / Logic error.
     * @throws PasswordException La contraseña es incorrecta. / The password is 
     * incorrect.
     * @throws LoginIDException El login es incorrecto. / The login is incorrect.
     * @throws DAOException Error al acceso de datos. / Error in the data acces.
     * @throws ServerException Error del servidor. / Server error.
     * @throws EsperaCompletaException El servidor esta antendiendo el máximo
     * de clientes en este momento. /  The server have more clients than it can
     * handle.
     */
    public User login(String id,String password) throws LogicException,PasswordException,LoginIDException, DAOException, ServerException, EsperaCompletaException;
    /**
     * Comprueba y registra a un usuario.
     * Check and register a user.
     * @param user El usuario a registrar. / The user to register.
     * @return True si se a registrado correctamente | False en los demas casos
     * / True if the user is registed | False in the other cases.
     * @throws EsperaCompletaException El servidor esta antendiendo el máximo
     * de clientes en este momento. /  The server have more clients than it can
     * handle.
     * @throws LogicException Error de logica. / Logic error.
     * @throws LoginIDException Ya existe alguien con ese Login. / Exists other
     * user with this login.
     * @throws DAOException Error al acceso de datos. / Error in the data acces.
     * @throws ServerException Error del servidor. / Server error.
     */
    public boolean registro(User user) throws EsperaCompletaException,LogicException,LoginIDException, DAOException,ServerException;
    
    public void finAcceso(String login)throws EsperaCompletaException,LogicException,DAOException,ServerException;
}
