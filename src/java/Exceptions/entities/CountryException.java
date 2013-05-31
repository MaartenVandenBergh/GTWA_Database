package Exceptions.entities;

import javax.ejb.ApplicationException;

/**
 *
 * @author Maarten
 */
@ApplicationException
public class CountryException extends RuntimeException{
    public CountryException(){
    }
    public CountryException(String s){
        super("[CountryException]" + s);
    }
}