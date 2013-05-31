package Exceptions.entities;

import javax.ejb.ApplicationException;

/**
 *
 * @author Maarten
 */
@ApplicationException
public class AccountException extends RuntimeException{
    public AccountException(){
    }
    public AccountException(String s){
        super("[AccountException]" + s);
    }
}