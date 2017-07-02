package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 * Created by Barbara Fernadez on 7/1/2017.
 */
public class ChangeTrainingNameCommand extends Command {

    private Entity modifyTrainingObject;

    public ChangeTrainingNameCommand(Entity training){
        this.modifyTrainingObject = training;
    }
    public void execute() {
        DaoTraining dao = DaoFactory.instanceDaoTraining(modifyTrainingObject);
        dao.modifyName(modifyTrainingObject);
    }
}