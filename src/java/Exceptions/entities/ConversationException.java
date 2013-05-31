package Exceptions.entities;

import javax.ejb.ApplicationException;

/**
 *
 * @author Maarten
 */
@ApplicationException
public class ConversationException extends RuntimeException{
    public ConversationException(){
    }
    public ConversationException(String s){
        super("[ConversationException]" + s);
    }
}