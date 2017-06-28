package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

public class CreateTrainingCommand extends Command {

    Entity _newTraining;
    int _userId;

    public CreateTrainingCommand(Entity newTraining, int userId){

        this._newTraining = newTraining;
        this._userId = userId;
    }

    public void execute() {
        try{
            DaoTraining dao = DaoFactory.instanceDaoTraining( _newTraining);
            dao.create(_newTraining);
        }
        catch(Exception e){
            //lanzar exception
        }
    }
}
