package edu.ucab.desarrollo.fitucab.dataAccessLayer.M11;

import edu.ucab.desarrollo.fitucab.common.Exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Food;
import edu.ucab.desarrollo.fitucab.common.entities.Sql;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by charbel on 29/06/2017.
 */
public interface IDaoFood extends IDao {

    void Create(Entity e);

    public String getFoodPer(Entity e) throws SQLException, BdConnectException;

    public  String getFoodAll (Entity e) throws SQLException, BdConnectException;

    public String getSugge (Entity e ) throws SQLException, BdConnectException;

    public String getFoodAuto (Entity e ) throws SQLException, BdConnectException;

    public String DeletPerFood (Entity e ) throws SQLException, BdConnectException;

    public String updatePerso (Entity e ) throws SQLException, BdConnectException;

    public  String InsertarAlimen (Entity e)  throws SQLException, BdConnectException;

    public String insertarPersoFood (Entity e)  throws SQLException, BdConnectException;

    public String getPersonalizedLis (Entity e ) throws BdConnectException, SQLException;


}