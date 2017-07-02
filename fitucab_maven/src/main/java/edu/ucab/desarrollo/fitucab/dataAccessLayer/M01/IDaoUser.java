package edu.ucab.desarrollo.fitucab.dataAccessLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.GetUserException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;

/**
 * Created by karo on 28/06/17.
 */
public interface IDaoUser extends IDao {
    String testEmail (String email) throws SQLException;
    Entity login(Entity e) throws SQLException;
    public boolean update() throws CreateHomeException;
    public void UpdateName(String name) throws BdConnectException, SQLException;
    public void UpdateEmail(String email);
    public void UpdatePhone(String phone);
    /**
     * Metodo que implementa el DaoUser que recibe un _id
     * @param _id
     */
    public Entity read(int _id) throws GetUserException;

}
