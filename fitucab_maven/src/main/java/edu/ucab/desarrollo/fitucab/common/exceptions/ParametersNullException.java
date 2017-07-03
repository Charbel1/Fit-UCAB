package edu.ucab.desarrollo.fitucab.common.exceptions;

/**
 * Created by jaorr on 30/06/17.
 */
public class ParametersNullException extends NullPointerException {

    private String _paramName;
    private final int _status = 501;



    public ParametersNullException(String paramName) {
        this._paramName = paramName;
    }

    public ParametersNullException(String s, String paramName) {
        super(s);
        this._paramName = paramName;
    }

    public int getStatus() {
        return _status;
    }

    @Override
    public String getMessage(){
        return "Error: el siguientes campo es null, " + _paramName;
    }
}