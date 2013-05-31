package Exceptions.facades;

import javax.ejb.ApplicationException;

/**
 *
 * @author Maarten
 */
@ApplicationException
public class DBFacadeMainException extends RuntimeException{
    public DBFacadeMainException(){
    }
    public DBFacadeMainException(String s){
        super("[DBFacadeMainException]" + s);
    }
}