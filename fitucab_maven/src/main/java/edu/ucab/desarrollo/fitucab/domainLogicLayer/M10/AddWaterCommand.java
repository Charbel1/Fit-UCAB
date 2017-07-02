package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;import com.google.gson.Gson;import edu.ucab.desarrollo.fitucab.common.entities.Entity;import edu.ucab.desarrollo.fitucab.common.entities.Water;import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;import edu.ucab.desarrollo.fitucab.dataAccessLayer.M10.IDaoWater;import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;import org.slf4j.LoggerFactory;import java.sql.SQLException;import java.util.ArrayList;/** * Comando para agregar agua que extiende de command */public class AddWaterCommand extends Command {    Entity _water;    public String returned;    /**     * constructor de agregar agua     * @param water     */    public AddWaterCommand (Entity water){ _water = water; };    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AddWaterCommand.class);    public void execute() {        logger.debug("Debug: Agregando water.");        IDaoWater daoWater = DaoFactory.instanceDaoWater(_water);        try {            Water water = (Water) daoWater.create(_water);            Gson gson = new Gson();            returned = gson.toJson(water);        } catch (SQLException e) {            e.printStackTrace();            MessageException error = new MessageException(e, this.getClass().getSimpleName(),                    Thread.currentThread().getStackTrace()[1].getMethodName());            logger.debug("Debug: ", error.toString());            logger.error("Error: ", error.toString());        }        //returned = "HOLA BEBE";    }}